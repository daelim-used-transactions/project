package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface DaoTest {
    public EmployeeDTO selectById(String id);
}
