package com.daelim.transactions.controller;

import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class ServerTest {

    @Autowired
    ServiceTest serviceTest;

    @GetMapping(value = "/test")
    public String test (Model model){
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("loginInfo",memberDTO);
        return "login/login";
    }

    @GetMapping(value = "/test/main")
    public String testMain (){
        return "main";
    }
    @GetMapping(value = "/test/findId")
    public String testFindId (){
        return "/login/findId";
    }
    @GetMapping(value = "/test/findPw")
    public String testFindPw (){
        return "/login/findPw";
    }
    @GetMapping(value="/test/main/myPage")
    public String testMyPage(){
        return "/myPage/myPage";
    }
    @PostMapping(value="/test/main/login")
    public String loginTest(final MemberDTO memberDTO, HttpServletRequest hsr , RedirectAttributes rttr) throws NoSuchAlgorithmException {
        HttpSession session = hsr.getSession();
        MemberDTO member = serviceTest.getLogin(memberDTO);
        if(member == null){
            session.setAttribute("member",null);
            rttr.addFlashAttribute("msg",false);
        }else{
            session.setAttribute("member",member);
            session.setMaxInactiveInterval(60*30);
        }
        return "main";
    }
    @GetMapping(value="/test/main/logout")
    public String logout( HttpSession session){
        session.invalidate();
        return "main";
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
