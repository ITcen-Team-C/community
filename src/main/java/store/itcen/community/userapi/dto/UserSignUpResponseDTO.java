package store.itcen.community.userapi.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import store.itcen.community.userapi.entity.UserEntity;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "email")

// 회원가입 완료후 클라이언트에게 응답할 데이터를 담는 객체
public class UserSignUpResponseDTO {
    private String email;
    private String nickname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinDate;

    // 엔터티를 dto로 변경하는 생성자
    public UserSignUpResponseDTO(UserEntity entity) {
        this.email = entity.getEmail();
        this.nickname = entity.getNickname();
        this.joinDate = entity.getJoinDate();
    }
}