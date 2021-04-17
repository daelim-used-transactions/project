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

public class AttachDTO {

    /** 파일 번호 (PK) */
    private int idx;

    /** 게시글 번호 (FK) */
    private int boardIdx;

    /** 저장 파일명 */
    private String saveName;

    /** 파일 크기 */
    private long size;

    /**삭제 여부 */
    private String deleteYn;

    /** 입력 시간 */
    private LocalDateTime insertTime;

    /**삭제 시간 */
    private LocalDateTime  deleteTime;

    public AttachDTO() {

    }
}
