package com.daelim.transactions.service;

import com.daelim.transactions.dto.EmployeeDTO;

import java.util.Optional;

public interface ServiceTest {

    Optional<EmployeeDTO> optToEmpInfo(String id);
}
