package com.daelim.transactions.service;

import com.daelim.transactions.utils.MailHandler;
import com.daelim.transactions.utils.RandomPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Map<String, Object> idSearch(String email, String id) {

        MailHandler mailHandler;
        String senderAdr = "explore2012121@gmail.com";
        id = id.replace(id.substring(id.length()/2-2,id.length()/2+2),"****");
        try{
            mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(email);
            mailHandler.setFrom(senderAdr , "대림대학교 중고 거래");
            mailHandler.setSubject("아이디 찾기 입니다.");
            mailHandler.setText("회원님의 아이디는 " + id + "입니다.",false);
            mailHandler.send();

        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String msg = "메일이 발송되었습니다.";
        Map<String , Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("id", id);
        return map;
    }

    /**
     * 이메일 인증번호 발송
     */
    @Override
    public String sendNumber(String email) {

        MailHandler mailHandler;
        RandomPassword randomPassword = new RandomPassword();
        String senderAdr = "explore2012121@gmail.com"; // ???
        String AuthenticationNumber = randomPassword.getRandom(6);;
        try{
            mailHandler = new MailHandler(mailSender);
            mailHandler.setTo(email);
            mailHandler.setFrom(senderAdr , "대림장터 이메일 인증번호 발송");
            mailHandler.setSubject("대림장터 이메일 인증번호 발송");
            mailHandler.setText("인증번호는 " +  AuthenticationNumber + "입니다.",false);
            mailHandler.send();

        } catch (MessagingException | UnsupportedEncodingException e) {
            AuthenticationNumber = null;
            e.printStackTrace();
        }

        return  AuthenticationNumber;
    }
}
