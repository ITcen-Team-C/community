package store.itcen.community.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.itcen.community.userapi.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);


    // 이메일로 회원 조회
    UserEntity findAllByEmail(String email);

    // 이메일 중복 검사
    boolean existsByEmail(String email);
}
