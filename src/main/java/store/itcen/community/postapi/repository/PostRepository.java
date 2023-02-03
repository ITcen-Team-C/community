package store.itcen.community.postapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.itcen.community.postapi.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity,String> {



}
