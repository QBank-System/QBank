package com.qbank.qbank.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.qbank.qbank.utils.TimeUtil.getTime;

/**
 * @author wangyujie
 */
public class LogUtil {

    public static void log(String operation, String operator, String remark) {
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement pstm;
            String sql;
            sql = "insert into logbook( operation, operator, remark, time ) value('" + operation + "','" + operator + "','" + remark + "','" + getTime() + "')";
            pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
            DBUtil.closeAll(conn, pstm, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
