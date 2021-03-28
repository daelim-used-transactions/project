package com.daelim.transactions.controller;

import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.MailService;
import com.daelim.transactions.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;

@Controller
public class MemberJoinController {
    @Autowired
    ServiceTest serviceTest;
    @Autowired
    MailService mailService;

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

    /**
     * email 전송 및 인증번호 반환
     */
    @PostMapping(value ="/emailtest")
    @ResponseBody
    public String emailTest(String userEmail){
        userEmail +="@email.daelim.ac.kr";
        String AuthenticationNumber = mailService.sendNumber(userEmail);
        return AuthenticationNumber;
    }


}//class end
