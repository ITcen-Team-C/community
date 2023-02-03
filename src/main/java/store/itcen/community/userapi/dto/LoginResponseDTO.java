package store.itcen.community.userapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import store.itcen.community.userapi.entity.UserEntity;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class LoginResponseDTO {

    private String email;
    private String nickname;
    @JsonFormat(pattern = "yyyy년 MM월 dd일")
    private LocalDate joinDate;

    private String token; // 인증 토큰

    private String message; // 응답 메시지

    // 엔터티를 DTO로 변경
    public LoginResponseDTO(UserEntity userEntity, String token) {
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.joinDate = LocalDate.from(userEntity.getJoinDate());
        this.token = token;
    }
}