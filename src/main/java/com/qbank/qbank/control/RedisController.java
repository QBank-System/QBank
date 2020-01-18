package com.qbank.qbank.control;

import com.qbank.qbank.service.impl.RedisImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisImplService service;

    @GetMapping("/set")
    public String setMsg(@RequestParam(value = "key") String key, @RequestParam(value = "value") String msg) throws Exception {
        return service.set(key, msg) ? "true" : "false";
    }

    @GetMapping("/get")
    public String getMsg(@RequestParam(value = "key") String key) throws Exception {
        return service.get(key).toString();
    }

}