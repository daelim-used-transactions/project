package com.daelim.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BuyCommentDTO extends CommonDTO{

    //댓글 번호
    private Long idx;

    //게시판 번호
    private Long boardIdx;

    //댓글 내용
    private String content;

    //쓴 사람
    private String writer;

    public BuyCommentDTO(){

    }
}
