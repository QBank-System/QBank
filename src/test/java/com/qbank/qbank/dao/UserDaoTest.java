package com.qbank.qbank.dao;

import com.qbank.qbank.dao.impl.UserDaoImpl;
import com.qbank.qbank.entity.User;
import com.qbank.qbank.utils.DatabaseOperations;
import org.junit.jupiter.api.Test;

/**
 * @author 王宇杰
 * @date 2020/1/9 15:46
 */
public class UserDaoTest {
    @Test
    public void addUserTest() {
        try {
            User user = new User();
            user.setUserWorkNumber("1705010450");
            user.setUserPassword("123456789");
            new UserDaoImpl(DatabaseOperations.getConnection()).addUser(new User());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
