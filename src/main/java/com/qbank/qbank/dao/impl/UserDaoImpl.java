package com.qbank.qbank.dao.impl;

import com.qbank.qbank.dao.inf.IUserDao;
import com.qbank.qbank.entity.User;
import com.qbank.qbank.utils.DatabaseOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.qbank.qbank.utils.MyTime.getTime;

/**
 * @author 王宇杰
 * @date 2020/1/9 14:52
 */
public class UserDaoImpl implements IUserDao {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    private String sql;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int addUser(User user) throws SQLException {
        Object[] objects = new Object[7];
        objects[0] = user.getUserWorkNumber();
        objects[1] = user.getUserPassword();
        objects[2] = user.getUserName();
        objects[3] = user.getUserCollege();
        objects[4] = user.getUserPhoneNumber();
        objects[5] = 2;
        objects[6] = getTime();
        int result;
        sql = "insert into user(work_number,password,name,college,phone_number,grade,time)value(?,?,?,?,?,?,?)";
        pstm = conn.prepareStatement(sql);
        result = DatabaseOperations.exUpdate(pstm, objects);
        DatabaseOperations.closeAll(null, pstm, null);
        return result;
    }

    @Override
    public int addUsers(List<User> users) throws SQLException {
        int result = 0;
        for (User user : users) {
            result += addUser(user);
        }
        DatabaseOperations.closeAll(conn, pstm, null);
        return result;
    }

    @Override
    public int delUser(String index, int indexClass) throws SQLException {
        Object[] objects = new Object[1];
        objects[0] = index;
        int result = 0;
        switch (indexClass) {
            case 1:
                sql = "delete from user where user.id=?;";
                break;
            case 2:
                sql = "delete from user where user.work_number=?;";
                break;
            case 3:
                sql = "delete from user where user.name=?;";
                break;
            default:
        }
        pstm = conn.prepareStatement(sql);
        result = DatabaseOperations.exUpdate(pstm, objects);
        DatabaseOperations.closeAll(null, pstm, null);
        return result;
    }

    @Override
    public int delUsers(String[] index, int[] indexClass) throws SQLException {
        int result = 0;
        for (int i = 0; i < index.length; i++) {
            result += delUser(index[i], indexClass[i]);
        }
        DatabaseOperations.closeAll(null, pstm, null);
        return result;
    }

    @Override
    public int updateUser(User user) throws SQLException {
        Object[] objects = new Object[11];
        objects[0] = user.getUserCase();
        objects[1] = user.getUserWorkNumber();
        objects[2] = user.getUserPassword();
        objects[3] = user.getUserName();
        objects[4] = user.getUserTitle();
        objects[5] = user.getUserCollege();
        objects[6] = user.getUserProfessionalField();
        objects[7] = user.getUserPhoneNumber();
        objects[8] = user.getUserMail();
        objects[9] = user.getUserOffice();
        objects[10] = user.getUserId();
        int result = 0;
        conn = DatabaseOperations.getConnection();
        sql = "update user set user.case=?,user.work_number=?,user.password=?,user.name=?,user.title=?,user.college=?,user.professional_field=?,user.phone_number=?,user.mail=?,user.office=?where user.id=?;";
        pstm = conn.prepareStatement(sql);
        result = DatabaseOperations.exUpdate(pstm, objects);
        DatabaseOperations.closeAll(null, pstm, null);
        return result;
    }

    @Override
    public int updateUsers(List<User> users) throws SQLException {
        int result = 0;
        for (User user : users) {
            result += updateUser(user);
        }
        return result;
    }

    @Override
    public User getUserByUserId(String index, int indexClass) throws SQLException {

        return null;
    }

    @Override
    public List<User> getUsersByUserId(String index) throws SQLException {
        return null;
    }

}
