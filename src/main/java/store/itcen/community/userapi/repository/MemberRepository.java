package store.itcen.community.userapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.itcen.community.userapi.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    boolean existsByUsername(String username);
}