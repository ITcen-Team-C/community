package store.itcen.community.postapi.dto;

import lombok.*;
import store.itcen.community.postapi.entity.Category;
import store.itcen.community.postapi.entity.PostEntity;
import store.itcen.community.userapi.entity.UserEntity;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateRequestDTO {


    @NotBlank
    private String title;
    private String contents;
    private Long price;
    private Category category;

    private String userId;

    private String nickName; //사용자 닉네임

    public PostEntity toEntity(){
        return PostEntity.builder()
                .title(this.title)
                .contents(this.contents)
                .category(this.category)
                .price(this.price)
                .userId(this.userId)
                .nickName(this.nickName)
                .build();
    }

}
