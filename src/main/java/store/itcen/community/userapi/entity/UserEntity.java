package store.itcen.community.userapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@Entity
@Table(name = "user")
public class UserEntity extends BaseTimeEntity {

    @Id
    @Column(name = "userId")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id; // 계정명이 아니라 식별코드

    @Column(unique = true)
    private String email; // 이메일


    @Column(nullable = false, length = 30)
    private String name; // 이름(실명)

    private String password; // 비밀번호

    private String nickname; // 별명

    @Column(nullable = false, length = 30)
    private Integer age; // 나이

    @Enumerated(EnumType.STRING)
    private Role role; // 권한 -> USER,ADMIN

    @CreationTimestamp
    private LocalDateTime joinDate;


// 아이디(식별), 이메일, 비밀번호, 이름, 닉네임, 나이
// 비밀번호와 이름, 닉네임과 나이는 변경할 수 있습니다.

//== 정보 수정 ==//
public void updatePassword(PasswordEncoder passwordEncoder, String password){
    this.password = passwordEncoder.encode(password);
}

    public void updateName(String name){
        this.name = name;
    }

    public void updateNickName(String nickname){
        this.nickname = nickname;
    }

    public void updateAge(int age){
        this.age = age;
    }

    //== 패스워드 암호화 ==//
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

}