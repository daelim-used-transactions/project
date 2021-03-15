package com.daelim.transactions.controller;

import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;

@Controller
public class ServerTest {

    @Autowired
    ServiceTest serviceTest;

    @GetMapping(value = "/test")
    public String test (){
        return "/study1";
    }

    @GetMapping(value="/test/login")
    public String testLogin(Model model){
        MemberDTO member = new MemberDTO();
        model.addAttribute("member",member);
        return "login/memberJoin";
    }

    @PostMapping(value = "/test/register")
    public String testRegister(final MemberDTO member) throws NoSuchAlgorithmException {
        int checkInsert = serviceTest.memberInsert(member);
        return "redirect:/test";
    }
}
