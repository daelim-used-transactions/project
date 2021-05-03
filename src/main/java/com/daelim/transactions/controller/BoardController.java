package com.daelim.transactions.controller;

import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class BoardController {

    @Autowired
    MainController mainController;
    @Autowired
    BoardService boardService;

    /**
     * @param board -> 게시글 등록 form 태그로 전달되는 객체
     * @RequestParam(value = "files") MultipartFile[] files -> 파일 저장을 위한 객체
     */
    @PostMapping(value="/main/product.do/register")
    public String registerBoard(BoardDTO board, @RequestParam(value = "files") MultipartFile[] files, HttpServletRequest request){
        MemberDTO member = mainController.commonSession(request);
        board.setLoginId(member.getLoginId());
        board.setInsertTime(LocalDateTime.now());
        if(boardService.registerBoard(board, files)){
            //insert 성공
        }else{
            //실패
        }

        return "redirect:/main";
    }

}