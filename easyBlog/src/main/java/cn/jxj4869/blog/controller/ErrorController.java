package cn.jxj4869.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/console/unLogin")
    public String consoleUnlogin() {
        return "console/unlogin";
    }

}
