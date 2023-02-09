package store.itcen.community.postapi.dto;

import lombok.*;
import store.itcen.community.postapi.entity.Category;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@ToString
public class SearchDTO {
    private String searchTitle;
    private String searchWriter;
    private Long searchPriceMin;
    private Long searchPriceMax;
    private Category searchCategory;

}
