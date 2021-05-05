package com.daelim.transactions.service;

import com.daelim.transactions.dto.AttachDTO;
import com.daelim.transactions.dto.BoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    public boolean registerBoard(BoardDTO board);
    public boolean registerBoard(BoardDTO board, MultipartFile[] files);
    public List<BoardDTO> getBoardList();
    /**
     *  모든 게시판 찾기
     * */
    public List<AttachDTO> getAttachList( );

    /**
     * 아이디로 게시판 찾기
     * */
    public List<BoardDTO> getBoardList(String loginId);

    /**
     *  보더idx로 이미지 찾아오기
     * */
    public List<AttachDTO> getAttachList(List<BoardDTO> boardList);
}
