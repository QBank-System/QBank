package com.qbank.qbank.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.google.gson.JsonObject;
import com.qbank.qbank.dao.inf.IUserDao;
import com.qbank.qbank.dto.MvcDataDto;
import com.qbank.qbank.entity.User;
import com.qbank.qbank.utils.AliyunUtil;
import com.qbank.qbank.utils.LogUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.expression.Mvc;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

import static com.qbank.qbank.service.impl.UserServiceImpl.getUserService;

/**
 * @author 王宇杰
 * @date 2020/1/17 9:30
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/userManagement")
    public String admin() {
        return "userManagement";
    }

    @RequestMapping("/listView")
    public String getView(@RequestParam("user_id") String userId, @RequestParam("user_password") String userPassword) throws Exception {
        MvcDataDto data = getUserService().login(userId, userPassword, IUserDao.CLASS_USERID);
        //密码正确
        if (data.getCode()==MvcDataDto.SUCCESS) {
            return "userList";
        } else {
            return "error/noJurisdiction";
        }
    }

    @RequestMapping("/registerView")
    public String register(@RequestParam("user_id") String userId, @RequestParam("user_password") String userPassword) throws Exception {
        MvcDataDto data = getUserService().login(userId, userPassword, IUserDao.CLASS_USERID);
        //密码正确
        if (data.getCode()==MvcDataDto.SUCCESS) {
            return "userRegister";
        } else {
            return "error/noJurisdiction";
        }
    }

    @RequestMapping("/getList")
    @ResponseBody
    public Object getUserList(@RequestParam("page") int page, @RequestParam("limit") int limit) throws Exception {
        return getUserService().getUserList(page, limit);
    }

    @RequestMapping("/register")
    @ResponseBody
    public Object register(@RequestParam("user_id") String userId, @RequestParam("user_password") String userPassword,@RequestParam("user_list") Object users) throws Exception {
        JSONArray userList = JSONArray.parseArray(users.toString());
        MvcDataDto data = getUserService().login(userId, userPassword, IUserDao.CLASS_USERID);
        //密码正确
        if (data.getCode()==MvcDataDto.SUCCESS) {
            List<User> list = new LinkedList<>();
            for (int i = 0; i < userList.size(); i++) {
                User user = new User();
                user.setUserName(userList.getJSONObject(i).getString("userName"));
                user.setUserWorkNumber(userList.getJSONObject(i).getString("userWorkNumber"));
                user.setUserPassword(DigestUtils.sha1Hex(userList.getJSONObject(i).getString("userPassword")));
                user.setUserCollege(userList.getJSONObject(i).getString("userCollege"));
                user.setUserPhoneNumber(userList.getJSONObject(i).getString("userPhoneNumber"));
                list.add(user);
            }
            data = getUserService().batchRegister(list);
            data.setUrl("/QBank/user/userManagement");
        } else {
            data = new MvcDataDto();
            data.setCode(MvcDataDto.FAIL);
            data.setMsg("您没有操作权限");
        }
        return data;
    }


    @RequestMapping("/SendVerificationCode")
    @ResponseBody
    public Object sendVerificationCode(@RequestParam("phoneNumber") String phoneNumber) {
        int code = ((int) ((Math.random() * 9 + 1) * 100000));
        MvcDataDto data = new MvcDataDto();
        try {
            AliyunUtil.sendSMS(phoneNumber, code);
            JsonObject object = new JsonObject();
            object.addProperty("phone_number", phoneNumber);
            object.addProperty("verification_code", code);
            data.setCode(MvcDataDto.SUCCESS);
            data.setData(object);
            data.setMsg("发送成功");
        } catch (ClientException e) {
            data.setCode(MvcDataDto.FAIL);
            data.setMsg("发送失败");
            e.printStackTrace();
        }
        return data;
    }


}
