package store.itcen.community.postapi.dto;

import lombok.*;
import store.itcen.community.postapi.entity.PostEntity;

import javax.validation.constraints.NotBlank;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateRequestDTO {


    @NotBlank
    private String title;
    private String contents;
    private Long price;

    public PostEntity toEntity(){
        return PostEntity.builder()
                .title(this.title)
                .contents(this.contents)
                .build();}

}
