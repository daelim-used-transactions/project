package com.daelim.transactions.controller;

import com.daelim.transactions.dto.AttachDTO;
import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.BuyBoardDTO;
import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;

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

    @GetMapping(value ="/main/saleList/view")
    public String openDetailBoard(@RequestParam(value="idx", required = false)Long idx, Model model , HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (idx == null) {
            return "redirect:/main";
        }
        BoardDTO Board = boardService.getBoardDetail(idx);
//        List<AttachDTO> attachList = boardService.getAttachList(idx);

        if (Board == null || "Y".equals(Board.getDeleteYn())) {
            return "redirect:/main";
        }


        Cookie[] cookie = request.getCookies();
        String idxId = (String) request.getSession().getAttribute("idxId");
        String str = (String) request.getSession().getAttribute("str");
        MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
        String copyList ="";

        if(member != null){
            model.addAttribute("member", member);
        }
        System.out.println("메인 세션 아이디 : "+idxId);
        if(cookie != null && idxId !=null ){

            str += Board.getContents()+",";
            System.out.println(" 보자보자 : " + str);
            request.getSession().removeAttribute("str");
            request.getSession().setAttribute("str", str);

            List<String> strList  = asList(str.split(","));
            if(strList.size()>3){
                int start = strList.size()-3;
                for(int i=start; i< strList.size(); i++){
                    copyList+=strList.get(i)+",";
                }
                Cookie cookie1 = new Cookie(idxId, URLEncoder.encode(copyList,"utf-8"));
                System.out.println("이게 되나 ? " + copyList);
                cookie1.setPath("/");
                cookie1.setMaxAge(24*60*60);
                response.addCookie(cookie1);
            }
            else {
                Cookie cookie1 = new Cookie(idxId,URLEncoder.encode(str,"utf-8"));
                System.out.println("이게 되나 ? " + str);
                cookie1.setPath("/");
                cookie1.setMaxAge(24*60*60);
                response.addCookie(cookie1);
            }
            System.out.println("좀 되라 ㅡㅡ "+strList.size());
            System.out.println("좀 되라 ㅡㅡ리스트야 "+copyList);
        }
        System.out.println("여기는 상세페이지요");
        model.addAttribute("board", Board);
        return "saleList/saleDetail";
    }
}