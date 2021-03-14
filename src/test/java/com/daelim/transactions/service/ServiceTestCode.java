package com.daelim.transactions.service;

import com.daelim.transactions.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ServiceTestCode {

    @Autowired
    ServiceTest serviceTest;

    @Test
    void OptEmpInfoTest(){
        Optional<EmployeeDTO> optEmpInfo = serviceTest.optToEmpInfo("id");

        if(!optEmpInfo.isPresent()){
            // id가 존재하지 않을 시
            System.out.println("비었다..");
        }else{
            EmployeeDTO empInfo = optEmpInfo.get();
            System.out.println(empInfo.getLoginID());

        }
    }
}
