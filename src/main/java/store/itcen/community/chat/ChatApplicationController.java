package store.itcen.community.chat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.util.WebUtils;
import store.itcen.community.security.TokenProvider;

@Slf4j
@Controller
public class ChatApplicationController {

    private SimpMessagingTemplate simpMessage;
	private ChatRoomService chatRoomService;
	private TokenProvider tokenProvider;

	@Autowired
	public ChatApplicationController(SimpMessagingTemplate simpMessage, ChatRoomService chatRoomService, TokenProvider tokenProvider) {
		this.simpMessage = simpMessage;
		this.chatRoomService = chatRoomService;
		this.tokenProvider = tokenProvider;
	}

	//상세페이지에서 채팅방 입장
    @RequestMapping(value="/chatMessage", method=RequestMethod.GET)
    public String getWebSocketWithSockJs(ChatRoom chatRoom, Model model, HttpServletRequest request) throws IOException {
        
        //이미 chatRoom이 만들어져있는지 확인
        if (chatRoomService.countByChatId(chatRoom.getPost_id(), chatRoom.getBuyerId()) > 0) {
			log.info("채팅방 존재");
            //get ChatRoomInfo
            ChatRoom chatRoomTemp = chatRoomService.findByChatId(chatRoom.getPost_id(), chatRoom.getBuyerId());
            //load existing chat history
            List<ChatRoom> chatHistory = chatRoomService.readChatHistory(chatRoomTemp);
            //transfer chatHistory Model to View
            model.addAttribute("chatHistory", chatHistory);
        } else {
            //chatRoom 생성            
            chatRoomService.addChatRoom(chatRoom);            
            //text file 생성
            chatRoomService.createFile(chatRoom.getPost_id(), chatRoomService.getId(chatRoom.getPost_id(), chatRoom.getBuyerId()));
        }
 
            //채팅방 번호
            chatRoom.setId(chatRoomService.getId(chatRoom.getPost_id(), chatRoom.getBuyerId()));
			log.info("모델 : {}", chatRoom.toString());

            // 정보보내기

		Cookie tokenCookie = WebUtils.getCookie(request, "token");
		String token = tokenCookie.getValue();
		String buyerId = tokenProvider.validateAndGetUserId(token);
//		MemberDTO sessiondto = (MemberDTO)session.getAttribute("sessiondto");
//			String buyerId = sessiondto.getNickname();
    		String post_id = chatRoom.getPost_id();
    		int id = chatRoomService.getId(post_id, buyerId);
    		String post_title = chatRoom.getPost_title();
    		String sellerId = chatRoom.getSellerId();

    		model.addAttribute("id", id);
    		model.addAttribute("post_id", post_id);
    		model.addAttribute("buyerId", buyerId);
    		model.addAttribute("sellerId", sellerId);
    		model.addAttribute("post_title", post_title);
            
            model.addAttribute("chatRoomInfo", chatRoom);
        
        return "chat/chatBroadcastChatRoom";
    }
    
        

    // 채팅리스트에서 채팅방 입장
    @RequestMapping(value="/chatRoom/{pr_id}/{buyerId}", method=RequestMethod.GET)
	public String getChatRoom(@PathVariable Map<String, String> requestVar,
			Model model) throws IOException {

//    	String sessionId = (String)session.getAttribute("sessiondto");

		String buyerId = requestVar.get("buyerId");
		String post_id = requestVar.get("pr_id");
			
		//read chatHistory
		ChatRoom chatRoomRead = chatRoomService.findByChatId(post_id, buyerId);
		List<ChatRoom> chatHistory = chatRoomService.readChatHistory(chatRoomRead);
		model.addAttribute("chatHistory", chatHistory);
		
		int id = chatRoomService.getId(post_id, buyerId);
		String pr_title = chatRoomRead.getPost_title();
		String sellerId = chatRoomRead.getSellerId();


		model.addAttribute("id", id);
		model.addAttribute("pr_id", post_id);
		model.addAttribute("buyerId", buyerId);
		model.addAttribute("sellerId", sellerId);
		model.addAttribute("pr_title", pr_title);
		
		return "chat/chatBroadcastChatRoomList";
	}
    
    
    
    // 채팅방 리스트 
    @RequestMapping(value="/chatList", method=RequestMethod.GET)
   	public String getChatList() {
   		 return "chat/chatList";
   	}
       
       
    // 채팅방 리스트 AJAX    
    @RequestMapping(value="/chatList/ajax", method=RequestMethod.POST)
   	@ResponseBody
   	public String chatList(@RequestBody String json) {
   		
   		JSONObject jsn = new JSONObject(json);
   		String sessionid = (String) jsn.get("sessionid");
	    log.info("sessionid : {} ",sessionid);

   		List<ChatRoom> chatRoomList = chatRoomService.findByUserId(sessionid);
   		
   		JSONArray ja = new JSONArray();

   		 for (ChatRoom chatList : chatRoomList) {
   			
   			 JSONObject jo = new JSONObject();
   			 jo.put("pr_id", chatList.getPost_id());
   			 jo.put("buyerId", chatList.getBuyerId());
   			 jo.put("pr_title", chatList.getPost_title());
   			 jo.put("sellerId", chatList.getSellerId());
   			 ja.put(jo);
   		}
   		 JSONObject jsnResult = new JSONObject();
   		 jsnResult.put("chatList", ja);
   		 String result = jsnResult.toString();
   		 System.out.println("chatResult toString: " + result);
   		
   		 return result;
   	}
    
    
    
