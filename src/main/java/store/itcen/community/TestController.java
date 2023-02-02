package store.itcen.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {
    @GetMapping("/test")
    public String tester() {
        return "test";
    }

}
