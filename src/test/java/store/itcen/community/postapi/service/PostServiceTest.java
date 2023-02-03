package store.itcen.community.postapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import store.itcen.community.postapi.dto.PostCreateRequestDTO;
import store.itcen.community.postapi.dto.PostModifyRequestDTO;
import store.itcen.community.postapi.dto.PostResponseDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Commit
class PostServiceTest {

    @Autowired
    PostService postService;

    @BeforeEach
    void beforeInsert(){
        PostCreateRequestDTO dto1=PostCreateRequestDTO.builder()
                .title("질문 도와주세요")
                .contents("자바 프로그래밍질문이요")
                .price(20000L)
                .build();

        PostCreateRequestDTO dto2=PostCreateRequestDTO.builder()
                .title("help")
                .contents("파이썬 프로그래밍질문이요")
                .price(50000L)
                .build();

        PostCreateRequestDTO dto13=PostCreateRequestDTO.builder()
                .title("리액트 질문 도와주세요")
                .contents("jsp랑 헷갈려요")
                .price(10000L)
                .build();
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