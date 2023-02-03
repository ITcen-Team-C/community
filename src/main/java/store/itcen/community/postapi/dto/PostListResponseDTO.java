package store.itcen.community.postapi.dto;

import java.util.List;

public class PostListResponseDTO {
    private int count;
    // 페이지정보 객체
    private PostPageResponseDTO pageInfo;
    // 데이터 List
    private List<PostResponseDTO> posts;

}
