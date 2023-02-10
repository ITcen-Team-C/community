package store.itcen.community.postapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import store.itcen.community.postapi.dto.*;
import store.itcen.community.postapi.service.PostService;
import store.itcen.community.security.TokenProvider;
import store.itcen.community.userapi.entity.UserEntity;
import store.itcen.community.userapi.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/post")
public class PostMvcController {
    PostService postService;
    TokenProvider tokenProvider;
    UserRepository userRepository;

    @Autowired
    public PostMvcController(PostService postService, TokenProvider tokenProvider, UserRepository userRepository) {
        this.postService = postService;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }


    @GetMapping("/write")
    public String writePost(HttpServletRequest request, Model model) {

        Cookie tokenCookie = WebUtils.getCookie(request, "token");
        String token = tokenCookie.getValue();
        String userId = tokenProvider.validateAndGetUserId(token);
        log.info("userID : {}", userId);

        Optional<UserEntity> userDTO = userRepository.findById(userId);
        log.info("usrDTO : {}", userDTO);


        model.addAttribute("userId", userId);
        model.addAttribute("nickname", userDTO.get().getNickname());


//        PostResponseDTO responseDTO=postService.detail(postId)
        return "post/write";
    }

    @GetMapping("/update/{id}")
    public String updatePost(@PathVariable("id") String postId,
                             HttpServletRequest request,
                             Model model) {
//        Cookie tokenCookie = WebUtils.getCookie(request, "token");
//        String token = tokenCookie.getValue();
//        String userId = tokenProvider.validateAndGetUserId(token);
//
        PostResponseDTO responseDTO=postService.detail(postId);
//        log.info("responseDTO :{}",responseDTO );
//        log.info("post의 userid :{}",responseDTO.getUserId() );
//        log.info("token의 userid :{}",userId);


        model.addAttribute("responseDTO",responseDTO);
        return "post/updatePost";

    }

    @GetMapping("/{page}")
    public String allPosts(@PathVariable int page, Model model) {
        // 요청 페이지 Set
        PostPageRequestDTO pageRequestDTO = new PostPageRequestDTO();
        pageRequestDTO.setPage(page);

        PostListResponseDTO responseDTO = postService.getAllList(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);

        return "post/allPost";
    }


    @GetMapping("/detail/{id}")
    public String detailPost(@PathVariable("id") String postId,HttpServletRequest request, Model model) {

        String userId="";
        if (WebUtils.getCookie(request,"token")!=null){
            Cookie tokenCookie = WebUtils.getCookie(request, "token");
            String token = tokenCookie.getValue();
            userId = tokenProvider.validateAndGetUserId(token);
        }


        log.info("/community/post/{} get ", postId);
        PostResponseDTO responseDTO = postService.detail(postId);

        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("userId", userId);
        return "post/detailPost";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") String postId,
                             HttpServletRequest request,
                             Model model){
        log.info("/community/post/delete/{} get ", postId);
        PostListResponseDTO responseDTO=postService.delete(postId);
        model.addAttribute("responseDTO",responseDTO);
//        allPosts(1,model);
        return "post/allPost";
    }


    @GetMapping("/smartSearch/{page}")
    public String smartSearch(SearchDTO searchDTO, @PathVariable int page, Model model) {
        log.info("searchdto - {}", searchDTO);
        log.info("page - {}", page);

        // 요청 페이지 Set
        PostPageRequestDTO pageRequestDTO = new PostPageRequestDTO();
        pageRequestDTO.setPage(page);

        PostListResponseDTO responseDTO = postService.getSearchList(pageRequestDTO, searchDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("searchDTO", searchDTO);

        return "post/searchPost";


    }
}