    // 채팅 전송
    @MessageMapping("/broadcast")
    public void send(ChatRoom chatRoom) throws IOException {

		log.info("메세지 맵핑 : ", chatRoom.toString());
        chatRoom.setSendTime(TimeUtils.getCurrentTimeStamp());
        //append message to txtFile
        chatRoomService.appendMessage(chatRoom);
        
        int id = chatRoom.getId();
        String url = "/user/" + id + "/queue/messages";
        simpMessage.convertAndSend(url, new ChatRoom(chatRoom.getContent(), chatRoom.getSenderId(), chatRoom.getSendTime())); 
        
        // 내가 쓴 채팅 읽음 처리
        if(chatRoom.getSenderId().equals(chatRoom.getBuyerId())){
        chatRoomService.updateChatReadBuy(id, 1);}
        else if(chatRoom.getSenderId().equals(chatRoom.getSellerId())) {
        	chatRoomService.updateChatReadSell(id, 1);}
    }

    
    // 알림
    //채팅리스트로 들어갔을 때 읽음처리 
    @ResponseBody
    @RequestMapping("/chatread/chatroom/ajax")
	public void ajaxChatRoomRead(@RequestBody String json) throws IOException {
		JSONObject jsn = new JSONObject(json);
		String idStr = (String) jsn.get("id");
		int id = Integer.parseInt(idStr);
		String flag = (String) jsn.get("flag");
		
		System.out.println(flag);
		if (flag.equals("sell")) {
			chatRoomService.updateChatReadSell(id, 1);
		} else {
			chatRoomService.updateChatReadBuy(id, 1);
		}
		
	}
    
    
    
    //상세페이지로 들어갔을 때 읽음처리
    @ResponseBody
	@RequestMapping(value="/chatread/product/ajax", method=RequestMethod.POST)
	public void ajaxChatProductRead(@RequestBody String json) throws IOException {
		JSONObject jsn = new JSONObject(json);
		String idStr = (String) jsn.get("id");
		int id = Integer.parseInt(idStr);
		chatRoomService.updateChatReadBuy(id, 1);
	}
    
    
	// Header 에 채팅 알림숫자 표시 
	@RequestMapping(value="/chatUnreadAlert/ajax", method=RequestMethod.POST)
	@ResponseBody
	public int chatUnread(@RequestBody String json) {
		
		JSONObject jsn = new JSONObject(json);
		String sessionId = (String) jsn.get("sessionId");
    	int messages = chatRoomService.getUnreadMessages(sessionId);
		return messages;
	}

	// 채팅리스트에 '새 메세지' 표시 
	@RequestMapping(value="/chatUnreadMessageInfo/ajax", method=RequestMethod.POST)
	@ResponseBody
	public String chatListUnread(@RequestBody String json) {
		//ajax가 전송한 String을 key, value로 분류하기 위해 JSON Object convert
		JSONObject jsn = new JSONObject(json);
		//JSON.get([mapped name])으로 value 추출하기
		String sessionid = (String) jsn.get("sessionid");
		//email에 해당되는 모든 chatRoom select 받기
		List<ChatRoom> chatRoomList = chatRoomService.findByUserId(sessionid);
		//chatRoom 정보는 JSON Array에 저장됨
		JSONArray ja = new JSONArray();
		//email에 해당되는 읽지 않은 chatRoom select 받기
		List<Integer> unreadChatId = chatRoomService.getUnreadChatRoom(sessionid);

		 for (ChatRoom chatList : chatRoomList) {
			//chatRoom 정보를 JSON Object에 put 해줌, chatRoom이 반복문에서 넘어갈 때마다 객체 초기화 
			 JSONObject jo = new JSONObject();
			 jo.put("pr_id", chatList.getPost_id());
			 jo.put("buyerId", chatList.getBuyerId());
		 	//리스트에 출력할 상대방 닉네임 확인
		 if (chatList.getBuyerId().equals(sessionid)) {
			 jo.put("senderName", chatList.getSellerId());
		 } else {
			 jo.put("senderName", chatList.getBuyerId());
		 }
		 
		 	 jo.put("pr_title", chatList.getPost_title());
		 //읽지 않은 chatRoom이 0개일때
		 if (unreadChatId.size() == 0) {
			 jo.put("messageUnread", "");
		 	} else {
		 		//읽지 않은 chatRoomId들과 현재 chatRoomId 대조 후 처리 
				 for (int ele : unreadChatId) {
					 	if (chatList.getId() == ele) {
					 		jo.put("messageUnread", "새 메세지");
					 		break;
					 	} else {
					 		jo.put("messageUnread", "");
					 	}
				 }
			}
		 	ja.put(jo);
		}
		 //Javascript에 parsing 할 수 있도록 JSON Object에 Array를 담아줌
		 JSONObject jsnResult = new JSONObject();
		 jsnResult.put("chatList", ja);
		 //String으로 변환해주면 끝, 프런트<->백엔드 전달은 String으로 이루어지며 형식은 JSON을 선택했음 
		 String result = jsnResult.toString();
		 //View로 result를 return해줌
		 return result;
	}

}
