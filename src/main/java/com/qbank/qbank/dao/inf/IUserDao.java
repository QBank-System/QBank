package com.qbank.qbank.dao.inf;

import com.qbank.qbank.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 王宇杰
 * @date 2020/1/9 12:44
 */
public interface IUserDao {

    //增

    /**
     * 添加一个用户数据
     *
     * @param user 用户
     * @return 影响行数
     * @throws SQLException SQLException
     */
    int addUser(User user) throws SQLException;

    /**
     * 添加一组用户数据
     *
     * @param users 用户组
     * @return 影响行数
     * @throws SQLException SQLException
     */
    int addUsers(List<User> users) throws SQLException;

    //删

    /**
     * 通过索引删除用户数据
     *
     * @param index      索引
     * @param indexClass 索引类别(1:userId;2:userWorkNumber;3:userName)
     * @return 影响行数
     * @throws SQLException SQLException
     */
    int delUser(String index, int indexClass) throws SQLException;

    /**
     * 通过索引组删除用户数据
     *
     * @param index      索引组
     * @param indexClass 每个索引类别(1:userId;2:userWorkNumber;3:userName)
     * @return 影响行数
     * @throws SQLException SQLException
     */
    int delUsers(String[] index, int[] indexClass) throws SQLException;

    //改

    /**
     * 修改一个用户数据
     *
     * @param user 用户
     * @return 影响行数
     * @throws SQLException SQLException
     */
    int updateUser(User user) throws SQLException;

    /**
     * 修改一组用户数据
     *
     * @param users 用户组
     * @return 影响行数
     * @throws SQLException SQLException
     */
    int updateUsers(List<User> users) throws SQLException;

    //查

    /**
     * 通过索引获取用户数据
     *
     * @param index      索引
     * @param indexClass 索引类别(1:userId;2:userWorkNumber;3:userName)
     * @return 用户
     * @throws SQLException SQLException
     */
    User getUser(String index, int indexClass) throws SQLException;

    /**
     * 通过索引在userId,userWorkNumber,userName中模糊查找一组用户
     *
     * @param index 索引
     * @return 用户组
     * @throws SQLException SQLException
     */
    List<User> getUsers(String index) throws SQLException;


}
