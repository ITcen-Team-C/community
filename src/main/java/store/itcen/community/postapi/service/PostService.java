package store.itcen.community.postapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import store.itcen.community.postapi.dto.*;
import store.itcen.community.postapi.entity.PostEntity;
import store.itcen.community.postapi.repository.PostRepository;

import java.math.BigInteger;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    //게시글 detail 조회
    public PostResponseDTO detail(String postId){
        Optional<PostEntity> postEntity = postRepository.findById(postId);

        return new PostResponseDTO(postEntity);

    }


    //게시글 등록
    public PostResponseDTO create(
            final PostCreateRequestDTO createRequestDTO,
            final String userId
    )
            throws RuntimeException
    {
        PostEntity post=createRequestDTO.toEntity();
        post.setUserId(userId);
        String postId=post.getPostId();

        postRepository.save(post);
        log.info("게시글 등록. 제목 : {}, 내용 :{}, 가격 :{}",createRequestDTO.getTitle(),createRequestDTO.getContents(),createRequestDTO.getPrice());

        //추가하고 나면 목록 조회불러오기
        return detail(postId);
    }




//    //게시글 수정
    public PostResponseDTO update(
            final String postId,
            final PostModifyRequestDTO modifyRequestDTO,
            final String userId
    ){
        Optional<PostEntity> targetEntity = postRepository.findById(postId);

        targetEntity.ifPresent(postEntity -> {
            postEntity.setTitle(modifyRequestDTO.getTitle());
            postEntity.setContents(modifyRequestDTO.getContents());
            postEntity.setPrice(modifyRequestDTO.getPrice());
        });

//        //수정하고 나면 수정 후 글 조회
        return detail(postId);
    }


    //게시글삭제
    public PostResponseDTO delete(final String postId,final String userId){
        try{
            postRepository.deleteById(postId);
        } catch (Exception e) {
            log.error("id가 존재하지 않아 삭제에 실패했습니다. - ID: {}, err: {}"
                    , postId, e.getMessage());
            throw new RuntimeException("post id 가 존재하지 않아 삭제에 실패했습니다.");
        }

        return retrieve(userId);
    }



}
