package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.BuyBoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class BuyMapperTestCode {

    @Autowired
    private DaoBuyBoard boardMapper;

    @Test
    public void testOfInsert() {
        BuyBoardDTO params = new BuyBoardDTO();
        params.setTitle("구해요 테스트 제목");
        params.setCategory("여성의류");
        params.setContents("구해요 테스트  내용");
        params.setDeleteYn("N");
        params.setInsertTime(LocalDateTime.now());
        params.setLoginId("geo98");

        int result = boardMapper.insertBoard(params);
        System.out.println("결과는 " + result + "입니다.");
    }
}
