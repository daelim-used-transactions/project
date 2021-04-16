package com.daelim.transactions.controller;

import com.daelim.transactions.dto.BoardDTO;
import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.ServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;


@Controller
public class MainController {

    @Autowired
    ServiceTest serviceTest;

    @GetMapping(value = "/main")
    public String goMain() {
        return "main";
    }


    @GetMapping(value = "/main/myPage.do")
    public String toMyPage(HttpServletRequest request, Model model) {
        MemberDTO member = commonSession(request);
        model.addAttribute("memNick",member.getNickName());
        model.addAttribute("memProfile",member.getProfile());
        return "/myPage/myPage";
    }

    @GetMapping(value="/main/myPage/profile.do")
    public String toProfile(HttpServletRequest request, Model model){
        MemberDTO member = commonSession(request);
        model.addAttribute("memNick",member.getNickName());
        model.addAttribute("memProfile",member.getProfile());
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
