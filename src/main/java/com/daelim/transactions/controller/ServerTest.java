package com.daelim.transactions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServerTest {

    @Autowired
    ServerTest serviceTest;

    @GetMapping(value = "/test")
    public String test (){
        return "/study1";
    }
}
