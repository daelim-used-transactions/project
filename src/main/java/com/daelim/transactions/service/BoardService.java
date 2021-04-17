package com.daelim.transactions.service;

import com.daelim.transactions.dto.BoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    public boolean registerBoard(BoardDTO board);
    public boolean registerBoard(BoardDTO board, MultipartFile[] files);
}
