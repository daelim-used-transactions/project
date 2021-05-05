package com.daelim.transactions.service;

import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.mapper.DaoTest;
import com.daelim.transactions.utils.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class ProfileServiceImpl implements ProfileService{

    @Autowired
    DaoTest daoTest;

    @Override
    public int updateByPass(MemberDTO memberDTO) throws NoSuchAlgorithmException {
        CryptoUtil cryptoUtil = new CryptoUtil();
        String enpass = cryptoUtil.sha256(memberDTO.getLoginPw());
        memberDTO.setLoginPw(enpass);
        int chk = daoTest.updateByPass(memberDTO);
        if(chk == 1){
            return 1;
        }else{
            return 0;
        }

    }
}
