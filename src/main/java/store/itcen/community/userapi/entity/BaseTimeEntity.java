package store.itcen.community.userapi.entity;


// 페이지 생성시간과 수정시간 등록
// 생성시간과 수정시간은 모든 엔티티에서 사용할 것이므로 @MappedSupperclass를 사용하여
// 여러 엔티티가 상속받을 수 있도록 만들어주겠습니다.

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(updatable = true)
    private LocalDateTime lastModifiedDate;
}
