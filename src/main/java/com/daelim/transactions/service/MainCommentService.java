package com.daelim.transactions.service;

import com.daelim.transactions.dto.BuyCommentDTO;
import com.daelim.transactions.dto.MainCommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MainCommentService {
    public boolean registerComment(MainCommentDTO params);

    public boolean deleteComment(Long idx);

    public List<MainCommentDTO> getCommentList(MainCommentDTO params);
}
