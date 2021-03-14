package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.EmployeeDTO;
import com.daelim.transactions.dto.afafDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DaoTest {

    public EmployeeDTO selectById(String id);

    public int insertMember(afafDTO testDTO);
}
