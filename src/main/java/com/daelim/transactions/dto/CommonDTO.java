package com.daelim.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class CommonDTO extends Criteria{

    /** 페이징 정보 */
    private PaginationInfo paginationInfo;

    //삭제 여부
    private String deleteYn;

    //입력 시간
    private LocalDateTime insertTime;

    //삭제 시간
    private LocalDateTime  deleteTime;

    //조회수
    private int views;

    public CommonDTO(){

    }
}
