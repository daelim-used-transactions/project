package com.daelim.transactions.service;

import com.daelim.transactions.dto.EmployeeDTO;
import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.dto.afafDTO;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface ServiceTest {

    //Optional<EmployeeDTO> optToEmpInfo(String id);

    int memberInsert(MemberDTO member) throws NoSuchAlgorithmException;

    boolean isDuplicatedId(String id);

    public List<MemberDTO> getMemberList(MemberDTO memberDTO);

    public MemberDTO getLogin(MemberDTO memberDTO) throws NoSuchAlgorithmException;

    public MemberDTO getFindId(MemberDTO memberDTO);

    public MemberDTO getFindPass(MemberDTO memberDTO,String changPass) throws NoSuchAlgorithmException;

    public String putRandomPass();
}
