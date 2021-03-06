package com.daelim.transactions.controller;

import com.daelim.transactions.adapter.GsonLocalDateTimeAdapter;
import com.daelim.transactions.dto.*;
import com.daelim.transactions.service.BoardService;
import com.daelim.transactions.service.BuyBoardService;
import com.daelim.transactions.service.ServiceTest;
import com.daelim.transactions.service.likeAndView.LikeAndViewService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class MainController {

    @Autowired
    ServiceTest serviceTest;

    @Autowired
    BoardService boardService;
    @Autowired
    BuyBoardService buyBoardService;
    @Autowired
    LikeAndViewService likeAndViewService;

    @GetMapping(value = "/main")
    public String goMain(Model model, HttpServletRequest request) throws UnsupportedEncodingException {

        System.out.println("여기는 메인인데요");
        List<BoardDTO> boardList = boardService.getBoardList();
        List<AttachDTO> attachList = boardService.getAttachList();
        MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
        if(member != null){
            model.addAttribute("likeCount",likeAndViewService.SaleLikeTotalCount(member.getLoginId()));
        }
        List<String> cookieList = null;
        String cookieStr ="";
        String idxId = (String)request.getSession().getAttribute("idxId");
        Cookie[] cookie = request.getCookies();
        if(cookie != null){
            for (Cookie cc:cookie) {
                if(cc.getName().equals(idxId)){
                    cookieStr = URLDecoder.decode(cc.getValue(),"utf-8");
                }
            }
            System.out.println("안녕 "+ cookieStr);
            cookieList = Arrays.asList(cookieStr.split(","));
        }
        System.out.println(cookieList);
        model.addAttribute("boardList", boardList);
        model.addAttribute("attachList", attachList);
        model.addAttribute("cookieList",cookieList);
        return "main";
    }


    @GetMapping(value = "/main/myPage.do")
    public String toMyPage(HttpServletRequest request, Model model, HttpServletResponse response) {
        MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");

        List<BoardDTO> boardList = boardService.getBoardList(member.getLoginId());
        List<AttachDTO> attachList = boardService.getAttachList(boardList);
        List<BuyBoardDTO> buyBoardList = buyBoardService.getBoardList(member.getLoginId());
        String date = (String)member.getCreateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String createDate ="가입일 : " + date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " +date.substring(6) +"일 ";
        model.addAttribute("createTime",createDate);
        model.addAttribute("boardList", boardList);
        model.addAttribute("attachList", attachList);
        model.addAttribute("memNick",member.getNickName());
        model.addAttribute("memProfile",member.getProfile());
        model.addAttribute("buyListCount", buyBoardList.size());
        return "/myPage/myPage";
    }

    @GetMapping(value = "/main/myPage/buyList.do")
    public String fromMyPageToBuyList(HttpServletRequest request, Model model){
        MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");

        List<BuyBoardDTO> boardList = buyBoardService.getBoardList(member.getLoginId());
        List<IttachDTO> attachList = buyBoardService.getAttachList(boardList);
        List<BoardDTO> saleBoardList = boardService.getBoardList(member.getLoginId());
        String date = (String)member.getCreateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String createDate ="가입일 : " + date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " +date.substring(6) +"일 ";
        model.addAttribute("createTime",createDate);
        model.addAttribute("boardList", boardList);
        model.addAttribute("attachList", attachList);
        model.addAttribute("memNick",member.getNickName());
        model.addAttribute("memProfile",member.getProfile());
        model.addAttribute("saleListCount", saleBoardList.size());
        return "/myPage/myPageByBuyList";
    }

    @GetMapping(value = "/main/myPage/favorites.do")
    public String toFavorites(HttpServletRequest request, Model model){
        MemberDTO member = (MemberDTO) request.getSession().getAttribute("member");
        List<BoardDTO> boardList = boardService.getLikeBoardList(member.getLoginId());
        List<AttachDTO> attachList = boardService.getAttachList(boardList);
        List<BuyBoardDTO> buyBoardList = buyBoardService.getBoardList(member.getLoginId());
        List<BoardDTO> saleBoardList = boardService.getBoardList(member.getLoginId());
        String date = (String)member.getCreateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String createDate ="가입일 : " + date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " +date.substring(6) +"일 ";
        model.addAttribute("createTime",createDate);
        model.addAttribute("boardList", boardList);
        model.addAttribute("attachList", attachList);
        model.addAttribute("buyListCount", buyBoardList.size());
        model.addAttribute("saleListCount", saleBoardList.size());
        return "/myPage/favorites";
    }

    @GetMapping(value="/main/myPage/profile.do")
    public String toProfile(HttpServletRequest request, Model model){
        MemberDTO member = commonSession(request);
        MemberDTO newMemberPw = new MemberDTO();
        List<BuyBoardDTO> buyBoardList = buyBoardService.getBoardList(member.getLoginId());
        List<BoardDTO> saleBoardList = boardService.getBoardList(member.getLoginId());
        String date = (String)member.getCreateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String createDate ="가입일 : " + date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " +date.substring(6) +"일 ";
        model.addAttribute("createTime",createDate);
        model.addAttribute("memNick",member.getNickName());
        model.addAttribute("memProfile",member.getProfile());
        model.addAttribute("memName",member.getName());
        model.addAttribute("member",newMemberPw);
        model.addAttribute("buyListCount", buyBoardList.size());
        model.addAttribute("saleListCount", saleBoardList.size());
        System.out.println(member.getNickName());
        return "/myPage/profile";
    }

    @GetMapping(value="/main/myPage/memDel.do")
    public String toMemDelel(HttpServletRequest request, Model model){
        MemberDTO member = commonSession(request);
        MemberDTO newMemberPw = new MemberDTO();
        List<BuyBoardDTO> buyBoardList = buyBoardService.getBoardList(member.getLoginId());
        List<BoardDTO> saleBoardList = boardService.getBoardList(member.getLoginId());
        String date = (String)member.getCreateTime().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String createDate ="가입일 : " + date.substring(0, 4) + "년 " + date.substring(4, 6) + "월 " +date.substring(6) +"일 ";
        model.addAttribute("createTime",createDate);
        model.addAttribute("memNick",member.getNickName());
        model.addAttribute("memProfile",member.getProfile());
        model.addAttribute("memName",member.getName());
        model.addAttribute("member",newMemberPw);
        model.addAttribute("buyListCount", buyBoardList.size());
        model.addAttribute("saleListCount", saleBoardList.size());
        System.out.println(member.getNickName());
        return "/myPage/memDel";
    }


    @PostMapping(value="/profile/upload.do")
    public String uploadProfile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request){
        MemberDTO member = commonSession(request);
        System.out.println("member ="+member.getLoginId());
        boolean isChange = serviceTest.changProfile(member,file);
        request.getSession().removeAttribute("member");
        request.getSession().removeAttribute("memProfile");
        request.getSession().removeAttribute("memNick");
        request.getSession().setAttribute("member", member);
        request.getSession().setAttribute("memProfile", member.getProfile());
        request.getSession().setAttribute("memNick", member.getNickName());
        request.getSession().setMaxInactiveInterval(60 * 30);
        return "redirect:/main/myPage.do";
    }

    @GetMapping(value="/main/product.do")
    public String product(Model model){
        BoardDTO board = new BoardDTO();
        model.addAttribute("board",board);
        return "/productUpload";
    }

    @GetMapping(value="main/buylist")
    public String buyList(Model model){
        BuyBoardDTO board = new BuyBoardDTO();
        model.addAttribute("board",board);
        return "/buyList";
    }

    @GetMapping(value="/main/buyUpload.do")
    public String buyUpload(Model model){
        BuyBoardDTO buyBoard = new BuyBoardDTO();
        model.addAttribute("buyBoard",buyBoard);
        return "/buyUpload";
    }

    /**
     * 검색할 경우 동작
     * @param
     * @return html 파일
     */
    @GetMapping(value = "/search")
    public String searchProduct(@ModelAttribute("params")BoardDTO params, Model model){
        System.out.println("검색 값 : " +params.getSearchKeyword());
        List<BoardDTO> boardList = boardService.getSearchBoardList(params);
        List<AttachDTO> attachList = boardService.getAttachList(boardList);
        model.addAttribute("boardList", boardList);
        model.addAttribute("attachList", attachList);
        model.addAttribute("searchType", 2);
        model.addAttribute("searchCount", boardService.getSearchCount(params));
        return "/search";
    }

    /**
     * 카테고리 클릭 시 동작
     * @param
     * @return html 파일
     */
    @GetMapping(value = "/categories")
    public String categoriesProduct(@ModelAttribute("params")BoardDTO params, Model model){
        System.out.println("카테고리 선택 값 : " + params.getCategoriesProduct());
        List<BoardDTO> boardList = boardService.getCategoryBoardList(params);
        List<AttachDTO> attachList = boardService.getAttachList(boardList);
        model.addAttribute("boardList", boardList);
        model.addAttribute("attachList", attachList);
        model.addAttribute("searchType", 1);
        return "/search";
    }

