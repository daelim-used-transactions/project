package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.BuyBoardDTO;
import com.daelim.transactions.dto.BuyLikeDTO;
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

    //메인 페이징
    public List<BuyBoardDTO> selectBoardList(BuyBoardDTO params);

    //검색 결과 반환
    public List<BuyBoardDTO> selectBoardListSearch(BuyBoardDTO params);
    //카테고리 반환
    public List<BuyBoardDTO> selectBoardListCategory(BuyBoardDTO params);

    public int selectBoardTotalCount();
    //1번째 : BuyBoardDTO parmas, 2번째 : searchType : 1이면 카테고리 2면 검색
    public int selectBoardTotalCount2(Map<String,Object> map);
    public List<BuyBoardDTO> selectBoardListById(String loginId);

    public int selectBoardMemberCount(String loginId);

    public void updateBoardViews(long boardIdx);
    public void updateBoardBuyLike(Map<String,Integer> map);

    public void insertBuyLike(BuyLikeDTO param);
    public void deleteBuyLike(long idx);
    public BuyLikeDTO selectBuyLike(long idx);
    public int selectBuyLikeCount(long idx);


    /**
     *  상세페이지 가져오기
     * */
    public BuyBoardDTO selectBoardDetail(Long idx);
}
