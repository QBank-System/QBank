package com.qbank.qbank.test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangyujie
 */
@Controller
public class Test {

    @RequestMapping("/Test")
    public String test(){
        return "login";
    }

    public static void main(String[] args) {
        String result = "{\"resultCode\":\"success\",\"resultMessage\":\"查询成功\",\"resultObj\":{\"user1\":{\"userId\":\"2\",\"userCase\":\"E:\\\\other\\\\test\\\\test2.png\",\"userWorkNumber\":\"1705010425\",\"userPassword\":\"59ce43ba4e602b1f92bdd0ee7db67017c2d13058\",\"userName\":\"黄金金\",\"userTitle\":\"教师\",\"userCollege\":\"计算机科学与工程\",\"userProfessionalField\":\"计算机\",\"userPhoneNumber\":\"15200371329\",\"userMail\":\"jinjinhuanglucky@163.com\",\"userOffice\":\"8-3-418\",\"userGrade\":\"0\",\"userTime\":\"20191231000000\"},\"user2\":{\"userId\":\"3\",\"userCase\":\"E:\\\\other\\\\test\\\\test2.png\",\"userWorkNumber\":\"1705010450\",\"userPassword\":\"59ce43ba4e602b1f92bdd0ee7db67017c2d13058\",\"userName\":\"Test\",\"userTitle\":\"教师\",\"userCollege\":\"计算机科学与工程\",\"userProfessionalField\":\"计算机\",\"userPhoneNumber\":\"17769400425\",\"userMail\":\"858335157@qq.cpm\",\"userOffice\":\"7-10-107\",\"userGrade\":\"2\",\"userTime\":\"20200110173638\"},\"page_number\":1,\"user0\":{\"userId\":\"1\",\"userCase\":\"E:\\\\other\\\\test\\\\test.png\",\"userWorkNumber\":\"1705010406\",\"userPassword\":\"59ce43ba4e602b1f92bdd0ee7db67017c2d13058\",\"userName\":\"王宇杰\",\"userTitle\":\"学生\",\"userCollege\":\"计算机科学与工程\",\"userProfessionalField\":\"计算机\",\"userPhoneNumber\":\"18570379120\",\"userMail\":\"858335157@qq.com\",\"userOffice\":\"7-10-107\",\"userGrade\":\"0\",\"userTime\":\"20191231000000\"},\"count\":3,\"page_count\":1,\"count_a_page\":10},\"redirectUrl\":null}";
        JSONObject object = JSONObject.parseObject(result);
        JSONObject object1 = object.getJSONObject("resultObj");
        System.out.println(object1.getString("user_count"));
        System.out.println(object1.getString("page_count"));
        System.out.println(object1.getString("count_a_page"));
        System.out.println(object1.getString("page_number"));
        for(int i = 0 ; i < object1.getInteger("user_count");i++){
            System.out.println(object1.getString("user"+i));
        }
    }
}
