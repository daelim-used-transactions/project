package com.daelim.transactions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @GetMapping(value = "/main")
    public String goMain() {
        return "main";
    }


    @GetMapping(value = "/main/myPage")
    public String toMyPage() {
        return "/myPage/myPage";
    }
}
