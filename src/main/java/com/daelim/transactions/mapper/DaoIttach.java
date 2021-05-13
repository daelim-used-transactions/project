package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.BuyBoardDTO;
import com.daelim.transactions.dto.IttachDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DaoIttach {
    public int insertIttach(List<IttachDTO> ittachList);

    public List<IttachDTO> selectIttachList();

    public int selectIttachTotalCount();

    public int selectIttachOneCount(int boardIdx);

    public int selectIttachIdxCount(List<BuyBoardDTO> boardList);

    public List<IttachDTO> selectIttachListByBoardIdx(List<BuyBoardDTO> boardList);

    public IttachDTO selectIttachOne(@Param("boardIdx")int boardIdx, @Param("count")int count);

}
