package store.itcen.community.postapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter @Setter @ToString
public class PostListResponseDTO {
    private int count;
    // 페이지정보 객체
    private PostPageResponseDTO pageInfo;
    // 데이터 List
    private List<PostResponseDTO> posts;

}
