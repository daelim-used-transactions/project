package com.daelim.transactions.service;

import com.daelim.transactions.dto.AttachDTO;
import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.BuyBoardDTO;
import com.daelim.transactions.dto.PaginationInfo;
import com.daelim.transactions.mapper.DaoAttach;
import com.daelim.transactions.mapper.DaoBoard;
import com.daelim.transactions.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Access;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service @RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final DaoBoard daoBoard;

    private final FileUtils fileUtils;

    private final DaoAttach daoAttach;


    @Override
    public boolean registerBoard(BoardDTO board) {
        return daoBoard.insertBoard(board) == 1;  //DB에 들어갔다면 True
    }

    @Override
    public boolean registerBoard(BoardDTO board, MultipartFile[] files) {
        int queryResult = 1;

        if (registerBoard(board) == false) {
            return false;
        }
        List<AttachDTO> fileList = fileUtils.uploadBoardFiles(files, board.getBoardIdx());
        if (CollectionUtils.isEmpty(fileList) == false) {
            queryResult = daoAttach.insertAttach(fileList);
            if (queryResult < 1) {
                queryResult = 0;
            }
        }

        return (queryResult > 0);
    }

    @Override
    public List<BoardDTO> getBoardList() {
        List<BoardDTO> boardList = Collections.emptyList();

        int boardTotalCount = daoBoard.selectBoardTotalCount();

        if (boardTotalCount > 0) {
            boardList = daoBoard.selectBoardList(0);
        }

        return boardList;
    }

    /**
     * @param count-> 페이징을 위한 카운트
     * @return BoardDTO list형태로 반환
     */
    @Override
    public List<BoardDTO> getBoardList(int count) {
        List<BoardDTO> boardList = Collections.emptyList();

        int boardTotalCount = daoBoard.selectBoardTotalCount();

        if (boardTotalCount > 0) {
            boardList = daoBoard.selectBoardList(count);
        }

        return boardList;
    }


    @Override
    public List<BoardDTO> getBoardList(String loginId) {
        List<BoardDTO> boardList = Collections.emptyList();

        int boardTotalCount = daoBoard.selectBoardMemberCount(loginId);

        if (boardTotalCount > 0) {
            boardList = daoBoard.selectBoardListById(loginId);
        }

        return boardList;
    }

    @Override
    public List<AttachDTO> getAttachList( ) {
        List<AttachDTO> attachList = Collections.emptyList();

        int boardTotalCount = daoAttach.selectAttachTotalCount();

        if (boardTotalCount > 0) {
            attachList = daoAttach.selectAttachList();
        }

        return attachList;
    }

    @Override
    public List<AttachDTO> getAttachList(List<BoardDTO> boardList) {
        List<AttachDTO> attachList = Collections.emptyList();

        int boardTotalCount = daoAttach.selectAttachIdxCount(boardList);

        if (boardTotalCount > 0) {
            attachList = daoAttach.selectAttachListByBoardIdx(boardList);
            System.out.println(attachList.get(0));
        }

        return attachList;
    }

    @Override
    public List<BoardDTO> getSearchBoardList(BoardDTO params) {

        List<BoardDTO> boardList = Collections.emptyList();
        Map<String, Object> map = new HashMap<>();
        map.put("params", params);
        map.put("searchType", 2);
        int boardTotalCount = daoBoard.selectBoardTotalCount2(map);
        System.out.println("검색값에 해당하는 수 : " + boardTotalCount);
        PaginationInfo paginationInfo = new PaginationInfo(params);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        params.setPaginationInfo(paginationInfo);
        if (boardTotalCount > 0) {
            boardList = daoBoard.selectBoardListSearch(params);
        }

        return boardList;
    }

    @Override
    public List<BoardDTO> getCategoryBoardList(BoardDTO params) {

        List<BoardDTO> boardList = Collections.emptyList();
        Map<String, Object> map = new HashMap<>();
        map.put("params", params);
        map.put("searchType", 1);
        int boardTotalCount = daoBoard.selectBoardTotalCount2(map);
        System.out.println("카테고리에 해당하는 수 : " + boardTotalCount);
        PaginationInfo paginationInfo = new PaginationInfo(params);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        params.setPaginationInfo(paginationInfo);
        if (boardTotalCount > 0) {
            boardList = daoBoard.selectBoardListCategory(params);
        }

        return boardList;
    }

    /**
     *  boardIdx에 맞는 글 가져오기
     * */
    @Override
    public BoardDTO getBoardDetail(long idx) {

        return daoBoard.selectBoardDetail(idx);
    }

}//end class

