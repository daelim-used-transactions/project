package com.daelim.transactions.controller;

import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class MainController {

    @Autowired
    ServiceTest serviceTest;

    @GetMapping(value = "/main")
    public String goMain() {
        return "main";
    }


    @GetMapping(value = "/main/myPage/{memId}")
    public String toMyPage(@PathVariable("memId") String memId,  Model model) {

        System.out.println("로그인 아이디 : "+memId);
        MemberDTO member =  serviceTest.getProfile(memId);
        model.addAttribute("memNick",member.getNickName());
        model.addAttribute("memProfile",member.getProfile());
        model.addAttribute("memId",memId);
        return "/myPage/myPage";
    }
}
