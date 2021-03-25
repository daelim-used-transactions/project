package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.EmployeeDTO;
import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.dto.afafDTO;
import org.apache.ibatis.annotations.Mapper;

import java.lang.reflect.Member;
import java.util.List;

@Mapper
public interface DaoTest {

    public EmployeeDTO selectById(String id);

    public int insertMember(MemberDTO memberDTO);

    public int selectMemberTotalCount(MemberDTO memberDTO);

    /**
     * 로그인시 디비에서 정보 가져오기
     * */
    public MemberDTO selectLogin(MemberDTO memberDTO);


    public List<MemberDTO>selectMemberList(MemberDTO memberDTO);

    /**
     *
     * @param memberDTO
     * @return
     * 아이디 찾기
     */
    public MemberDTO findLoginId(MemberDTO memberDTO);

    public MemberDTO findLoginPass(MemberDTO memberDTO);

    public int updateByPass(MemberDTO memberDTO);

}
