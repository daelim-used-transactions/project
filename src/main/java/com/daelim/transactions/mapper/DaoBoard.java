package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.BuyBoardDTO;
import com.daelim.transactions.dto.SaleLikeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface DaoBoard {

    public int insertBoard(BoardDTO params);

    public BoardDTO selectBoard(int boardIdx);

    public int updateBoard(BoardDTO params);

    public int deleteBoard(int boardIdx);

    //메인 페이징
    public List<BoardDTO> selectBoardList(int count);
    
    //검색 결과 반환
    //검색, 카테고리 if문으로 따로 2개 나누는 방법이 더 간결하지만 보류
    public List<BoardDTO> selectBoardListSearch(BoardDTO params);
    //카테고리 반환
    public List<BoardDTO> selectBoardListCategory(BoardDTO params);

    public List<BoardDTO> selectBoardList();

    public int selectBoardTotalCount();
    //1번째 : BoardDTO parmas, 2번째 : searchType : 1이면 카테고리 2면 검색
    public int selectBoardTotalCount2(Map<String,Object> map);
    public List<BoardDTO> selectBoardListById(String loginId);

    public int selectBoardMemberCount(String loginId);


    /**
     *  상세페이지 가져오기
     * */
    public BoardDTO selectBoardDetail(Long idx);

    /**
     * 마이페이지 위시리스트(찜 한 팔아요 게시글)
     */
    public List<BoardDTO> selectLikeBoard(List<SaleLikeDTO> params);
    /**
     * 마이페이지 게시글 삭제
     */
    public void deleteBoardList(List<Integer> params);
}
