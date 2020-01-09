package com.qbank.qbank.servlet;

import com.aliyuncs.exceptions.ClientException;
import com.google.gson.JsonObject;
import com.qbank.qbank.utils.AliyunTool;
import com.qbank.qbank.utils.DatabaseOperations;
import com.qbank.qbank.utils.LogBook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.qbank.qbank.utils.MyTime.getTime;

/**
 * @author wangyujie
 */
@Controller
public class Personal {
    private String operation;
    private String operator;
    private String remark;

    @RequestMapping("/Login")
    public ResponseEntity<String> login(HttpServletRequest request) {
        operation = "";
        operator = "";
        remark = "";
        String workNumber = request.getParameter("work_number");
        String password = request.getParameter("password");
        operator = workNumber;
        JsonObject result = new JsonObject();
        try {
            Connection conn = DatabaseOperations.getConnection();
            PreparedStatement pstm;
            ResultSet rs;
            String sql;
            sql = "select * from user where (work_number = '" + workNumber + "' and password = '" + password + "');";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                result.addProperty("state", 1);
                JsonObject object = new JsonObject();
                object.addProperty("user_id", rs.getString(1));
                object.addProperty("user_case", rs.getString(2));
                object.addProperty("user_work_number", rs.getString(3));
                object.addProperty("user_password", rs.getString(4));
                object.addProperty("user_name", rs.getString(5));
                object.addProperty("user_title", rs.getString(6));
                object.addProperty("user_college", rs.getString(7));
                object.addProperty("user_professional_field", rs.getString(8));
                object.addProperty("user_phone_number", rs.getString(9));
                object.addProperty("user_email", rs.getString(10));
                object.addProperty("user_office", rs.getString(11));
                object.addProperty("user_grade", rs.getString(12));
                object.addProperty("user_create_time", rs.getString(13));
                result.add("personal", object);
                operation = "Login:s";
            } else {
                result.addProperty("state", 0);
                operation = "Login:f";
                remark += "账号或密码错误\n";
            }
        } catch (SQLException e) {
            result = new JsonObject();
            result.addProperty("state", -1);
            operation = "Login:f";
            remark += "SQLException\n";
            e.printStackTrace();
        }
        LogBook.log(operation, operator, remark);
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @RequestMapping("/Register")
    public ResponseEntity<String> register(HttpServletRequest request) {
        operation = "";
        operator = "";
        remark = "";
        String username = request.getParameter("user_name");
        String workNumber = request.getParameter("user_work_number");
        String password = request.getParameter("user_password");
        String college = request.getParameter("user_college");
        String phoneNumber = request.getParameter("user_phone_number");
        operator = workNumber;
        JsonObject result = new JsonObject();
        try {
            Connection conn = DatabaseOperations.getConnection();
            PreparedStatement pstm;
            ResultSet rs;
            String sql;
            sql = "select * from user where ( work_number = '" + workNumber + "')";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                result.addProperty("state", 0);
                operation = "Register:f";
                remark += "用户工号已被注册\n";
            } else {
                sql = "insert into user(name, work_number, password, college, phone_number, grade, time) value ('" + username + "','" + workNumber + "','" + password + "','" + college + "','" + phoneNumber + "','2','" + getTime() + "')";
                pstm = conn.prepareStatement(sql);
                if (pstm.executeUpdate() == 1) {
                    result.addProperty("state", 1);
                    sql = "select * from user where (name = '" + username + "' and work_number = '" + workNumber + "' and password = '" + password + "' and college = '" + college + "' and phone_number = '" + phoneNumber + "')";
                    pstm = conn.prepareStatement(sql);
                    rs = pstm.executeQuery();
                    rs.next();
                    JsonObject object = new JsonObject();
                    object.addProperty("user_id", rs.getString(1));
                    object.addProperty("user_case", rs.getString(2));
                    object.addProperty("user_work_number", rs.getString(3));
                    object.addProperty("user_password", rs.getString(4));
                    object.addProperty("user_name", rs.getString(5));
                    object.addProperty("user_title", rs.getString(6));
                    object.addProperty("user_college", rs.getString(7));
                    object.addProperty("user_professional_field", rs.getString(8));
                    object.addProperty("user_phone_number", rs.getString(9));
                    object.addProperty("user_email", rs.getString(10));
                    object.addProperty("user_office", rs.getString(11));
                    object.addProperty("user_grade", rs.getString(12));
                    object.addProperty("user_create_time", rs.getString(13));
                    result.add("personal", object);
                    operation = "Register:s";
                } else {
                    result.addProperty("state", -1);
                    operation = "Register:f";
                    remark += "插入数据库数据失败，改变行数!=1\n";
                }
            }
            DatabaseOperations.closeAll(conn, pstm, null);
        } catch (SQLException e) {
            result = new JsonObject();
            result.addProperty("state", -1);
            operation = "Register:f";
            remark += "SQLException\n";
            e.printStackTrace();
        }
        LogBook.log(operation, operator, remark);
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @RequestMapping("/SendVerificationCode")
    public ResponseEntity<String> sendVerificationCode(HttpServletRequest request) {
        operation = "";
        operator = "";
        remark = "";
        String phoneNumber = request.getParameter("phone_number");
        int code = ((int) ((Math.random() * 9 + 1) * 100000));
        operator = "phone:" + phoneNumber;
        JsonObject result = new JsonObject();
        try {
            AliyunTool.sendSMS(phoneNumber, code);
            result.addProperty("state", 1);
            JsonObject object = new JsonObject();
            object.addProperty("phone_number", phoneNumber);
            object.addProperty("verification_code", code);
            result.add("code", object);
            operation = "SendVerificationCode:s";
        } catch (ClientException e) {
            result = new JsonObject();
            result.addProperty("state", -1);
            operation = "SendVerificationCode:f";
            remark += "ClientException\n";
            e.printStackTrace();
        }
        LogBook.log(operation, operator, remark);
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

}
