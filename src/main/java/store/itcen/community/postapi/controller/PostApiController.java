package store.itcen.community.postapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.itcen.community.postapi.dto.*;
import store.itcen.community.postapi.service.PostService;

import javax.servlet.http.HttpServletRequest;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/community/post")
public class PostApiController {

    private final PostService postService;

    // 전체 게시글 조회 ( 페이징 ) // 사용안함 => MVC Controller
    @GetMapping("/allpost")
    public ResponseEntity<?> allPosts(@RequestParam int page, Model model) {
        // 요청 페이지 Set
        PostPageRequestDTO pageRequestDTO = new PostPageRequestDTO();
        pageRequestDTO.setPage(page);

        PostListResponseDTO responseDTO = postService.getAllList(pageRequestDTO);
        return ResponseEntity
                .ok()
                .body(responseDTO);
    }


    //글 조회 요청
    @GetMapping("/{id}")
    public ResponseEntity<?> detailPost(
            @PathVariable("id") String postId){
        log.info("/community/post/{} get ",postId);
        PostResponseDTO responseDTO=postService.detail(postId);
        return ResponseEntity.ok().body(responseDTO);
    }



    //글 등록 요청
    @PostMapping
    public ResponseEntity<?> createPost(
            @AuthenticationPrincipal String userId,
            @Validated @RequestBody PostCreateRequestDTO requestDTO,
            BindingResult result
    ){
        if(result.hasErrors()) {
            log.warn("DTO 검증 에러 발생 : {}", result.getFieldError());
            return org.springframework.http.ResponseEntity
                  .badRequest()
                  .body(result.getFieldError());
      }
        try {
            PostResponseDTO responseDTO=postService.create(requestDTO,"402880d08624b21b018624b2cc790000");
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        }catch (RuntimeException e){
            log.error(e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
      }

    }




    //글 수정 요청
    @RequestMapping(
            value = "/{id}"
            ,method = {RequestMethod.PATCH,RequestMethod.PUT}
    )

    public ResponseEntity<?> updatePost(
            @AuthenticationPrincipal String userId //userid 연결 후 입력 필요
            , @PathVariable("id") String postId
            , @Validated @RequestBody PostModifyRequestDTO requestDTO
            , BindingResult result
            , HttpServletRequest request
            ){
        if(result.hasErrors()){
            return ResponseEntity.badRequest()
                    .body(result.getFieldError());
        }

        log.info("/community/post/{} {} request",postId,request.getMethod());
        log.info("modifying dto : {}",requestDTO);

        try{
            PostResponseDTO responseDTO = postService.update(postId,requestDTO);
            return ResponseEntity.ok().body(responseDTO);
        }catch (Exception e){
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }

    }


    //글 삭제 요청
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(
            @AuthenticationPrincipal String userId,
            @PathVariable("id") String postId
    ){
        log.info("/api/post/{} Delete request!",postId);

        if(postId==null||postId.trim().equals("")){
            return ResponseEntity
                    .badRequest()
                    .body(PostListResponseDTO.builder().error("ID를 전달해주세요"));
        }
        try{
            PostListResponseDTO listResponseDTO=postService.delete(postId);
            return ResponseEntity.ok().body(listResponseDTO);
        }catch (Exception e){
            return ResponseEntity.internalServerError()
                    .body(PostListResponseDTO.builder().error(e.getMessage()));
        }

    }



}
