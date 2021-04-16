package com.daelim.transactions.controller;

import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class BoardController {

    @Autowired
    MainController  mainController;
    @Autowired
    BoardService boardService;

    @PostMapping(value="/main/product.do/register")
    public String registerBoard(BoardDTO board, HttpServletRequest request){
        MemberDTO member = mainController.commonSession(request);
        board.setLoginId(member.getLoginId());
        board.setInsertTime(LocalDateTime.now());
        if(boardService.registerBoard(board)){
            //insert 성공
        }else{
            //실패
        }
        return "redirect:/main";
    }
}
