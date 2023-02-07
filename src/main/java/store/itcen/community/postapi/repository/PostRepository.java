package store.itcen.community.postapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.itcen.community.postapi.dto.SearchDTO;
import store.itcen.community.postapi.entity.PostEntity;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,String> {


    @Query(value = "select * from post where ${searchDTO.searchOption} like concat('%', :#{#searchDTO.searchContent}, '%') order by createdAt desc limit ${searchDTO.searchLimit}, 10", nativeQuery = true)
    List<PostEntity> searchByOption(@Param("searchDTO")SearchDTO searchDTO);

    // 전체조회 페이징
    Page<PostEntity> findAll(Pageable pageable);


    // 제목 검색조회 페이징
    Page<PostEntity> findByTitleContaining(@Param("searchTitle") String searchTitle, Pageable pageable);
    // 작성자 검색조회 페이징
    Page<PostEntity> findByUserIdContaining(@Param("searchKeyword") String searchKeyword, Pageable pageable);








}
