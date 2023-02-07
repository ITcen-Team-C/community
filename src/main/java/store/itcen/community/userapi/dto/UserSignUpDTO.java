package store.itcen.community.userapi.dto;

import lombok.*;
import store.itcen.community.userapi.entity.UserEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "email")
@Builder

// 회원가입시에 클라이언트가 보낸 데이터를 담는 객체
public class UserSignUpDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
//    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
//    @Size(min = 2, max = 5)
    private String nickname;

    private Integer age;

    private String name; // 실명

    // 엔터티로 변경하는 메서드
    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .age(this.age)
                .name(this.name)
                .build();
    }
}
