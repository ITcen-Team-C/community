package store.itcen.community.postapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import store.itcen.community.postapi.dto.*;
import store.itcen.community.postapi.service.PostService;

@Controller
@Slf4j
@RequestMapping("/post")
public class PostMvcController {
    PostService postService;

    @Autowired
    public PostMvcController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/write")
    public String writePost() {
        return "post/write";
    }

    @GetMapping("/update/{id}")
    public String updatePost(@PathVariable("id") String postId,
                             Model model) {
        PostResponseDTO responseDTO=postService.detail(postId);
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
    public String detailPost(@PathVariable("id") String postId, Model model) {
        log.info("/community/post/{} get ", postId);
        PostResponseDTO responseDTO = postService.detail(postId);
        model.addAttribute("responseDTO", responseDTO);
        return "post/detailPost";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") String postId, Model model){
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
