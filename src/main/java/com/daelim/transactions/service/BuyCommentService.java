package com.daelim.transactions.service;

import com.daelim.transactions.dto.BuyCommentDTO;

import java.util.List;

public interface BuyCommentService {

    public boolean registerComment(BuyCommentDTO params);

    public boolean deleteComment(Long idx);

    public List<BuyCommentDTO> getCommentList(BuyCommentDTO params);
}
