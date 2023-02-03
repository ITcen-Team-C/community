package store.itcen.community.postapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "todoId")
@Builder

@Entity
@Table(name = "tbl_post")
public class PostEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private BigInteger postId; //게시물 아이디

    private String title; //게시물(질문) 제목

    private String contents; //게시물(질문) 내용

    private BigInteger price; //의뢰 가격

    @CreationTimestamp
    private LocalDateTime createDate; // 질문 등록 시간

    private BigInteger userId;


}
