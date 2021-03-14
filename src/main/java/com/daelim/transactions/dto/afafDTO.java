package com.daelim.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder @AllArgsConstructor
public class afafDTO {
    private String id;
    private String pass;
    private String email;

    public afafDTO() {

    }
}
