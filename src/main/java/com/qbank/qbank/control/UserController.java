package com.qbank.qbank.control;

import com.alibaba.fastjson.JSONObject;
import com.qbank.qbank.dao.inf.IUserDao;
import com.qbank.qbank.dto.MvcDataDto;
import com.qbank.qbank.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.qbank.qbank.service.impl.UserServiceImpl.getUserService;

/**
 * @author 王宇杰
 * @date 2020/1/17 9:30
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/index")
    public String index() {
        return "error/noJurisdiction";
    }
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/view")
    public String getView(@RequestParam("user_id") String userId, @RequestParam("user_password") String userPassword) throws Exception {
        MvcDataDto data = getUserService().login(userId, userPassword, IUserDao.CLASS_USERID);
        //密码正确
        if (data.getResultCode().equals(MvcDataDto.SUCCESS)) {
            return "userList";
        } else {
            return "error/noJurisdiction";
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object getUserList(@RequestParam("user_count") int userCount, @RequestParam("index") int index) throws Exception {
        return getUserService().getUserList(userCount,index);
    }

    @RequestMapping("/test")
    public String test(){
        return "template";
    }
}
