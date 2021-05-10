package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.BuyBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface DaoBuyBoard {

    public int insertBoard(BuyBoardDTO params);

    public BuyBoardDTO selectBoard(int boardIdx);

    public int updateBoard(BuyBoardDTO params);

    public int deleteBoard(int boardIdx);

    public List<BuyBoardDTO> selectBoardList();

    public int selectBoardTotalCount();

    public List<BuyBoardDTO> selectBoardListById(String loginId);

    public int selectBoardMemberCount(String loginId);


    /**
     *  상세페이지 가져오기
     * */
    public BuyBoardDTO selectBoardDetail(Long idx);
}
