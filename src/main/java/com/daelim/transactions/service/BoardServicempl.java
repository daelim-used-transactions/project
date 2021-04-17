package com.daelim.transactions.service;

import com.daelim.transactions.dto.AttachDTO;
import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.mapper.DaoAttach;
import com.daelim.transactions.mapper.DaoBoard;
import com.daelim.transactions.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Access;
import java.util.List;

@Service
public class BoardServicempl implements BoardService {

    @Autowired
    DaoBoard daoBoard;
    @Autowired
    FileUtils fileUtils;
    @Autowired
    DaoAttach daoAttach;

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
}

