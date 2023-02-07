package store.itcen.community.postapi.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@ToString
public class SearchDTO {
    private String searchTitle;
    private String searchWriter;
    private int searchPriceMin;
    private int searchPriceMax;

}
