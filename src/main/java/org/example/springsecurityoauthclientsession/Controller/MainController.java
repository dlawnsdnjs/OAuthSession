package org.example.springsecurityoauthclientsession.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainP(){
        return "main";
    }
}
