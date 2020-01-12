package com.qbank.qbank.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 王宇杰
 * @date 2020/1/11 19:40
 */
@Controller
public class MainControl {

    @RequestMapping("")
    public String index() {
        return new LoginControl().index();
    }

    @RequestMapping("/Main")
    public String main() {
        return "main";
    }

//    @RequestMapping("/404")
//    public String No() {
//        return "404";
//    }

}