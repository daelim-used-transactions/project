package com.daelim.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SaleLikeDTO {

    private String loginId;

    private int boardIdx;

    public SaleLikeDTO(){

    }
}
