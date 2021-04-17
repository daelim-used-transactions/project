package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.AttachDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DaoAttach {
    public int insertAttach(List<AttachDTO> attachList);
}
