package com.daelim.transactions.service;

import com.daelim.transactions.dto.AttachDTO;
import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.BuyBoardDTO;
import com.daelim.transactions.mapper.DaoAttach;
import com.daelim.transactions.mapper.DaoBoard;
import com.daelim.transactions.mapper.DaoBuyBoard;
import com.daelim.transactions.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service @RequiredArgsConstructor
public class BuyBoardServiceImpl implements BuyBoardService {

    private final DaoBuyBoard daoBuyBoard;


    @Override
    public boolean registerBoard(BuyBoardDTO board) {
        return daoBuyBoard.insertBoard(board) == 1;  //DB에 들어갔다면 True
    }




}

