package com.daelim.transactions.controller;


import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.MailService;
import com.daelim.transactions.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

@Controller
public class FindController {

    @Autowired
    ServiceTest serviceTest;

    @Autowired
    MailService mailService;

    @GetMapping(value = "/login/findId")
    public String testFindId (Model model) {
        MemberDTO member = new MemberDTO();
        model.addAttribute("member", member);
        return "login/findId";
    }

    @GetMapping(value = "/login/findPw")
    public String testFindPw (Model model){
        MemberDTO member = new MemberDTO();
        model.addAttribute("member", member);
        return "login/findPw";
    }


    @PostMapping(value="/login/dlogin/findId")
    public String testFindId(final MemberDTO memberDTO, Model model){
        Optional<MemberDTO> member = Optional.ofNullable(serviceTest.getFindId(memberDTO));

        if(!member.isPresent()){
            boolean flag = false;
            model.addAttribute("member",new MemberDTO());
            model.addAttribute("flag",flag);
            return "login/findId";
        }else{
            Map<String,Object> idSearch = mailService.idSearch(member.get().getEmail(),member.get().getLoginId());
            return "login/findIdResult";
        }
    }

    @PostMapping(value="/login/dlogin/findPw")
    public String testFindPass(final MemberDTO memberDTO,Model model) throws NoSuchAlgorithmException {
        String rcvPass = serviceTest.putRandomPass();
        Optional<MemberDTO> member = Optional.ofNullable(serviceTest.getFindPass(memberDTO,rcvPass));

        if(!member.isPresent()){
            boolean flag = false;
            model.addAttribute("member",new MemberDTO());
            model.addAttribute("flag",flag);
            return "login/findPw";
        }else{
            model.addAttribute("memberPw", rcvPass);
            return "login/findPwResult";
        }
    }
}
