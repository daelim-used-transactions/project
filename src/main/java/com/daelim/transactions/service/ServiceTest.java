package com.daelim.transactions.service;

import com.daelim.transactions.dto.EmployeeDTO;
import com.daelim.transactions.dto.afafDTO;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface ServiceTest {

    Optional<EmployeeDTO> optToEmpInfo(String id);

    int optMemberInsert(afafDTO testDTO) throws NoSuchAlgorithmException;
}
