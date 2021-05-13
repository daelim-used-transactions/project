package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.BuyCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BuyCommentMapper {

    public int insertComment(BuyCommentDTO params);

    public BuyCommentDTO selectCommentDetail(Long idx);

    public int updateComment(BuyCommentDTO params);

    public int deleteComment(Long idx);

    public List<BuyCommentDTO> selectCommentList(BuyCommentDTO params);

    public int selectCommentTotalCount(BuyCommentDTO params);
}
