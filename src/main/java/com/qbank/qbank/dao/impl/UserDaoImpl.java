package com.qbank.qbank.dao.impl;

import com.qbank.qbank.dao.inf.IUserDao;
import com.qbank.qbank.entity.User;
import com.qbank.qbank.utils.DBUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.qbank.qbank.utils.TimeUtil.getTime;

/**
 * @author 王宇杰
 * @date 2020/1/9 14:52
 */
public class UserDaoImpl implements IUserDao {
    private static UserDaoImpl userDao;
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    private String sql;

    public UserDaoImpl() {
        this.conn = DBUtil.getConnection();
    }

    public static UserDaoImpl getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public void closeAll() {
        DBUtil.closeAll(conn, pstm, rs);
    }

    @Override
    public int addUser(User user) throws SQLException {
        Object[] objects = new Object[7];
        objects[0] = user.getUserWorkNumber();
        objects[1] = DigestUtils.sha1Hex(user.getUserPassword());
        objects[2] = user.getUserName();
        objects[3] = user.getUserCollege();
        objects[4] = user.getUserPhoneNumber();
        objects[5] = 2;
        objects[6] = getTime();
        int result;
        sql = "insert into sys_user(sys_user_worknumber,sys_user_password,sys_user_name,sys_user_college,sys_user_phonenumber,sys_user_grade,sys_user_createtime)value(?,?,?,?,?,?,?)";
        pstm = conn.prepareStatement(sql);
        result = DBUtil.exUpdate(pstm, objects);
        return result;
    }

    @Override
    public int[] addUsers(List<User> users) throws SQLException {
        int[] result = new int[users.size()];
        int i = 0;
        for (User user : users) {
            result[i++] = addUser(user);
        }
        return result;
    }

    @Override
    public int delUser(String index, int indexClass) throws SQLException {
        Object[] objects = new Object[1];
        objects[0] = index;
        int result;
        switch (indexClass) {
            case CLASS_USERID:
                sql = "delete from sys_user where sys_user.sys_user_id=?;";
                break;
            case CLASS_USERWORKNUMBER:
                sql = "delete from sys_user where sys_user.sys_user_worknumber=?;";
                break;
            case CLASS_USERNAME:
                sql = "delete from sys_user where sys_user.sys_user_name=?;";
                break;
            default:
                return 0;
        }
        pstm = conn.prepareStatement(sql);
        result = DBUtil.exUpdate(pstm, objects);
        sql = "select sys_user.sys_user_id from sys_user order by sys_user.sys_user_id desc limit 1;";
        pstm = conn.prepareStatement(sql);
        rs = pstm.executeQuery();
        rs.next();
        int autoIncrement = rs.getInt(1);
        sql = "alter table QBank.sys_user auto_increment=" + autoIncrement + ";";
        pstm = conn.prepareStatement(sql);
        pstm.executeUpdate();
        return result;
    }

    @Override
    public int[] delUsers(String[] index, int[] indexClass) throws SQLException {
        int[] result = new int[index.length];
        for (int i = 0; i < index.length; i++) {
            result[i] = delUser(index[i], indexClass[i]);
        }
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
        int result;
        conn = DBUtil.getConnection();
        sql = "update sys_user set sys_user.sys_user_case=?,sys_user.sys_user_worknumber=?,sys_user.sys_user_password=?,sys_user.sys_user_name=?,sys_user.sys_user_title=?,sys_user.sys_user_college=?,sys_user.sys_user_professionalfield=?,sys_user.sys_user_phonenumber=?,sys_user.sys_user_mail=?,sys_user.sys_user_office=?where sys_user.sys_user_id=?;";
        pstm = conn.prepareStatement(sql);
        result = DBUtil.exUpdate(pstm, objects);
        return result;
    }

    @Override
    public int[] updateUsers(List<User> users) throws SQLException {
        int[] result = new int[users.size()];
        int i = 0;
        for (User user : users) {
            result[i++] = updateUser(user);
        }
        return result;
    }

    @Override
    public User getUser(String index, int indexClass) throws SQLException {
        Object[] objects = new Object[1];
        objects[0] = index;
        User user = new User();
        switch (indexClass) {
            case CLASS_USERID:
                sql = "select * from sys_user where sys_user.sys_user_id=?;";
                break;
            case CLASS_USERWORKNUMBER:
                sql = "select * from sys_user where sys_user.sys_user_worknumber=?;";
                break;
            case CLASS_USERNAME:
                sql = "select * from sys_user where sys_user.sys_user_name=?;";
                break;
            default:
                return null;
        }
        pstm = conn.prepareStatement(sql);
        rs = DBUtil.exQuery(pstm, objects);
        if (rs.next()) {
            user.setUserId(rs.getString(1));
            user.setUserCase(rs.getString(2));
            user.setUserWorkNumber(rs.getString(3));
            user.setUserPassword(rs.getString(4));
            user.setUserName(rs.getString(5));
            user.setUserTitle(rs.getString(6));
            user.setUserCollege(rs.getString(7));
            user.setUserProfessionalField(rs.getString(8));
            user.setUserPhoneNumber(rs.getString(9));
            user.setUserMail(rs.getString(10));
            user.setUserOffice(rs.getString(11));
            user.setUserGrade(rs.getString(12));
            user.setUserTime(rs.getString(13));
        } else {
            user = null;
        }
        return user;
    }

    @Override
    public List<User> getUsers(String index) throws SQLException {
        index = "%" + index + "%";
        List<User> list = new LinkedList<>();
        Object[] objects = new Object[3];
        objects[0] = index;
        objects[1] = index;
        objects[2] = index;
        sql = "select * from sys_user where cast(sys_user.sys_user_id as char) like ? or cast(sys_user.sys_user_worknumber as char)  like ? or sys_user.sys_user_name  like ? ;";
        pstm = conn.prepareStatement(sql);
        rs = DBUtil.exQuery(pstm, objects);
        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getString(1));
            user.setUserCase(rs.getString(2));
            user.setUserWorkNumber(rs.getString(3));
            user.setUserPassword(rs.getString(4));
            user.setUserName(rs.getString(5));
            user.setUserTitle(rs.getString(6));
            user.setUserCollege(rs.getString(7));
            user.setUserProfessionalField(rs.getString(8));
            user.setUserPhoneNumber(rs.getString(9));
            user.setUserMail(rs.getString(10));
            user.setUserOffice(rs.getString(11));
            user.setUserGrade(rs.getString(12));
            user.setUserTime(rs.getString(13));
            list.add(user);
        }
        return list;
    }

}
