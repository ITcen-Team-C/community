package store.itcen.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

    @GetMapping("/test")
    public String asdf() {
        return "test";
    }
}
