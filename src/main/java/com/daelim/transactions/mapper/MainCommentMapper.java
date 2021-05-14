package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.MainCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainCommentMapper {

    public int insertComment(MainCommentDTO params);

    public MainCommentDTO selectCommentDetail(Long idx);

    public int updateComment(MainCommentDTO params);

    public int deleteComment(Long idx);

    public List<MainCommentDTO> selectCommentList(MainCommentDTO params);

    public int selectCommentTotalCount(MainCommentDTO params);
}
