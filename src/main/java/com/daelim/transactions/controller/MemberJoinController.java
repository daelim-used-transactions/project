package com.daelim.transactions.controller;

import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.MailService;
import com.daelim.transactions.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
    @PostMapping(value = "/idCheck")
    @ResponseBody
    public String ajaxTest(String id){
        String idCheck = null;
        if(serviceTest.isDuplicatedId(id)){
            idCheck = "true";  //중복되는 아이디가 없을 경우
        }
        return idCheck;
    }

    /**
     * email 전송 및 인증번호 반환
     */
    @PostMapping(value ="/emailCheck")
    @ResponseBody
    public String emailTest(String userEmail){
        userEmail +="@email.daelim.ac.kr";
        String AuthenticationNumber = mailService.sendNumber(userEmail);
        return AuthenticationNumber;
    }


    /**
     * ajax 와 server 간에 json으로 데이터를 주고받는 연습이자
     * 닉네임 중복검사 API
     */
    @PostMapping(value ="/nicknameCheck")
    @ResponseBody
    public Object nicknameTest(@RequestBody Map<String,Object> param){//Stirng,Object로 해도 되네
        String nickNameCheck = null;
        Map<String, Object> map = new HashMap<>();
        if(serviceTest.isDuplicatedNickname((String) param.get("userNickname"))){
            nickNameCheck = "true";  //중복되는 닉네임이 없을 경우
            map.put("nickNameCheck", nickNameCheck);
        }
        return map;
    }

}//class end
