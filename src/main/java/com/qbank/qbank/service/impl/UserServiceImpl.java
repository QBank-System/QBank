package com.qbank.qbank.service.impl;

import com.qbank.qbank.dao.impl.UserDaoImpl;
import com.qbank.qbank.dao.inf.IUserDao;
import com.qbank.qbank.dto.MvcDataDto;
import com.qbank.qbank.entity.User;
import com.qbank.qbank.service.inf.IUserService;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author 王宇杰
 * @date 2020/1/11 20:40
 */
public class UserServiceImpl implements IUserService {
    IUserDao userDao = new UserDaoImpl();

    @Override
    public MvcDataDto login(String workNumber, String password) throws Exception {
        //TODO 添加日志控制
        MvcDataDto returnData = new MvcDataDto();

        User user = userDao.getUser(workNumber, IUserDao.CLASS_USERWORKNUMBER);
        if (user != null) {
            //登录成功
            if (user.getUserPassword().equals(DigestUtils.sha1Hex(password))) {
                returnData.setResultCode(MvcDataDto.SUCCESS);
                returnData.setResultMessage("登陆成功");
                returnData.setResultObj(user);
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
        //TODO Register
        return null;
    }

    @Override
    public MvcDataDto uploadCase(String userId, String imgByBase64) throws Exception {
        //TODO UploadCase
        return null;
    }

}
