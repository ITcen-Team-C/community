package store.itcen.community.postapi.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@ToString
public class SearchDTO {
    private String searchTitle;
    private String searchWriter;
    private Long searchPriceMin;
    private Long searchPriceMax;

}
