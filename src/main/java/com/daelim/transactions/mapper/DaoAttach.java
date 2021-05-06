package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.AttachDTO;
import com.daelim.transactions.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DaoAttach {
    public int insertAttach(List<AttachDTO> attachList);

    public List<AttachDTO> selectAttachList();

    public int selectAttachTotalCount();

    public int selectAttachOneCount(int boardIdx);

    public int selectAttachIdxCount(List<BoardDTO> boardList);

    public List<AttachDTO> selectAttachListByBoardIdx(List<BoardDTO> boardList);

    public AttachDTO selectAttachOne(@Param("boardIdx")int boardIdx, @Param("count")int count);
}
