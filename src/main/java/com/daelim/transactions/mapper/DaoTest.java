package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.EmployeeDTO;
import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.dto.afafDTO;
import org.apache.ibatis.annotations.Mapper;

import java.lang.reflect.Member;
import java.util.List;

@Mapper
public interface DaoTest {

    public int insertMember(MemberDTO memberDTO);

    public String selectById(String id);

    public int selectMemberTotalCount(MemberDTO memberDTO);

    /**
     * 로그인시 디비에서 정보 가져오기
     * */
    public MemberDTO selectLogin(MemberDTO memberDTO);


    public List<MemberDTO>selectMemberList(MemberDTO memberDTO);



}
