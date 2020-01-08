package com.qbank.qbank.servlet;

import com.google.gson.JsonObject;
import com.qbank.qbank.utils.AliyunTool;
import com.qbank.qbank.utils.DatabaseOperations;
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

    @RequestMapping("/Login")
    public ResponseEntity<String> login(HttpServletRequest request) {

        String workNumber = request.getParameter("work_number");
        String password = request.getParameter("password");

        JsonObject result = new JsonObject();
        try {
            Connection conn = DatabaseOperations.getConnection();
            PreparedStatement pstm;
            ResultSet rs;
            String sql;

            sql = "select * from user where (user_work_number = '" + workNumber + "' and user_password = '" + password + "');";

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
                object.addProperty("user_create_time", rs.getString(12));
                object.addProperty("user_grade", rs.getString(13));
                result.add("personal", object);
            } else {
                result.addProperty("state", 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @RequestMapping("/Register")
    public ResponseEntity<String> register(HttpServletRequest request) {
        String userName = request.getParameter("user_name");
        String userWorkNumber = request.getParameter("user_work_number");
        String userPassword = request.getParameter("user_password");
        String userCollege = request.getParameter("user_college");
        String userPhoneNumber = request.getParameter("user_phone_number");
        JsonObject result = new JsonObject();
        try {
            Connection conn = DatabaseOperations.getConnection();
            PreparedStatement pstm;
            ResultSet rs;
            String sql;
            sql = "select * from user where ( user_work_number = '" + userWorkNumber + "')";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                result.addProperty("state", 0);
            } else {
                sql = "insert into user(user_name, user_work_number, user_password, user_college, user_phone_number, user_create_time, user_grade) value ('" + userName + "','" + userWorkNumber + "','" + userPassword + "','" + userCollege + "','" + userPhoneNumber + "','" + getTime() + "','2')";
                pstm = conn.prepareStatement(sql);
                if (pstm.executeUpdate() == 1) {
                    result.addProperty("state", 1);
                    sql = "select * from user where (user_name = '" + userName + "' and user_work_number = '" + userWorkNumber + "' and user_password = '" + userPassword + "' and user_college = '" + userCollege + "' and user_phone_number = '" + userPhoneNumber + "')";
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
                    object.addProperty("user_create_time", rs.getString(12));
                    object.addProperty("user_grade", rs.getString(13));
                    result.add("personal", object);
                } else {
                    result.addProperty("state", -1);
                }
            }
            DatabaseOperations.closeAll(conn, pstm, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

    @RequestMapping("/SendVerificationCode")
    public ResponseEntity<String> sendVerificationCode(HttpServletRequest request) {
        String phoneNumber = request.getParameter("phone_number");
        int code = ((int) ((Math.random() * 9 + 1) * 100000));
        AliyunTool.sendSMS(phoneNumber, code);
        JsonObject result = new JsonObject();
        result.addProperty("state", 1);
        JsonObject object = new JsonObject();
        object.addProperty("phone_number", phoneNumber);
        object.addProperty("verification_code", code);
        result.add("code", object);
        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }

}
