package com.qbank.qbank.entity;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 王宇杰
 * @date 2020/1/12 14:08
 */
class UserTest {

    @Test
    void toJson(){
        //{"userMail1":"userMail","userMail2":"userMail","userMail3":"userMail"}
        //{"userId":"1651165","userCase":"null","userWorkNumber":"null","userPassword":"null","userName":"null","userTitle":"null","userCollege":"null","userProfessionalField":"null","userPhoneNumber":"null","userMail":"null","userOffice":"5sf6516f","userGrade":"null","userTime":"null"}
        User user = new User();
        user.setUserId("1651165");
        user.setUserOffice("5sf6516f");
        System.out.println(user.toString());
    }

}