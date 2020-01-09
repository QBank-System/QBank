package com.qbank.qbank.servlet;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangyujie
 */
@Controller
public class QuestionLibs {

    @RequestMapping("/QueryAllQuestions")
    public ResponseEntity<String> queryAllQuestions(HttpServletRequest request) {

        return null;

    }

}
