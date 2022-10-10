package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    //wellcomefile보다 우선순위가 읽게 설정된다.
    public String home(){
        return "home";
    }

}
