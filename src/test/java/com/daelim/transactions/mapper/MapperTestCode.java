package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class MapperTestCode {

    @Autowired
    private DaoBoard boardMapper;

    @Test
    public void testOfInsert() {
        BoardDTO params = new BoardDTO();
        params.setTitle("테스트 게시글 제목2");
        params.setContents("테스트 게시글 내용2");
        params.setDeleteYn("N");
        params.setPrice(2400);
        params.setInsertTime(LocalDateTime.now());
        params.setLoginId("rlaxodid123");

        int result = boardMapper.insertBoard(params);
        System.out.println("결과는 " + result + "입니다.");
    }
}
