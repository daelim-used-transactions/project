package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface DaoBoard {

    public int insertBoard(BoardDTO params);

    public BoardDTO selectBoard(int boardIdx);

    public int updateBoard(BoardDTO params);

    public int deleteBoard(int boardIdx);

    public List<BoardDTO> selectBoardList();

    public int selectBoardTotalCount();



}
