package com.daelim.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class IttachDTO extends CommonDTO{

    /** 파일 번호 (PK) */
    private int idx;

    /** 게시글 번호 (FK) */
    private int boardIdx;

    /** 저장 파일명 */
    private String saveName;

    /** 파일 크기 */
    private long size;

    /**파일 카운트(갯수) 웹에서 사용해야함**/
    private int count;

    public IttachDTO() {

    }
}
