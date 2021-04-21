package com.daelim.transactions.service;

import com.daelim.transactions.dto.MemberDTO;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ServiceTest {

    //Optional<EmployeeDTO> optToEmpInfo(String id);

    int memberInsert(MemberDTO member) throws NoSuchAlgorithmException;

    boolean isDuplicatedId(String id);
    boolean isDuplicatedNickname(String userNickname);

    public List<MemberDTO> getMemberList(MemberDTO memberDTO);

    public MemberDTO getLogin(MemberDTO memberDTO) throws NoSuchAlgorithmException;

    public MemberDTO getFindId(MemberDTO memberDTO);

    public MemberDTO getFindPass(MemberDTO memberDTO,String changPass) throws NoSuchAlgorithmException;

    public String putRandomPass();

    public boolean changProfile(MemberDTO memberDTO, MultipartFile files);

    public MemberDTO getAllInfo(String memId);
}
