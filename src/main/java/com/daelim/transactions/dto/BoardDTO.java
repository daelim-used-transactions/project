package com.daelim.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class BoardDTO extends CommonDTO {
    //게시글 번호
    private int boardIdx;

    //아이디(작성자)
    private String loginId;

    //제목
    private String title;

    //가격
    private String price;

    //카테고리
    private String category;

    //내용
    private String contents;

    private int likeCount;

    public BoardDTO(){

    }
}
