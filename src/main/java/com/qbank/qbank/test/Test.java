package com.qbank.qbank.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
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

    public static void main(String[] args) {
        System.out.println(DigestUtils.sha1Hex("05156420"));
    }

    public static double quality(double diameter) {
        return 4.0 / 3 * Math.PI * diameter *diameter*diameter;
    }

}
