package com.daelim.transactions.service;

import com.daelim.transactions.dto.EmployeeDTO;
import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.dto.afafDTO;
import com.daelim.transactions.utils.CryptoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
public class ServiceTestCode {

    @Autowired
    ServiceTest serviceTest;

    @Autowired
    MailService mailService;

    @Test
    void OptEmpInfoTest(){
        Optional<EmployeeDTO> optEmpInfo = serviceTest.optToEmpInfo("id");

        if(!optEmpInfo.isPresent()){
            // id가 존재하지 않을 시
            System.out.println("비었다..");
        }else{
            EmployeeDTO empInfo = optEmpInfo.get();
            System.out.println(empInfo.getLoginID());

        }
    }

    @Test
    void insertMemberTest() throws NoSuchAlgorithmException {
        MemberDTO member = new MemberDTO();
        member.setLoginId("hello12");
        member.setLoginPw("world");
        member.setName("김태양");
        member.setEmail("helloWorld@naver.com");
        member.setNickName("태비서");
        CryptoUtil cryptoUtil = new CryptoUtil();
        String enPass = cryptoUtil.sha256(member.getLoginPw());
        member.setLoginPw(enPass);
        int optMember = serviceTest.memberInsert(member);
    }
    @Test
    public void mailTest(){
        mailService.idSearch("explore2012@naver.com", "dkandkdlel123");
    }
}
