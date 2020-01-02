package com.qbank.qbank.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangyujie
 */
@Controller
public class Test {

    @RequestMapping("/Index")
    public String index(){
        return "login";
    }

}
