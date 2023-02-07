package store.itcen.community.userapi.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.Map;

public class signcontroller
{

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        model.put("time", new Date());
        return "user/index";
    }

}
