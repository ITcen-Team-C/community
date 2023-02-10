package store.itcen.community.postapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import store.itcen.community.postapi.entity.Category;
import store.itcen.community.postapi.entity.PostEntity;

import java.time.LocalDateTime;
import java.util.Optional;
@Getter @ToString
public class PostResponseDTO {

    private String error; //에러발생시 클라이언트에게 전달할 메세지
    private String postId;
    private String title;
    private String contents;

    private Category category;

    private long price;

    private String nickName;

    private String userId;

    @JsonFormat(pattern = "yyyy년 MM월 dd일 a hh시 mm분 ss초")//a 는 am,pm표기
    private LocalDateTime createDate;

    //엔터티를 받아서 DTO로 만들어주는 생성자
    public PostResponseDTO(Optional<PostEntity> postEntity){

        this.postId=postEntity.get().getPostId();
        this.userId=postEntity.get().getUserId();
        this.title=postEntity.get().getTitle();
        this.contents=postEntity.get().getContents();
        this.price=postEntity.get().getPrice();
        this.category=postEntity.get().getCategory();
        this.createDate=postEntity.get().getCreateDate();
        this.nickName=postEntity.get().getNickName();
    }

}
