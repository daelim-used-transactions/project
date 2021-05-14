package com.daelim.transactions.service;


import com.daelim.transactions.dto.BuyCommentDTO;
import com.daelim.transactions.dto.MainCommentDTO;
import com.daelim.transactions.mapper.MainCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service @RequiredArgsConstructor
public class MainCommentServiceImpl implements MainCommentService{

    private final MainCommentMapper mainCommentMapper;


    @Override
    public boolean registerComment(MainCommentDTO params) {
        int queryResult = 0;

        if (params.getIdx() == null) {
            queryResult = mainCommentMapper.insertComment(params);
        } else {
            queryResult = mainCommentMapper.updateComment(params);
        }

        return (queryResult == 1) ? true : false ;
    }

    @Override
    public boolean deleteComment(Long idx) {
        int queryResult = 0;

        MainCommentDTO comment = mainCommentMapper.selectCommentDetail(idx);

        if (comment != null && "N".equals(comment.getDeleteYn())) {
            queryResult = mainCommentMapper.deleteComment(idx);
        }

        return (queryResult == 1) ? true : false ;
    }

    @Override
    public List<MainCommentDTO> getCommentList(MainCommentDTO params) {
        List<MainCommentDTO> commentList = Collections.emptyList();

        int commentTotalCount = mainCommentMapper.selectCommentTotalCount(params);
        if (commentTotalCount > 0) {
            commentList = mainCommentMapper.selectCommentList(params);
        }

        return commentList;
    }
}
