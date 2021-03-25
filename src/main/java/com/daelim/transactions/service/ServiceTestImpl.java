package com.daelim.transactions.service;

import com.daelim.transactions.dto.EmployeeDTO;
import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.dto.afafDTO;
import com.daelim.transactions.mapper.DaoTest;
import com.daelim.transactions.utils.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceTestImpl implements ServiceTest {

    private final DaoTest daoTest;


//    @Override
//    public Optional<EmployeeDTO> optToEmpInfo(String id) {
//        //EmployeeDTO emp = dao.selectById(id);
//        Optional<EmployeeDTO> optEmp = Optional.ofNullable(daoTest.selectById(id));
//        return optEmp;
//    }

    @Override
    public int memberInsert(MemberDTO member) throws NoSuchAlgorithmException {
        CryptoUtil cryptoUtil =new CryptoUtil();
        String enPass = cryptoUtil.sha256(member.getLoginPw());
        member.setLoginPw(enPass);
         int optMember = daoTest.insertMember(member);
        System.out.println("테스트 해보자 " + optMember);
        return optMember;
    }
    /**
     * false 반환 -> 아이디 이미 존재
     * true 반환 -> 아이디 존재 X
     * */
    @Override
    public boolean isDuplicatedId(String id){
        id = daoTest.selectById(id);
        return (id == null);
    }

    @Override
    public List<MemberDTO> getMemberList(MemberDTO memberDTO) {
        List<MemberDTO> memberList = Collections.emptyList();
        int memberTotalCount = daoTest.selectMemberTotalCount(memberDTO);
        if(memberTotalCount > 0){
            memberList = daoTest.selectMemberList(memberDTO);
        }

        return memberList;
    }

    @Override
    public MemberDTO getLogin(MemberDTO memberDTO) throws NoSuchAlgorithmException {
        CryptoUtil cryptoUtil =new CryptoUtil();
        String enPass = cryptoUtil.sha256(memberDTO.getLoginPw());
        memberDTO.setLoginPw(enPass);
        return  daoTest.selectLogin(memberDTO);
    }

}
