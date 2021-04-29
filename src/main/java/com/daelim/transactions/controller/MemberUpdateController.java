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
        System.out.println("d;r[padkdo비밀번호 : " + enPass);
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("memId");
        MemberDTO memberDTO =serviceTest.getAllInfo(id);
        System.out.println("멤버 비밀번호 : " + memberDTO.getLoginPw());
        if(enPass.equals(memberDTO.getLoginPw())){
            return true ;
        }else{
            return false ;
        }
    }
}
