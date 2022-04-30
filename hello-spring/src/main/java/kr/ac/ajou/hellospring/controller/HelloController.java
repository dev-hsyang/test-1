package kr.ac.ajou.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;
//test test test

@Controller
public class HelloController {

    @GetMapping("hello") // web application 에서 /hello라고 호출이 되면 이 메서드가 호출된다.
    public String hello(Model model){
        model.addAttribute("data", "Spring!!");
        return "hello"; // resources 에 있는 template 의 hello.html를 찾아서 렌더링하게 한다.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){ //RequestParam으로 "name"에다가 받아온 값을 String name에 할당
        model.addAttribute("name", name); //name 에는 동적으로 받아온 String name이 할당된다.
        return "hello-template";
    }







    @GetMapping("hello-string")
    @ResponseBody //http의 body에다가 직접 데이터를 넣어주겠다는 annotation
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // html 없이, view 필요 없이 이 data가 http의 body에 직접 올라간다.
                                // @ResponseBody 에서 문자열이 반환이 되면 기본적으로 String Convertor 가 문자열을 띄운다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){ // json 방식, key : value 방식으로 이루어진 구조를 json 이라고 한다
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // @ResponseBody 에서 객체가 반환이 되면 json convertor 가 객체를 json 스타일로 바꾼다.
    }

    static class Hello{ // json 에서 사용할 클래스
        private String name; // json 의 key 는 name, value 는 이름.

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }

    @GetMapping("date")
    public ModelAndView date(){
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedData = dateFormat.format(date);

        ModelAndView mv = new ModelAndView("date");
        mv.addObject("serverTime", formattedData);
        return mv;
    }

}
