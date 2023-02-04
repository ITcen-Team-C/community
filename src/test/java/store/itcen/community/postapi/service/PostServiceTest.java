package store.itcen.community.postapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import store.itcen.community.postapi.dto.*;
import store.itcen.community.postapi.entity.Category;
import store.itcen.community.postapi.entity.PostEntity;
import store.itcen.community.postapi.repository.PostRepository;
import store.itcen.community.userapi.entity.UserEntity;
import store.itcen.community.userapi.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Commit
class PostServiceTest {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    @Transactional
    @Rollback
    void beforeInsert(){
        UserEntity user1 = UserEntity.builder()
                .id("uuid")
                .email("tester@email.com")
                .nickname("테스터닉")
                .password("임시비번")
                .build();

        UserEntity dummyUser1 = userRepository.save(user1);

        PostCreateRequestDTO dto1=PostCreateRequestDTO.builder()
                .title("질문 도와주세요")
                .contents("자바 프로그래밍질문이요")
                .price(20000L)
                .category(Category.BACKEND)
                .build();

        PostCreateRequestDTO dto2=PostCreateRequestDTO.builder()
                .title("help")
                .contents("파이썬 프로그래밍질문이요")
                .price(50000L)
                .category(Category.FRONTEND)
                .build();

        PostCreateRequestDTO dto3=PostCreateRequestDTO.builder()
                .title("리액트 질문 도와주세요")
                .contents("jsp랑 헷갈려요")
                .category(Category.DATABASE)
                .price(10000L)
                .build();

        PostCreateRequestDTO dto4=PostCreateRequestDTO.builder()
                .title("이것좀")
                .contents("백엔드 질문")
                .price(20000L)
                .category(Category.BACKEND)
                .build();

        PostCreateRequestDTO dto5=PostCreateRequestDTO.builder()
                .title("도와줘")
                .contents("자바 프로그래밍질문이요")
                .price(50000L)
                .category(Category.BACKEND)
                .build();

        PostCreateRequestDTO dto6=PostCreateRequestDTO.builder()
                .title("DB 질문")
                .contents("mysql 디비 질문")
                .category(Category.DATABASE)
                .price(10000L)
                .build();

        PostCreateRequestDTO dto7=PostCreateRequestDTO.builder()
                .title("DB 질문2")
                .contents("mysql 디비 질문2")
                .category(Category.DATABASE)
                .price(10000L)
                .build();



        PostEntity post1 = dto1.toEntity();
        post1.setUserId(dummyUser1.getId());
        PostEntity savedPost = postRepository.save(post1);

        PostEntity post2 = dto1.toEntity();
        post2.setUserId(dummyUser1.getId());
        postRepository.save(post2);

        PostEntity post3 = dto1.toEntity();
        post3.setUserId(dummyUser1.getId());
        postRepository.save(post3);

        PostEntity post4 = dto1.toEntity();
        post4.setUserId(dummyUser1.getId());
        postRepository.save(post4);

        PostEntity post5 = dto1.toEntity();
        post5.setUserId(dummyUser1.getId());
        postRepository.save(post5);

        PostEntity post6 = dto1.toEntity();
        post6.setUserId(dummyUser1.getId());
        postRepository.save(post6);

        PostEntity post7 = dto1.toEntity();
        post7.setUserId(dummyUser1.getId());
        postRepository.save(post7);
    }



    @Test
    @DisplayName("dummy게시물 insert 확인용")
    @Transactional
    @Rollback
    void dummyInsertTset() {
        //given
        //Before each 7 dummies

        //when
        List<PostEntity> all = postRepository.findAll();

        //then
        assertEquals(7, all.size());
    }

    @Test
    @DisplayName("페이징 전체조회 시, PostListResponseDTO 에서 PostPageResponseDTO totalCount 정보 확인")
    @Transactional
    @Rollback
    void checkPagingTest() {
        //given
        //7dummies
        PostPageRequestDTO postPageRequestDTO = new PostPageRequestDTO(); //1페이지, 개당 3 기본 값

        //when
        PostListResponseDTO listResponseDTO = postService.getAllList(postPageRequestDTO);
        PostPageResponseDTO pageInfo = listResponseDTO.getPageInfo();

        //then
        assertEquals(7, pageInfo.getTotalCount());
    }


    @Test
    @DisplayName("페이징 전체조회 시, 페이지 요청이 2번째 페이지로 왔을 때 current 페이지, end 페이지 확인 ")
    @Transactional
    @Rollback
    void requestPagingNum2Tset() {
        //given
        //7dummies //page per 3
        PostPageRequestDTO postPageRequestDTO = new PostPageRequestDTO(); //1페이지, 개당 3 기본 값
        postPageRequestDTO.setPage(2);

        //when
        PostListResponseDTO listResponseDTO = postService.getAllList(postPageRequestDTO);
        PostPageResponseDTO pageInfo = listResponseDTO.getPageInfo();

        //then
        assertEquals(2, pageInfo.getCurrentPage());
        assertEquals(1, pageInfo.getStartPage());
        assertEquals(3, pageInfo.getEndPage());
    }





    @Test
    @DisplayName("2번째 할일의 제목을 수정수정으로 수정하고 할일 완료 처리를 해야한다.")
    void updateTest(){
        //given
        String newTitle ="수정수정";
        Long newPrice=80000L;

        PostModifyRequestDTO modifyRequestDTO
                = PostModifyRequestDTO.builder()
                .title(newTitle)
                .price(newPrice)
                .build();

        //when
//        PostResponseDTO targetPost
//                = postService.retrieve().getPosts().get(1);

//        TodoListResponseDTO responseDTO = todoService.update(targetTodo.getId(), modifyRequestDTO);

        //then
//        assertEquals("수정수정",responseDTO.getTodos().get(1).getTitle());
//        assertTrue(responseDTO.getTodos().get(1).isDone());
//
//        System.out.println("===============================");
//        responseDTO.getTodos().forEach(System.out::println);
    }

}