package com.daelim.transactions.service;

import com.daelim.transactions.dto.EmployeeDTO;
import com.daelim.transactions.mapper.DaoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceTestImpl implements ServiceTest {

    @Autowired
    private DaoTest daoTest;

    @Override
    public Optional<EmployeeDTO> optToEmpInfo(String id) {
        //EmployeeDTO emp = dao.selectById(id);
        Optional<EmployeeDTO> optEmp = Optional.ofNullable(daoTest.selectById(id));
        return optEmp;
    }
}
