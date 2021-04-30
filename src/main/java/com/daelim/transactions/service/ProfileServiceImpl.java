package com.daelim.transactions.service;

import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.mapper.DaoTest;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfileServiceImpl implements ProfileService{

    @Autowired
    DaoTest daoTest;

    @Override
    public int updateByPass(MemberDTO memberDTO) {
        int chk = daoTest.updateByPass(memberDTO);

        return 1;
    }
}
