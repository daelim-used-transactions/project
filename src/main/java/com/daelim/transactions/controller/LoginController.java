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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    ServiceTest serviceTest;


    @GetMapping(value = "/login")
    public String login(Model model, @RequestParam(value = "flag",required = false ,defaultValue = "true") boolean flag) {
        MemberDTO memberDTO = new MemberDTO();
        model.addAttribute("loginInfo", memberDTO);
        model.addAttribute("flag", flag);
        return "login/login";
    }

    @PostMapping(value = "/main/dlogin")
    public String loginTest(final MemberDTO memberDTO, HttpServletRequest hsr, RedirectAttributes rttr) throws NoSuchAlgorithmException {
        HttpSession session = hsr.getSession();
        MemberDTO member = serviceTest.getLogin(memberDTO);
        if (member == null) {
            session.setAttribute("member", null);
            rttr.addFlashAttribute("msg", false);

            return "redirect:/login?flag=false";
        } else {
            session.setAttribute("member", member);
            session.setAttribute("memId",member.getLoginId());
            session.setMaxInactiveInterval(60 * 30);

            return "main";
        }

    }

    @GetMapping(value = "/main/dlogout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "main";
    }


}
