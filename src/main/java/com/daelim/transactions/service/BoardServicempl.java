package com.daelim.transactions.service;

import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.mapper.DaoBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;

@Service
public class BoardServicempl implements BoardService {

    @Autowired
    DaoBoard daoBoard;

    @Override
    public boolean registerBoard(BoardDTO board) {
        return daoBoard.insertBoard(board) == 1;  //DB에 들어갔다면 True
    }
}

