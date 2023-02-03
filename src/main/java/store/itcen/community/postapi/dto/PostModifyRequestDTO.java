package store.itcen.community.postapi.dto;

import lombok.*;
import store.itcen.community.postapi.entity.Category;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostModifyRequestDTO {

    @NotBlank
    private String title;
    private String contents;
    private Long price;

    private Category category;

}
