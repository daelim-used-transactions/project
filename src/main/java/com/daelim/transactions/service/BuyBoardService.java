package com.daelim.transactions.service;

import com.daelim.transactions.dto.AttachDTO;
import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.BuyBoardDTO;
import com.daelim.transactions.dto.IttachDTO;
import com.daelim.transactions.dto.BuyLikeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BuyBoardService {

    public boolean registerBoard(BuyBoardDTO board);
    public boolean registerBoard(BuyBoardDTO board, MultipartFile[] files);
    public List<BuyBoardDTO> getBuyBoardList(BuyBoardDTO params);

    /**
     *  모든 게시판 찾기
     * */
    public List<IttachDTO> getIttachList( );
    public List<IttachDTO> getIttachList(long idx);
    public List<IttachDTO> getIttachList(List<BuyBoardDTO> boardList);

    /**
     *  아이디로 게시판 찾기
     * */
    public List<BuyBoardDTO> getBoardList(String loginId);

    /**
     *  보더idx로 이미지 찾아오기
     * */
    public List<IttachDTO> getAttachList(List<BuyBoardDTO> boardList);
    public List<BuyBoardDTO> getBoardList(int count);

    public List<BuyBoardDTO> getSearchBoardList(BuyBoardDTO params);
    public List<BuyBoardDTO> getCategoryBoardList(BuyBoardDTO params);


    public BuyBoardDTO getBoardDetail(long idx);

    /**
     * 마이페이지 구해요 게시글 삭제
     */
    List<BuyBoardDTO> getAttachList();

    public void removeBuyBoardList(List<Integer> params);
}
