package com.daelim.transactions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class MemberUpdateController {


    @PostMapping(value="/main/myPage/profile/passUpdate")
    @ResponseBody
    public String getPassword(String pass){


        return "ㄱㄷ" ;
    }
}
