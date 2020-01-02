package com.qbank.qbank.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

/**
 * @author wangyujie
 */
@Controller
public class Personal {

    @RequestMapping("/Login")
    public ResponseEntity<String> login(HttpServletRequest request) {

        String workNumber = request.getParameter("work_number");
        String password = request.getParameter("password");

        JsonArray resultSet = new JsonArray();
        try {
            Connection conn = DatabaseOperations.getConnection();
            PreparedStatement pstm;
            ResultSet rs;
            String sql;

            sql = "select * from user where (user_work_number = '" + workNumber + "' and user_password = '" + password + "');";

            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs.next()) {
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
                resultSet.add(object);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(resultSet.toString(), HttpStatus.OK);
    }

}
