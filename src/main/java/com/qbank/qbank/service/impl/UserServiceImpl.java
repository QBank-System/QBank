package com.qbank.qbank.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.internal.$Gson$Types;
import com.qbank.qbank.dao.inf.IUserDao;
import com.qbank.qbank.dto.MvcDataDto;
import com.qbank.qbank.entity.User;
import com.qbank.qbank.service.inf.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.qbank.qbank.dao.impl.UserDaoImpl.getUserDao;

/**
 * @author 王宇杰
 * @date 2020/1/11 20:40
 */
@Service
public class UserServiceImpl implements IUserService {
    private static final int COUNT_A_PAGE = 10;
    private static UserServiceImpl userService;

    public static UserServiceImpl getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public MvcDataDto login(String workNumber, String password, int indexClass) throws Exception {
        //TODO 添加日志控制
        MvcDataDto returnData = new MvcDataDto();

        User user = getUserDao().getUser(workNumber, indexClass);
        if (user != null) {
            //登录成功
            if (user.getUserPassword().equals(DigestUtils.sha1Hex(password))) {
                returnData.setCode(MvcDataDto.SUCCESS);
                returnData.setMsg("登陆成功");
                returnData.setData(user);
            } else if (user.getUserPassword().equals(password)) {
                if (!User.NORMAL.equals(user.getUserGrade())) {
                    //后台登录
                    returnData.setCode(MvcDataDto.SUCCESS);
                    returnData.setMsg("");
                    returnData.setData(user);
                } else {
                    returnData.setCode(MvcDataDto.FAIL);
                    returnData.setMsg("没有权限");
                    returnData.setData(null);
                }
            } else {
                //密码错误
                returnData.setCode(MvcDataDto.FAIL);
                returnData.setMsg("密码错误");
                returnData.setData(null);
            }
        } else {
            //该用户不存在
            returnData.setCode(MvcDataDto.FAIL);
            returnData.setMsg("用户不存在");
            returnData.setData(null);
        }
        return returnData;
    }

    @Override
    public MvcDataDto register(User user) throws Exception {
        //TODO 添加日志控制
        MvcDataDto returnData = new MvcDataDto();
        User userTemp = getUserDao().getUser(user.getUserWorkNumber(), IUserDao.CLASS_USERWORKNUMBER);
        if (userTemp != null) {
            returnData.setCode(MvcDataDto.FAIL);
            returnData.setMsg("注册失败,用户学工号已存在");
            returnData.setData(null);
        } else {
            int result = getUserDao().addUser(user);
            if (result == 1) {
                returnData.setCode(MvcDataDto.SUCCESS);
                returnData.setMsg("注册成功");
                returnData.setData(user);
            } else {
                returnData.setCode(MvcDataDto.ERROR);
                returnData.setMsg("注册失败，未知错误");
                returnData.setData(null);
            }
        }
        return returnData;
    }

    @Override
    public MvcDataDto batchRegister(List<User> list) throws Exception {
        //TODO 添加日志控制
        MvcDataDto returnData = new MvcDataDto();
        MvcDataDto[] returnDataList = new MvcDataDto[list.size()];
        int i = 0;
        int successCount = 0;
        for (User user : list) {
            MvcDataDto data = new MvcDataDto();
            User userTemp = getUserDao().getUser(user.getUserWorkNumber(), IUserDao.CLASS_USERWORKNUMBER);
            if (userTemp != null) {
                data.setCode(MvcDataDto.FAIL);
                data.setMsg("注册失败,用户学工号已存在");
            } else {
                int result = getUserDao().addUser(user);
                if (result == 1) {
                    data.setCode(MvcDataDto.SUCCESS);
                    data.setMsg("注册成功");
                    successCount++;
                } else {
                    data.setCode(MvcDataDto.ERROR);
                    data.setMsg("注册失败，未知错误");
                }
            }
            returnDataList[i++] = data;
        }
        if (successCount == list.size()) {
            returnData.setCode(MvcDataDto.SUCCESS);
            returnData.setMsg("全部注册成功");
            returnData.setData(returnDataList);
        } else if (successCount != 0) {
            returnData.setCode(MvcDataDto.OTHER);
            returnData.setMsg(successCount + "人注册成功");
            returnData.setData(returnDataList);
        } else {
            returnData.setCode(MvcDataDto.FAIL);
            returnData.setMsg("全部注册失败");
            returnData.setData(null);
        }
        return returnData;
    }

    @Override
    public MvcDataDto uploadCase(String userId, String imgByBase64) throws Exception {
        //TODO UploadCase
        return null;
    }

    @Override
    public MvcDataDto getUserList(int page, int limit) throws Exception {
        MvcDataDto returnData = new MvcDataDto();
        JSONArray result = new JSONArray();
        List<User> list = getUserDao().getUsers("");
        for (int i = (page - 1) * limit; (i < (page * limit)) && (i < list.size()); i++) {
            result.add(list.get(i));
        }
        returnData.setCode(MvcDataDto.SUCCESS);
        returnData.setCount(list.size());
        returnData.setData(result);
        return returnData;
    }

    @Override
    public MvcDataDto getAllUserList() throws Exception {
        MvcDataDto returnData = new MvcDataDto();
        List<User> list = getUserDao().getUsers("");
        returnData.setCode(MvcDataDto.SUCCESS);
        returnData.setCount(list.size());
        returnData.setData(list);
        return returnData;
    }

}
