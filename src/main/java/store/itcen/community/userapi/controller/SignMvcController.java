package store.itcen.community.userapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Map;

@Controller
public class SignMvcController
{

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        model.put("time", new Date());
        return "user/home";
    }

    @GetMapping("/index")
    public String index(Map<String, Object> model) {
        model.put("time", new Date());
        return "user/index";
    }

    @GetMapping("/join")
    public String join() {
//        userRepository.save(new UserEntity("uuid", "email2", "password2", "nickname2", LocalDateTime.now()));
        return "user/join";
    }


    @GetMapping("/login")
    public String write() {
//        postRepository.save(new PostEntity("uupostid", "title", "contents", 1000L, LocalDateTime.now(), new UserEntity("uuid2", "email3", "password3", "nickname3", LocalDateTime.now()), "402880ab8615ea15018615ea26a60000"));
        return "user/login";
    }

}
