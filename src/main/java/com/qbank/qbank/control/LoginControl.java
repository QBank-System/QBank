package com.qbank.qbank.control;

import com.alibaba.fastjson.JSONObject;
import com.qbank.qbank.dto.MvcDataDto;
import com.qbank.qbank.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 王宇杰
 * @date 2020/1/11 19:10
 */
@Controller
@RequestMapping("/Login")
public class LoginControl {

    private UserServiceImpl userService = new UserServiceImpl();

    @RequestMapping("/Index")
    public String index() {
        return "login";
    }

    @RequestMapping("/Login")
    @ResponseBody
    public JSONObject login(@RequestParam("work_number") String work_number, @RequestParam("password") String password) throws Exception {
        MvcDataDto data = userService.login(work_number, password);
        if(MvcDataDto.SUCCESS.equals(data.getResultCode())){
            data.setRedirectUrl("/Main");
        }
        return data.toJson();
    }

}
