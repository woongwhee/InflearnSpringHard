package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hihi";//hihi.html파일을 찾아가겠다.
    }
    @GetMapping("hello-mvc")
    public String helloMbc(@RequestParam(value="name") String name, Model model){
        model.addAttribute("name",name);
        return "helloTemplates";
    }
    @GetMapping("hello-string")
    @ResponseBody//응답바디부에 직접넣겠다
    public String helloString(@RequestParam("name") String name){
        return "hello"+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }//객체가 Json형식으로 넘어간다!

    static class Hello{
        private String name;
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name=name;
        }
    }

}
