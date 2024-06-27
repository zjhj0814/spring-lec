package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //localhost 8080으로 들어오면 이것이 호출됨
    public String home(){
        return "home"; //home.html 호출
    }
    //컨트롤러를 먼저 찾고 template에서 찾기 때문에 기존에 만든 index.html은 무시됨
}
