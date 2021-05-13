package com.daelim.transactions.controller;

import com.daelim.transactions.dto.*;
import com.daelim.transactions.service.BoardService;
import com.daelim.transactions.service.BuyBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.Arrays.asList;

@Controller
public class BuyBoardController {


    @Autowired
    MainController mainController;
    @Autowired
    BuyBoardService buyBoardService;

    /**
     * @param board  -> 게시글 등록 form 태그로 전달되는 객체
     * @RequestParam(value = "files") MultipartFile[] files -> 파일 저장을 위한 객체
     */
    @PostMapping(value="/main/buyUpload.do/register")
    public String registerBoard(BuyBoardDTO board, @RequestParam(value = "files") MultipartFile[] files, HttpServletRequest request) {
        MemberDTO member = mainController.commonSession(request);
        board.setLoginId(member.getLoginId());
        board.setInsertTime(LocalDateTime.now());
        if(buyBoardService.registerBoard(board, files)){
            //insert 성공
        }else{
            //실패
        }

        return "redirect:/main/buyList";
    }


//    @GetMapping(value="/main/buyList.do")
//    public String registerBoard(){
//        return "buyUpload";
//    }


    @GetMapping(value="/main/buyList")
    public String showBuyList( Model model){

        List<BuyBoardDTO> boardList = buyBoardService.getBoardList();
        System.out.println("여기는 구해요 게시판 인데요");
        System.out.println("보더리스트 뭐냐 " + boardList);
        model.addAttribute("boardList", boardList);
        return "buyList/buyList";
    }

    @GetMapping(value ="/main/buyList/view")
    public String openDetailBoard(@RequestParam(value="idx", required = false)Long idx, Model model , HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (idx == null) {
            return "redirect:/main/buyList";
        }
        buyBoardService.addBoardViews(idx);
        BuyBoardDTO buyBoard = buyBoardService.getBoardDetail(idx);
<<<<<<< Updated upstream
        boolean likeCheck = false;
=======

>>>>>>> Stashed changes
        if (buyBoard == null || "Y".equals(buyBoard.getDeleteYn())) {
            return "redirect:/main/buyList";
        }

        Cookie[] cookie = request.getCookies();
        String idxId = (String) request.getSession().getAttribute("idxId");
        String str = (String) request.getSession().getAttribute("str");
        MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
        String copyList ="";

        if(member != null && buyBoardService.getBuyLikes(idx)){
            likeCheck = true;
        }

        if(member != null){
            model.addAttribute("member", member);
        }
        System.out.println("메인 세션 아이디 : "+idxId);
        if(cookie != null && idxId !=null ){

            str += buyBoard.getContents()+",";
            System.out.println(" 보자보자 : " + str);
            request.getSession().removeAttribute("str");
            request.getSession().setAttribute("str", str);

            List<String> strList  = asList(str.split(","));
            if(strList.size()>3){
                int start = strList.size()-3;
                for(int i=start; i< strList.size(); i++){
                    copyList+=strList.get(i)+",";
                }
                Cookie cookie1 = new Cookie(idxId,URLEncoder.encode(copyList,"utf-8"));
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
        model.addAttribute("board", buyBoard);
        model.addAttribute("likeCheck", likeCheck);
        return "buyList/forSale";
    }

    @PostMapping(value ="/buyLikeAjax")
    @ResponseBody
    public Object buyLikeAjaxTest(@RequestBody Map<String,Object> param){//Stirng,Object로 해도 되네
        String nickNameCheck = null;
        System.out.println(param.get("sessionId") +"굿굿" + param.get("idx"));
        BuyLikeDTO buyLike = new BuyLikeDTO();
        buyLike.setLoginId((String) param.get("sessionId"));
        buyLike.setBoardIdx((Integer) param.get("idx"));
        buyBoardService.addBuyLikes(buyLike);

        return null;
    }

    @PostMapping(value ="/buyLikeCancel")
    @ResponseBody
    public Object buyLikeAjaxCancel(@RequestBody Map<String,Object> param){//Stirng,Object로 해도 되네
        String nickNameCheck = null;
        System.out.println("굿굿" + param.get("idx"));
        buyBoardService.removeBuyLikes((Integer) param.get("idx"));

        return null;
    }

}