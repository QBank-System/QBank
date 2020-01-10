package com.qbank.qbank.dao.impl;

import com.qbank.qbank.entity.User;
import com.qbank.qbank.utils.DatabaseOperations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static com.qbank.qbank.dao.impl.UserDaoImpl.CLASS_USERWORKNUMBER;
import static com.qbank.qbank.dao.impl.UserDaoImpl.getUserDao;

/**
 * @author 王宇杰
 * @date 2020/1/10 15:46
 */
class UserDaoImplTest {

    @Test
    void logicTest() throws SQLException, InterruptedException {
        System.out.println("addUser");
        Thread.sleep(500);
        addUser();
        System.out.println("delUser");
        Thread.sleep(500);
        delUser();
        System.out.println("addUsers");
        Thread.sleep(500);
        addUsers();
        System.out.println("delUsers");
        Thread.sleep(500);
        delUsers();
        System.out.println("addUser");
        Thread.sleep(500);
        addUser();
        System.out.println("updateUser");
        Thread.sleep(500);
        updateUser();
        System.out.println("end");
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        getUserDao().closeAll();
    }

    @Test
    void addUser() throws SQLException {
        User user = new User();
        user.setUserWorkNumber("1705010450");
        user.setUserPassword("123456789");
        user.setUserName("Test");
        user.setUserCollege("计算机科学与工程");
        user.setUserPhoneNumber("17769400425");
        getUserDao().addUser(user);
    }

    @Test
    void addUsers() throws SQLException {
        User user1 = new User();
        user1.setUserWorkNumber("1705010450");
        user1.setUserPassword("123456789");
        user1.setUserName("Test");
        user1.setUserCollege(" 计算机科学与工程");
        user1.setUserPhoneNumber("17769400425");
        User user2 = new User();
        user2.setUserWorkNumber("1705010451");
        user2.setUserPassword("123456789");
        user2.setUserName("Test");
        user2.setUserCollege(" 计算机科学与工程");
        user2.setUserPhoneNumber("17769400425");
        User user3 = new User();
        user3.setUserWorkNumber("1705010452");
        user3.setUserPassword("123456789");
        user3.setUserName("Test");
        user3.setUserCollege(" 计算机科学与工程");
        user3.setUserPhoneNumber("17769400425");
        List<User> list = new LinkedList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        getUserDao().addUsers(list);
    }

    @Test
    void delUser() throws SQLException {
        String index = "1705010450";
        int indexClass = CLASS_USERWORKNUMBER;
        getUserDao().delUser(index, indexClass);
    }

    @Test
    void delUsers() throws SQLException {
        String[] index = new String[3];
        int[] indexClass = new int[3];
        index[0] = "1705010450";
        index[1] = "1705010451";
        index[2] = "1705010452";
        indexClass[0] = CLASS_USERWORKNUMBER;
        indexClass[1] = CLASS_USERWORKNUMBER;
        indexClass[2] = CLASS_USERWORKNUMBER;
        getUserDao().delUsers(index, indexClass);
    }

    @Test
    void updateUser() throws SQLException {
        User user = new User();
        user.setUserId("3");
        user.setUserCase("E:\\other\\test\\test2.png");
        user.setUserWorkNumber("1705010450");
        user.setUserPassword("123456789");
        user.setUserName("Test");
        user.setUserTitle("教师");
        user.setUserCollege("计算机科学与工程");
        user.setUserProfessionalField("计算机");
        user.setUserPhoneNumber("17769400425");
        user.setUserMail("858335157@qq.cpm");
        user.setUserOffice("7-10-107");
        getUserDao().updateUser(user);
    }

    @Test
    void updateUsers() throws SQLException {
        List<User> list = new LinkedList<>();
        User user1 = new User();
        user1.setUserId("3");
        user1.setUserCase("E:\\other\\test\\test2.png");
        user1.setUserWorkNumber("1705010450");
        user1.setUserPassword("123456789");
        user1.setUserName("Test");
        user1.setUserTitle("教师");
        user1.setUserCollege("计算机科学与工程");
        user1.setUserProfessionalField("计算机");
        user1.setUserPhoneNumber("17769400425");
        user1.setUserMail("858335157@qq.cpm");
        user1.setUserOffice("7-10-107");
        list.add(user1);
        getUserDao().updateUsers(list);
    }

    @Test
    void getUser() throws SQLException {
        User user = getUserDao().getUser("1705010406", CLASS_USERWORKNUMBER);
        System.out.println(user.getUserName());
    }

    @Test
    void getUsers() throws SQLException {
        List<User> list = getUserDao().getUsers("1");
        for (User user : list) {
            System.out.println(user.getUserName());
        }
    }
}