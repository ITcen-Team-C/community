package store.itcen.community.postapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import store.itcen.community.postapi.dto.*;
import store.itcen.community.postapi.entity.PostEntity;
import store.itcen.community.postapi.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    // 전체 조회 페이징
    public PostListResponseDTO getAllList(PostPageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1, pageRequestDTO.getSizePerPage(),
                Sort.Direction.DESC, "createDate"
        );

        final Page<PostEntity> pageData = postRepository.findAll(pageable);
        List<PostEntity> allPosts = pageData.getContent();

//        if (allPosts.isEmpty()) {
//            throw new RuntimeException("조회 결과 Empty!");
//        }

        // Entity => DTO ( 필요한 정보만 클라이언트에 필요한 꼴로 보내줌 )
        List<PostResponseDTO> responseDTOList = allPosts.stream()
                .map(et -> new PostResponseDTO(Optional.ofNullable(et)))
                .collect(Collectors.toList());


        PostListResponseDTO listResponseDTO = PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .pageInfo(new PostPageResponseDTO<PostEntity>(pageData))
                .posts(responseDTOList)
                .build();

        return listResponseDTO;
    }




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
            final PostModifyRequestDTO modifyRequestDTO
            //,final String userId
    ){
        Optional<PostEntity> targetEntity = postRepository.findById(postId);

        targetEntity.ifPresent(postEntity -> {
            postEntity.setTitle(modifyRequestDTO.getTitle());
            postEntity.setContents(modifyRequestDTO.getContents());
            postEntity.setCategory(modifyRequestDTO.getCategory());
            postEntity.setPrice(modifyRequestDTO.getPrice());


            postRepository.save(postEntity);
        });

//        //수정하고 나면 수정 후 글 조회
        return detail(postId);
    }


    //게시글삭제
    public PostListResponseDTO delete(final String postId){

        try{
            postRepository.deleteById(postId);
        } catch (Exception e) {
            log.error("id가 존재하지 않아 삭제에 실패했습니다. - ID: {}, err: {}"
                    , postId, e.getMessage());
            throw new RuntimeException("post id 가 존재하지 않아 삭제에 실패했습니다.");
        }

        return getAllList(new PostPageRequestDTO());
    }


    public PostListResponseDTO getSearchList(PostPageRequestDTO pageRequestDTO, SearchDTO searchDTO) {
        // 페이징 set
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1, pageRequestDTO.getSizePerPage(),
                Sort.Direction.DESC, "createDate"
        );

        // 검색 minmaxPrice => null 시 0 - 100000000set ( 전체조회 )
        boolean flag = false;
        if (searchDTO.getSearchPriceMax() == null && searchDTO.getSearchPriceMin() == null) {
            flag = true;
        }

        if (searchDTO.getSearchPriceMin() == null) {
            searchDTO.setSearchPriceMin(0L);
        }
        if (searchDTO.getSearchPriceMax() == null) {
            searchDTO.setSearchPriceMax(1000000000L);
        }

        // min max 크기 validate 필요

        // searchTitle 검색
//        final Page<PostEntity> pageData = postRepository.findByTitleContaining(searchDTO.getSearchTitle(),pageable);

        // searchTitle and searchWriter 검색
        final Page<PostEntity> pageData;
        if (flag) {
            pageData = postRepository.findByTitleContainingAndNickNameContaining(searchDTO.getSearchTitle(), searchDTO.getSearchWriter(), pageable);
        } else {
            log.info("flag - 금액까지적용쿼리 발동");
            pageData = postRepository.findByTitleContainingAndNickNameContainingAndPriceBetween(searchDTO.getSearchTitle(), searchDTO.getSearchWriter(), searchDTO.getSearchPriceMin(), searchDTO.getSearchPriceMax(), pageable);

//            List<PostEntity> collect = pageData.getContent().stream()
//                    .filter(postEntity -> postEntity.getPrice() > searchDTO.getSearchPriceMin() && postEntity.getPrice() < searchDTO.getSearchPriceMax())
//                    .collect(Collectors.toList());

        }


        List<PostEntity> allPosts = pageData.getContent();

//        if (allPosts.isEmpty()) {
//            throw new RuntimeException("조회 결과 Empty!");
//        }

        // Entity => DTO ( 필요한 정보만 클라이언트에 필요한 꼴로 보내줌 )
        List<PostResponseDTO> responseDTOList = allPosts.stream()
                .map(et -> new PostResponseDTO(Optional.ofNullable(et)))
                .collect(Collectors.toList());


        PostListResponseDTO listResponseDTO = PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .pageInfo(new PostPageResponseDTO<PostEntity>(pageData))
                .posts(responseDTOList)
                .build();

        return listResponseDTO;
    }




}
