package com.daelim.transactions.service;

import com.daelim.transactions.dto.MemberDTO;

import java.security.NoSuchAlgorithmException;

public interface ProfileService {
    public int updateByPass(MemberDTO memberDTO) throws NoSuchAlgorithmException;
    public boolean removeMember(String params);
}
