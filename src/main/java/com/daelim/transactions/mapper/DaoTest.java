package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DaoTest {

    public int insertMember(MemberDTO memberDTO);

    public String selectById(String id);
    public String selectByNickname(String userNickname);

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
     * 이름과 이메일을 통해
     * 아이디 찾기
     */
    public MemberDTO findLoginId(MemberDTO memberDTO);

    /**
     *
     * @param memberDTO
     * @return
     * 아이디와 이메일을 통해
     * 비밀번호 찾기
     */
    public MemberDTO findLoginPass(MemberDTO memberDTO);

    /**
     *
     * @param memberDTO
     * @return
     * 아이디에 맞는 비밀번호를 찾아 변경
     */
    public int updateByPass(MemberDTO memberDTO);


    /**
     * 프로필사진 변경에 대한 업데이트
     * */
    public int updateByProfile(MemberDTO memberDTO);


    /**
     *  아이디로 모든 정보 찾기
     * */
    public MemberDTO allInfoById(String memId);

    /**
     *  회원탈퇴
     */
    public int deleteMember(String params);
}
