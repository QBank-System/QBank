package com.qbank.qbank.test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangyujie
 */
@Controller
@RequestMapping("/test")
public class Test {

    @RequestMapping("/test")
    public String test() {
        return "template";
    }

}
