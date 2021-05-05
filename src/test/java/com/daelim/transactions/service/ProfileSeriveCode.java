package com.daelim.transactions.service;

import com.daelim.transactions.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class ProfileSeriveCode {

    @Autowired
    ProfileService profileService;

    @Test
    public void updat() throws NoSuchAlgorithmException {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setLoginId("123123");
        memberDTO.setLoginPw("1234");
        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setLoginPw("asdf");
        memberDTO.setLoginPw(memberDTO1.getLoginPw());
        int chk = profileService.updateByPass(memberDTO);
        if(chk == 1){
            System.out.println("있다");
        }else{
            System.out.println("없다");
        }
    }
}
