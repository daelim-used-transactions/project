package com.daelim.transactions.controller;

import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;

@Controller
public class MemberJoin {
    @Autowired
    ServiceTest serviceTest;

    /**
     * 회원가입 페이지로 넘어갈 때 mapping
     */
    @GetMapping(value="/login/join")
    public String testLogin(Model model){
        MemberDTO member = new MemberDTO();
        model.addAttribute("member",member);
        return "login/memberJoin";
    }

    /**
     * 회원가입 button 클릭 시 mapping
     */
    @PostMapping(value = "/login/join/register")
    public String testRegister(final MemberDTO member) throws NoSuchAlgorithmException {
        int checkInsert = serviceTest.memberInsert(member);
        return "redirect:/login";
    }

    /**
     * id 중복 검사 id 입력란 focus 잃었을 때 mapping
     */
    @PostMapping(value = "/idtest")
    @ResponseBody
    public String ajaxTest(String id){
        String idCheck = null;
        if(serviceTest.isDuplicatedId(id)){
            idCheck = "true";
        }
        return idCheck;
    }
}
