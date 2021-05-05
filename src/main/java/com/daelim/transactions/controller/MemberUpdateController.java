package com.daelim.transactions.controller;

import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.ProfileService;
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

    @Autowired
    ProfileService profileService;

    @PostMapping(value="/main/myPage/profile/passUpdate")
    @ResponseBody
    public Boolean getPassword(HttpServletRequest request, String pass) throws NoSuchAlgorithmException {
        CryptoUtil cryptoUtil =new CryptoUtil();
        String enPass = cryptoUtil.sha256(pass);
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("memId");
        MemberDTO memberDTO =serviceTest.getAllInfo(id);
        System.out.println("작성한 : " + enPass + "현재 비번 :"+memberDTO.getLoginPw());

        if(enPass.equals(memberDTO.getLoginPw())){
            return true;
        }else{
            return false;
        }
    }

    @PostMapping(value="/main/myPage/profile/passUpdate.do")
                                                                   // 비밀번호만 들어있는 객체
    public String updateProfileWrite(HttpServletRequest request, final MemberDTO memberDTO) throws NoSuchAlgorithmException {
        HttpSession session = request.getSession();
        MemberDTO member  = (MemberDTO) session.getAttribute("member");
        member.setLoginPw(memberDTO.getLoginPw());
        int chk = profileService.updateByPass(member);
        if(chk == 1){
//            알럴트처리 해야겠다ㅡㅡ
        }
        System.out.println("현재 멤버 아이디: " +member.getLoginId());
        System.out.println("현재 멤버 비번: " +member.getLoginPw());
        System.out.println("변경할 멤버 비번: " +memberDTO.getLoginPw());

        return "redirect:/main";
    }

}
