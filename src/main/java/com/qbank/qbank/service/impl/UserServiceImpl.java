package com.qbank.qbank.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qbank.qbank.dao.inf.IUserDao;
import com.qbank.qbank.dto.MvcDataDto;
import com.qbank.qbank.entity.User;
import com.qbank.qbank.service.inf.IUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.qbank.qbank.dao.impl.UserDaoImpl.getUserDao;

/**
 * @author 王宇杰
 * @date 2020/1/11 20:40
 */
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
                returnData.setResultCode(MvcDataDto.SUCCESS);
                returnData.setResultMessage("登陆成功");
                returnData.setResultObj(user);
            } else if (user.getUserPassword().equals(password)) {
                if (!User.NORMAL.equals(user.getUserGrade())) {
                    //后台登录
                    returnData.setResultCode(MvcDataDto.SUCCESS);
                    returnData.setResultMessage("");
                    returnData.setResultObj(user);
                } else {
                    returnData.setResultCode(MvcDataDto.FAIL);
                    returnData.setResultMessage("没有权限");
                    returnData.setResultObj(null);
                }
            } else {
                //密码错误
                returnData.setResultCode(MvcDataDto.FAIL);
                returnData.setResultMessage("密码错误");
                returnData.setResultObj(null);
            }
        } else {
            //该用户不存在
            returnData.setResultCode(MvcDataDto.FAIL);
            returnData.setResultMessage("用户不存在");
            returnData.setResultObj(null);
        }
        return returnData;
    }

    @Override
    public MvcDataDto register(User user) throws Exception {
        //TODO 添加日志控制
        MvcDataDto returnData = new MvcDataDto();
        User userTemp = getUserDao().getUser(user.getUserWorkNumber(), IUserDao.CLASS_USERWORKNUMBER);
        if (userTemp != null) {
            returnData.setResultCode(MvcDataDto.FAIL);
            returnData.setResultMessage("注册失败,用户学工号已存在");
            returnData.setResultObj(null);
        } else {
            int result = getUserDao().addUser(user);
            if (result == 1) {
                returnData.setResultCode(MvcDataDto.SUCCESS);
                returnData.setResultMessage("注册成功");
                returnData.setResultObj(user);
            } else {
                returnData.setResultCode(MvcDataDto.ERROR);
                returnData.setResultMessage("注册失败，未知错误");
                returnData.setResultObj(null);
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
                data.setResultCode(MvcDataDto.FAIL);
                data.setResultMessage("注册失败,用户学工号已存在");
                data.setResultObj(user);
            } else {
                int result = getUserDao().addUser(user);
                if (result == 1) {
                    data.setResultCode(MvcDataDto.SUCCESS);
                    data.setResultMessage("注册成功");
                    data.setResultObj(user);
                    successCount++;
                } else {
                    data.setResultCode(MvcDataDto.ERROR);
                    data.setResultMessage("注册失败，未知错误");
                    data.setResultObj(user);
                }
            }
            returnDataList[i++] = data;
        }
        if (successCount == list.size()) {
            returnData.setResultCode(MvcDataDto.SUCCESS);
            returnData.setResultMessage("全部注册成功");
            returnData.setResultObj(returnDataList);
        } else if (successCount != 0) {
            returnData.setResultCode(MvcDataDto.OTHER);
            returnData.setResultMessage(successCount + "人注册成功");
            returnData.setResultObj(returnDataList);
        } else {
            returnData.setResultCode(MvcDataDto.FAIL);
            returnData.setResultMessage("全部注册失败");
            returnData.setResultObj(null);
        }
        return returnData;
    }

    @Override
    public MvcDataDto uploadCase(String userId, String imgByBase64) throws Exception {
        //TODO UploadCase
        return null;
    }

    @Override
    public MvcDataDto getUserList(int userCount, int index) throws Exception {
        MvcDataDto returnData = new MvcDataDto();
        JSONObject resultData = new JSONObject();
        List<User> list = getUserDao().getUsers("");
        int pageCount = (list.size() - 1) / COUNT_A_PAGE + 1;
        resultData.put("user_count", list.size());
        resultData.put("page_count", pageCount);
        resultData.put("count_a_page", COUNT_A_PAGE);
        resultData.put("page_number", index);
        for (int i = (index - 1) * COUNT_A_PAGE; (i < (index * COUNT_A_PAGE))&&(i<list.size()); i++) {
            resultData.put("user" + i, list.get(i));
        }
        returnData.setResultCode(MvcDataDto.SUCCESS);
        returnData.setResultMessage("查询成功");
        returnData.setResultObj(resultData);
        return returnData;
    }



}