//    @PostMapping(value = "/mainPaging")
////    @ResponseBody
////    public Object mainBoardListControl(@RequestBody Map<String,Integer> param){
////        Map<String, Object> map = new HashMap<>();
////        List<BoardDTO> listMore = boardService.getBoardList(param.get("Idx"));
////        List<AttachDTO> attachMore = boardService.getAttachList(listMore);
////        map.put("listMore", listMore);
////        map.put("attachMore", attachMore);
////        return map;
////    }

    @PostMapping(value = "/mainPaging")
    @ResponseBody
    public Object mainBoardListControl(@RequestBody Map<String,Integer> param){
        JsonObject jsonObj = new JsonObject();
        System.out.println("뭐가 드가지 "+ param.get("Idx"));
        List<BoardDTO> listMore =  boardService.getBoardList(param.get("Idx"));
        List<AttachDTO> attachMore = boardService.getAttachList(listMore);

        if (CollectionUtils.isEmpty(listMore) == false) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
            JsonArray jsonArr = gson.toJsonTree(listMore).getAsJsonArray();
            jsonObj.add("listMore", jsonArr);
        }
        if (CollectionUtils.isEmpty(attachMore) == false) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
            JsonArray jsonArr = gson.toJsonTree(attachMore).getAsJsonArray();
            jsonObj.add("attachMore", jsonArr);
        }

        return jsonObj;
    }

    /**
     * 파일 업로드 인데 다른거로 할 예정
     * */
//    @PostMapping(value="/profile/upload.do")
//    public String uploadProfile(MultipartFile upload, HttpServletRequest request ){
//
//        String saveDir = request.getSession().getServletContext().getRealPath("/resources/static/images/profile");
//
//        File dir = new File(saveDir);
//        if(!dir.exists()){
//            dir.mkdirs();
//        }
//        if(!upload.isEmpty()){
//            String orifileName = upload.getOriginalFilename();
//            String ext = orifileName.substring(orifileName.lastIndexOf("."));
//
//            // 이름 값 변경을 위한 설정
//             SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmssSSS");
//             int rand = (int)(Math.random()*1000);
//             // 파일 이름 변경
//             String reName = sdf.format(System.currentTimeMillis()) + "_" + rand + ext;
//             // 파일 저장
//             try {
//                 upload.transferTo(new File(saveDir + "/" + reName));
//             }
//             catch (IllegalStateException | IOException e) {
//                 e.printStackTrace();
//             }
//        }
//        return "redirect:/main/myPage";
//    }


    public MemberDTO commonSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("memId");
        System.out.println("로그인 아이디 : "+id);
        return serviceTest.getAllInfo(id);
    }
}
