package com.daelim.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder @AllArgsConstructor
public class EmployeeDTO {

    private String loginID;
    private String joinTime;
    private String name;
    private String tel;
    private String email;
    private String birthDay;
    private String gender;
    private String age;
    private String nativeFlag;
    private String type;
    private String postcode;
    private String newAddr;
    private String oldAddr;
    private String detailAddr;
    private String part;
    private String rank;
    private String empStatus;
    private String contractStatus;
    private String bankCode;
    private String bankAccountNumber;
    private String salary;
    private String leaveTime;
    private String deleteFlag;
    private String createTime;
    private String updateTime;
    private String deleteTime;

    public EmployeeDTO() {
    }
}
