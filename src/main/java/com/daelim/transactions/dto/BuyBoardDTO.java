package com.daelim.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class BuyBoardDTO {
    //게시글 번호
    private int boardIdx;

    //아이디(작성자)
    private String loginId;

    //제목
    private String title;

    //카테고리
    private String category;

    //내용
    private String contents;

    //삭제 여부
    private String deleteYn;

    //입력 시간
    private LocalDateTime insertTime;

    //삭제 시간
    private LocalDateTime  deleteTime;

    public BuyBoardDTO(){

    }
}
