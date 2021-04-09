package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DaoBoard {
    public int insertBoard(BoardDTO params);

    public BoardDTO selectBoard(int boardIdx);

    public int updateBoard(BoardDTO params);

    public int deleteBoard(int boardIdx);
    /**
     * 글 작성시
     * */
}
