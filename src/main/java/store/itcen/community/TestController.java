package store.itcen.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import store.itcen.community.postapi.entity.PostEntity;
import store.itcen.community.postapi.repository.PostRepository;
import store.itcen.community.userapi.entity.UserEntity;
import store.itcen.community.userapi.repository.UserRepository;

import java.time.LocalDateTime;

@Controller
public class TestController {


    UserRepository userRepository;
    PostRepository postRepository;

    @Autowired
    public TestController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    @GetMapping("/test")
    public String tester() {
        return "test";
    }


    @GetMapping("join")
    public String join() {
        userRepository.save(new UserEntity("uuid", "email2", "password2", "nickname2", LocalDateTime.now()));
        return "redirect:/test";
    }

    @GetMapping("write")
    public String write() {
        postRepository.save(new PostEntity("uupostid", "title", "contents", 1000L, LocalDateTime.now(), new UserEntity("uuid2", "email3", "password3", "nickname3", LocalDateTime.now()), "402880ab8615ea15018615ea26a60000"));
        return "redirect:/test";
    }


}