package store.itcen.community.postapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import store.itcen.community.userapi.entity.UserEntity;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "postId")
@Builder

@Entity
@Table(name = "post")
public class PostEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String postId; //게시물 아이디

    private String title; //게시물(질문) 제목

    private String contents; //게시물(질문) 내용

    private Long price; //의뢰 가격

    @CreationTimestamp
    private LocalDateTime createDate; // 질문 등록 시간

    //user와 관계 설정
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    private UserEntity user;
    //userEntity 생성 후 다시 수정


    private String userId; //게시물 추가, 수정 시 사용할 외래키


}
