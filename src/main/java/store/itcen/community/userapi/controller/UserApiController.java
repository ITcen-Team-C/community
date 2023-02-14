package store.itcen.community.userapi.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import store.itcen.community.userapi.dto.LoginRequestDTO;
import store.itcen.community.userapi.dto.LoginResponseDTO;
import store.itcen.community.userapi.dto.UserSignUpDTO;
import store.itcen.community.userapi.dto.UserSignUpResponseDTO;
import store.itcen.community.userapi.exception.DuplicatedEmailException;
import store.itcen.community.userapi.exception.NoRegisteredArgumentsException;
import store.itcen.community.userapi.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserApiController {

    private final UserService userService;

    // 회원가입 요청 처리
    @PostMapping("/signup")
    public String signUp(
             UserSignUpDTO signUpDTO
    ) {
        log.info("/api/auth/signup POST! - {}", signUpDTO);

        try {
            UserSignUpResponseDTO responseDTO
                    = userService.create(signUpDTO);
            return "redirect:/  ";
        } catch (NoRegisteredArgumentsException e) {
            // 예외 상황 2가지 (dto가 null인 문제, 이메일 중복문제)
            log.warn("필수 가입 정보를 다시확인하세요.");
            return "";
        } catch (DuplicatedEmailException e) {
            log.warn("중복되었습니다. 다른 이메일을 작성해주세요.");
            return "";
        }
    }


    @PostMapping("/signin")
    public String signIn(
            LoginRequestDTO requestDTO
    , RedirectAttributes ra, HttpServletResponse response) {

        try {
            LoginResponseDTO userInfo = userService.getByCredentials(
                    requestDTO.getEmail(),
                    requestDTO.getPassword()
            );
//            ra.addFlashAttribute("token", userInfo.getToken());
            Cookie cookie = new Cookie("token", userInfo.getToken());
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
            return "redirect:/index";
        } catch (RuntimeException e) {
            ra.addFlashAttribute("message", "비번 틀림!");
            return "redirect:/";
        }

    }


    @GetMapping("/signout")
    public String signOut(
            HttpServletRequest request, HttpServletResponse response) {
       Cookie tokenCookie = WebUtils.getCookie(request, "token");
       tokenCookie.setPath("/");
       tokenCookie.setMaxAge(0);
       response.addCookie(tokenCookie);
       return "redirect:/index";

    }



}
