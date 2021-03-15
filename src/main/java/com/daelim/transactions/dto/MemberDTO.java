package com.daelim.transactions.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter@Getter@Builder@AllArgsConstructor
public class MemberDTO {
    //아이디
    private String loginId;

    //비밀번호
    private String loginPw;

    //이름
    private String name;

    //이메일
    private String email;

    //닉네임
    private String nickName;

    //계정 상태
    private String status;

    //계정 생성 시간
    private Date createTime;

    // 로그인 여부
    private String loginFlag;

    // 프로필
    private String profile;

    public MemberDTO(){

    }
}
