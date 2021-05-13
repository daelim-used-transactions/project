package com.daelim.transactions.service;

import com.daelim.transactions.dto.BuyCommentDTO;
import com.daelim.transactions.mapper.BuyCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service @RequiredArgsConstructor
public class BuyCommentServiceImpl implements BuyCommentService{


    private final BuyCommentMapper commentMapper;

    @Override
    public boolean registerComment(BuyCommentDTO params) {
        int queryResult = 0;

        if (params.getIdx() == null) {
            queryResult = commentMapper.insertComment(params);
        } else {
            queryResult = commentMapper.updateComment(params);
        }

        return (queryResult == 1) ? true : false ;
    }

    @Override
    public boolean deleteComment(Long idx) {
        int queryResult = 0;

        BuyCommentDTO comment = commentMapper.selectCommentDetail(idx);

        if (comment != null && "N".equals(comment.getDeleteYn())) {
            queryResult = commentMapper.deleteComment(idx);
        }

        return (queryResult == 1) ? true : false ;
    }

    @Override
    public List<BuyCommentDTO> getCommentList(BuyCommentDTO params) {
        List<BuyCommentDTO> commentList = Collections.emptyList();

        int commentTotalCount = commentMapper.selectCommentTotalCount(params);
        if (commentTotalCount > 0) {
            commentList = commentMapper.selectCommentList(params);
        }

        return commentList;
    }
}
