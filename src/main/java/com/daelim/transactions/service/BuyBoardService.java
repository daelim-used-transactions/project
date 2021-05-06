package com.daelim.transactions.service;

import com.daelim.transactions.dto.AttachDTO;
import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.BuyBoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BuyBoardService {
    public boolean registerBoard(BuyBoardDTO board);

}
