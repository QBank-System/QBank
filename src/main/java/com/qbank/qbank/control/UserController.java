package com.qbank.qbank.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qbank.qbank.dao.inf.IUserDao;
import com.qbank.qbank.dto.MvcDataDto;
import com.qbank.qbank.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @RequestMapping("/userAdmin")
    public String admin() {
        return "userAdmin";
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

    @RequestMapping("/register")
    public String register(@RequestParam("user_id") String userId, @RequestParam("user_password") String userPassword) throws Exception {
        MvcDataDto data = getUserService().login(userId, userPassword, IUserDao.CLASS_USERID);
        //密码正确
        if (data.getResultCode().equals(MvcDataDto.SUCCESS)) {
            return "userRegister";
        } else {
            return "error/noJurisdiction";
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object getUserList(@RequestParam("page") int page, @RequestParam("limit") int limit) throws Exception {
        MvcDataDto serviceData = getUserService().getUserList(page, limit);
        JSONObject returnData = new JSONObject();
        if(MvcDataDto.SUCCESS.equals(serviceData.getResultCode())){
            JSONArray objects = (JSONArray)serviceData.getResultObj();
            returnData.put("code",0);
            returnData.put("msg","");
            returnData.put("count",Integer.parseInt(serviceData.getResultMessage()));
            returnData.put("data",objects);
        }
        return returnData;
    }

    @RequestMapping("/test")
    public String test() {
        return "template";
    }
}
