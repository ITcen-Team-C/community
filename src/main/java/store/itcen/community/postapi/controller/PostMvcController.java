package store.itcen.community.postapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import store.itcen.community.postapi.dto.PostListResponseDTO;
import store.itcen.community.postapi.dto.PostPageRequestDTO;
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


    @GetMapping("/{page}")
    public String allPosts(@PathVariable int page, Model model) {
        // 요청 페이지 Set
        PostPageRequestDTO pageRequestDTO = new PostPageRequestDTO();
        pageRequestDTO.setPage(page);

        PostListResponseDTO responseDTO = postService.getAllList(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);

        return "allBoard";
    }








}
