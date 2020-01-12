package com.qbank.qbank.control;

import com.alibaba.fastjson.JSONObject;
import com.qbank.qbank.dto.MvcDataDto;
import com.qbank.qbank.service.impl.UserServiceImpl;
import com.qbank.qbank.utils.DatabaseOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author 王宇杰
 * @date 2020/1/11 19:10
 */
@Controller
@RequestMapping("/Login")
public class LoginControl {

    private UserServiceImpl userService = new UserServiceImpl();
    private static String homeUrl = "http://localhost";

    static {
        Properties properties = new Properties();
        InputStream is = DatabaseOperations.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        homeUrl = properties.getProperty("homeUrl");
    }


    @RequestMapping("/Index")
    public String index() {
        return "login";
    }

    @RequestMapping("/Login")
    @ResponseBody
    public JSONObject login(@RequestParam("work_number") String work_number, @RequestParam("password") String password) throws Exception {
        MvcDataDto data = userService.login(work_number, password);
        if (MvcDataDto.SUCCESS.equals(data.getResultCode())) {
            data.setRedirectUrl(homeUrl + "/Main");
        }
        return data.toJson();
    }

}
