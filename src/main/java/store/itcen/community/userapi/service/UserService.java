
package store.itcen.community.userapi.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.itcen.community.security.TokenProvider;
import store.itcen.community.userapi.dto.LoginResponseDTO;
import store.itcen.community.userapi.dto.UserSignUpDTO;
import store.itcen.community.userapi.dto.UserSignUpResponseDTO;
import store.itcen.community.userapi.entity.UserEntity;
import store.itcen.community.userapi.exception.DuplicatedEmailException;
import store.itcen.community.userapi.exception.NoRegisteredArgumentsException;
import store.itcen.community.userapi.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    // 회원가입 처리
    public UserSignUpResponseDTO create(final UserSignUpDTO userSignUpDTO) {
        if (userSignUpDTO == null) {
            throw new NoRegisteredArgumentsException("가입정보가 없습니다.");
        }
        final String email = userSignUpDTO.getEmail();
        if (userRepository.existsByEmail(email)) {
            log.warn("Email already exists - {}", email);
            throw new DuplicatedEmailException("중복된 이메일입니다.");
        }
        // 패스워드 인코딩
        String rawPassword = userSignUpDTO.getPassword(); // 평문 암호
        String encodedPassword = passwordEncoder.encode(rawPassword); // 암호화처리
        userSignUpDTO.setPassword(encodedPassword);

        UserEntity savedUser = userRepository.save(userSignUpDTO.toEntity());

        log.info("회원 가입 성공!! - user_id : {}", savedUser.getId());

        return new UserSignUpResponseDTO(savedUser);
    }

    // 이메일 중복확인
    public boolean isDuplicate(String email) {
        if (email == null) {
            throw new RuntimeException("이메일 값이 없습니다.");
        }
        return userRepository.existsByEmail(email);
    }


    // 로그인 검증
    public LoginResponseDTO getByCredentials(
            final String email,
            final String rawPassword) {

        // 입력한 이메일을 통해 회원정보 조회
        UserEntity originalUser = userRepository.findAllByEmail(email);

        if (originalUser == null) {
            throw new RuntimeException("가입된 회원이 아닙니다.");
        }
        // 패스워드 검증 (입력 비번, DB에 저장된 비번)
        if (!passwordEncoder.matches(rawPassword, originalUser.getPassword())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        log.info("{}님 로그인 성공!", originalUser.getNickname());

        // 토큰 발급
        String token = tokenProvider.createToken(originalUser);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(originalUser, token);
        log.info("{}",loginResponseDTO);
        return loginResponseDTO;
    }

    public String getNicknameByUserId(String userId) {
        return userRepository.getNicknameByUserId(userId);
    }



}

