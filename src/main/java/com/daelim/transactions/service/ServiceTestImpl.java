package com.daelim.transactions.service;

import com.daelim.transactions.dto.EmployeeDTO;
import com.daelim.transactions.dto.afafDTO;
import com.daelim.transactions.mapper.DaoTest;
import com.daelim.transactions.utils.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceTestImpl implements ServiceTest {

    private final DaoTest daoTest;


    @Override
    public Optional<EmployeeDTO> optToEmpInfo(String id) {
        //EmployeeDTO emp = dao.selectById(id);
        Optional<EmployeeDTO> optEmp = Optional.ofNullable(daoTest.selectById(id));
        return optEmp;
    }

    @Override
    public int optMemberInsert(afafDTO testDTO) throws NoSuchAlgorithmException {
        CryptoUtil cryptoUtil =new CryptoUtil();
        String enPass = cryptoUtil.sha256(testDTO.getPass());
        testDTO.setPass(enPass);
      int optMember = daoTest.insertMember(testDTO);
        System.out.println("테스트 해보자 " + optMember);
        return optMember;
    }
}
