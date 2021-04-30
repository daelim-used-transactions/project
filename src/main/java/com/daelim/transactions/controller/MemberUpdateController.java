package com.daelim.transactions.controller;

import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.ServiceTest;
import com.daelim.transactions.utils.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller
public class MemberUpdateController {

    @Autowired
    ServiceTest serviceTest;

    @PostMapping(value="/main/myPage/profile/passUpdate")
    @ResponseBody
    public Boolean getPassword(HttpServletRequest request, String pass) throws NoSuchAlgorithmException {
        CryptoUtil cryptoUtil =new CryptoUtil();
        String enPass = cryptoUtil.sha256(pass);
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("memId");
        MemberDTO memberDTO =serviceTest.getAllInfo(id);
        if(enPass.equals(memberDTO.getLoginPw())){
            return true ;
        }else{
            return false ;
        }
    }

    @PostMapping(value="/main/myPage/profile/passUpdate.do")
    public String updateProfileWrite(HttpServletRequest request, final MemberDTO memberDTO){
        HttpSession session = request.getSession();
        MemberDTO member  = (MemberDTO) session.getAttribute("member");
        System.out.println("현재 멤버 아이디: " +member.getLoginId());
        System.out.println("현재 멤버 비번: " +member.getLoginPw());
        System.out.println("변경할 멤버 비번: " +memberDTO.getLoginPw());

        return "redirect:/main";
    }

}
