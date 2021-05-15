package com.daelim.transactions.controller;

import com.daelim.transactions.dto.*;
import com.daelim.transactions.service.BoardService;
import com.daelim.transactions.service.BuyBoardService;
import com.daelim.transactions.service.likeAndView.BuyLikeAndViewService;
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
    @Autowired
    BuyLikeAndViewService buyLikeAndViewService;

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
    public String showBuyList(@ModelAttribute("params")BuyBoardDTO params, Model model){
        List<BuyBoardDTO> boardList = buyBoardService.getBuyBoardList(params);
        List<IttachDTO> ittachList = buyBoardService.getIttachList(boardList);
        model.addAttribute("boardList", boardList);
        model.addAttribute("searchType", 3);
        model.addAttribute("ittachList",ittachList);
        return "buyList/buyList";
    }

    @GetMapping(value ="/main/buyList/view")
    public String openDetailBoard(@RequestParam(value="idx", required = false)Long idx, Model model , HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (idx == null) {
            return "redirect:/main/buyList";
        }
        buyLikeAndViewService.addBuyBoardViews(idx);
        BuyBoardDTO buyBoard = buyBoardService.getBoardDetail(idx);
        List<IttachDTO> ittachList = buyBoardService.getIttachList(idx);
        boolean likeCheck = false;

        if (buyBoard == null || "Y".equals(buyBoard.getDeleteYn())) {
            return "redirect:/main/buyList";
        }

        Cookie[] cookie = request.getCookies();
        String idxId = (String) request.getSession().getAttribute("idxId");
        String str = (String) request.getSession().getAttribute("str");
        MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
        String copyList ="";



        if(member != null){
            model.addAttribute("member", member);
            BuyLikeDTO buyLike = new BuyLikeDTO();
            buyLike.setBoardIdx(Math.toIntExact(idx));
            buyLike.setLoginId(member.getLoginId());
            likeCheck = buyLikeAndViewService.getBuyLikes(buyLike);
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
        model.addAttribute("buyLikeCount", buyLikeAndViewService.buyLikeTotalCount(Math.toIntExact(idx)));
        model.addAttribute("ittachList", ittachList);
        return "buyList/forSale";
    }

    @PostMapping(value ="/buyLikeAjax")
    @ResponseBody
    public int buyLikeAjaxTest(@RequestBody BuyLikeDTO buyLike){//Stirng,Object로 해도 되네
//        BuyLikeDTO buyLike = new BuyLikeDTO();
//        buyLike.setLoginId((String) param.get("sessionId"));
//        buyLike.setBoardIdx((Integer) param.get("idx"));
//        System.out.println(param.get("sessionId") +"굿굿" + param.get("idx"));

        return buyLikeAndViewService.addBuyLikes(buyLike);
    }

    @PostMapping(value ="/buyLikeCancel")
    @ResponseBody
    public int buyLikeAjaxCancel(@RequestBody BuyLikeDTO buyLike){//Stirng,Object로 해도 되네
        System.out.println("찜 삭제... "+ buyLike.getLoginId());
        return buyLikeAndViewService.removeBuyLikes(buyLike);
    }

}