package com.daelim.transactions.controller;

import com.daelim.transactions.dto.*;
import com.daelim.transactions.service.BoardService;
import com.daelim.transactions.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.util.*;


@Controller
public class MainController {

    @Autowired
    ServiceTest serviceTest;

    @Autowired
    BoardService boardService;

    @GetMapping(value = "/main")
    public String goMain(Model model, HttpServletRequest request) throws UnsupportedEncodingException {

        System.out.println("여기는 메인인데요");
        List<BoardDTO> boardList = boardService.getBoardList();
        List<AttachDTO> attachList = boardService.getAttachList();
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

        model.addAttribute("boardList", boardList);
        model.addAttribute("attachList", attachList);
        model.addAttribute("memNick",member.getNickName());
        model.addAttribute("memProfile",member.getProfile());
        return "/myPage/myPage";
    }

    @GetMapping(value="/main/myPage/profile.do")
    public String toProfile(HttpServletRequest request, Model model){
        MemberDTO member = commonSession(request);
        MemberDTO newMemberPw = new MemberDTO();
        model.addAttribute("memNick",member.getNickName());
        model.addAttribute("memProfile",member.getProfile());
        model.addAttribute("memName",member.getName());
        model.addAttribute("member",newMemberPw);
        System.out.println(member.getNickName());
        return "/myPage/profile";
    }


    @PostMapping(value="/profile/upload.do")
    public String uploadProfile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request){
        MemberDTO member = commonSession(request);
        System.out.println("member ="+member.getLoginId());
        boolean isChange = serviceTest.changProfile(member,file);

        return "redirect:/main/myPage.do";
    }

    @GetMapping(value="/main/product.do")
    public String product(Model model){
        BoardDTO board = new BoardDTO();
        model.addAttribute("board",board);
        return "/productUpload";
    }

    @GetMapping(value="/main/buylist.do")
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

    @PostMapping(value = "/mainPaging")
    @ResponseBody
    public Object mainBoardListControl(@RequestBody Map<String,Integer> param){
        Map<String, Object> map = new HashMap<>();
        List<BoardDTO> listMore = boardService.getBoardList(param.get("Idx"));
        List<AttachDTO> attachMore = boardService.getAttachList(listMore);
        map.put("listMore", listMore);
        map.put("attachMore", attachMore);
        return map;
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
