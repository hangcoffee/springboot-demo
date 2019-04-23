package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Coffee
 * @Date: 2019/4/6
 */
@RestController
@RequestMapping("/api")
public class WxController {

    @GetMapping("/wx/callBack")
    public String callBack() {
        return null;
    }

}
