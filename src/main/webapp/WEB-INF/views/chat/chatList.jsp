<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="user-scalable=no, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, width=device-width">

    <title>채팅 리스트</title>
    <link rel="shortcut icon" type="image/x-icon" href="${path}/pictures/monster.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <link rel="stylesheet" href="${path}/css/chat.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/header_footer.css"/>
    <script src="${path}/js/jquery-3.6.0.min.js"></script>
</head>

<body>
<%@include file="/WEB-INF/views/post/header.jsp"%>

<%--<img class="chatList-BG" src="/pictures/chatListBG.png">--%>


    <div class="main-container">
        <!-- content-section -->
        <div class="content-container">
            
    	   <div class="chat-container">
               <p class="chatTitle"><span class="chatTitle-strong">무</span>엇이든 <span class="chatTitle-strong">물</span>어 <span class="chatTitle-strong">보</span>세요</p>
    		    <div class="chatlist-box">
     	 	    </div>
      	   </div>
        </div>
    </div>
    
        <script>
            let chatList = document.querySelector('.chatlist-box');
            var sessionid = '${sessionid}';
            
            $(document).ready(initialize());

            function initialize() {
                getChatList();
                getUnreadMessageInfo();
                unreadAlertInfinite();
            }
            
            // 채팅 상대방 설정
            function addChatDivImg(i, sellerId, sessionid, post_id, buyerId, post_title, img1) {
            	var opponent = "";
            	if(sessionid==sellerId){
            		opponent = buyerId;
            	}else {
            		opponent = sellerId; 
            	}
                $(chatList).prepend('<div class=chat-list-box chatMessageInfo' + i + '><div class="chatbox wrapPr_img"><img height=100% width=67 class="chatbox-img" src="/pictures/chatimg.png"><a href="/chatRoom/' + post_id + '/' + buyerId  + '"><div class=chat-title>'+ post_title+'</div><div class=chat-name><span style="color:orange; font-size:15px; font-weight : bold;">'+ opponent + '</span>  님 과의 채팅방'+'</div> <div class="wrapSellerTitle' + i +'"></div></div>');
                // $(chatList).prepend('<div class=chat-list-box chatMessageInfo' + i + '><div class="chatbox wrapPr_img"><img height=100% width=67 class="chatbox-img" src="/upload/' + img1 + '"><a href="/chatRoom/' + post_id + '/' + buyerId  + '"><div class=chat-title>'+ post_title+'</div><div class=chat-name><span style="color:orange; font-size:15px; font-weight : bold;">'+ opponent + '</span>  님 과의 채팅방'+'</div> <div class="wrapSellerTitle' + i +'"></div></div>');
            }
    
            //페이지가 로드되는 시점 한 번만 출력하면 되는 div, img를 출력하는 메서드
            // function addChatDivImg(idx, pr_id, buyerId, pr_title, img1) {
            //     $(chatList).append('<div class= chatMessageInfo' + idx + '><div class="wrapPr_img"><a href="http://localhost:8090/chatRoom/' + pr_id + '/' + buyerId + '">' + pr_title + '<br><img height=200 width=200 src="http://localhost:8090/upload/' + img1 + '"></a> <br>' + buyerId + '님 과의 채팅방 입니다.<hr></div><div class="wrapSellerTitle' +
            //         idx +
            //         '"></div></div>');
            // }
    
    
            //async(비동기)로 일정간격 업뎃되지 않아도 되는 img 파일을 불러옴
            function getChatList() {
                console.log("getChatList inprocess");
                $.ajax({
                    url: "/chatList/ajax",
                    type: "POST",
                    data: JSON.stringify({
                        sessionid: sessionid
                    }),
                    contentType: "application/json",
                 
                    success: function (data) {

                        var parsed = JSON.parse(data);
                        var length = parsed.chatList.length;
                        for (var i = 0; i < length; i++) {
                            //채팅방 갯수만큼 반복문을 돌면서 채팅방 틀(div, img 태그)를 만들어줌 
                            addChatDivImg(i, parsed.chatList[i].sellerId, sessionid, parsed.chatList[i].post_id, parsed.chatList[i].buyerId, parsed.chatList[i].post_title);
                        }
                    } // success
                }); //ajax 
            }
            
            
      //[[ 알림 AJAX ]]
            // 채팅리스트 에서 '새 메세지' 표시 
            function getUnreadMessageInfo() {
    
                    $.ajax({
                        url:"/chatUnreadMessageInfo/ajax",
                        type: "POST",
                        data: JSON.stringify({
                            sessionid: sessionid
                        }),
                        contentType: "application/json",
                        success: function(data) {
                            var parsed = JSON.parse(data);
                            var length = parsed.chatList.length;
                            
                            for (var i = 0; i < length; i++) {
                               $('.wrapSellerTitle' + i).html('');
                                addChatList(parsed.chatList[i].post_id, parsed.chatList[i].buyerId, parsed.chatList[i].senderName, parsed.chatList[i].post_title, parsed.chatList[i].messageUnread, i);
                            }
                        } //success
                }); //ajax
            }
            
            
            //1000milliseconds(==1초) 간격으로 getUnreadMessageInfo()를 실행시키는 반복 메서드
            function unreadAlertInfinite() {
                setInterval(() => {
                    getUnreadMessageInfo();                
               }, 1000);
            }
            
            //일정 간격으로 업데이트된 데이터를 화면에 출력하는 메서드  //새 메세지 받는 용도로 사용 
            function addChatList(post_id, buyerId, senderName, post_title, messageUnread, idx) {
    
                var str =
                '<a href="/chatRoom/' + 
                post_id +
                '/' + 
                buyerId + 
                '">' +
              /*   '<h3><span id="sellerName">' + 
                senderName +
                '&nbsp;</span>' +
                '<span id="title">' + 
                pr_title +  </span>*/
                '<span id="message" style="color:red; font-size : 16px; position : absolute; bottom : 10px; right : 10px;" >' + 
                messageUnread +'</span></div></a>';
                
                //HTML화면의 <div class="wrapSellerTitle0,1,...etc"> 하위에 str 변수를 추가해준다.                  
                 $('.wrapSellerTitle' + idx).append(str);
            } 
    
        </script>


    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
        crossorigin="anonymous"></script>

<%@include file="/WEB-INF/views/post/footer.jsp"%>
</body>

</html>