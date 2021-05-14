package com.daelim.transactions.controller;

import com.daelim.transactions.adapter.GsonLocalDateTimeAdapter;
import com.daelim.transactions.dto.BuyCommentDTO;
import com.daelim.transactions.dto.MainCommentDTO;
import com.daelim.transactions.dto.MemberDTO;
import com.daelim.transactions.service.MainCommentService;
import com.daelim.transactions.service.ServiceTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MainCommentController {

    @Autowired
    private  ServiceTest serviceTest;
    @Autowired
    private  MainCommentService mainCommentService;

    @GetMapping(value = "/maincomments/{boardIdx}")
    public JsonObject getCommentList(@PathVariable("boardIdx") Long boardIdx, @ModelAttribute("params") MainCommentDTO params) {

        JsonObject jsonObj = new JsonObject();

        List<MainCommentDTO> commentList = mainCommentService.getCommentList(params);
        for (MainCommentDTO m : commentList) {
            System.out.println("뭘까요: "  +m.getContent());
        }
        if (CollectionUtils.isEmpty(commentList) == false) {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
            JsonArray jsonArr = gson.toJsonTree(commentList).getAsJsonArray();
            jsonObj.add("commentList", jsonArr);
        }

        return jsonObj;
    }

    @RequestMapping(value = { "/maincomments", "/maincomments/{idx}" }, method = { RequestMethod.POST, RequestMethod.PATCH })
    public JsonObject registerComment(@PathVariable(value = "idx", required = false) Long idx, @RequestBody final MainCommentDTO params, HttpServletRequest request) {

        JsonObject jsonObj = new JsonObject();
        String memId = (String) request.getSession().getAttribute("memId");
        MemberDTO member = new MemberDTO();
        try {
            if (idx != null ) {
                params.setIdx(idx);
            }else{
                if(memId != null){
                    member = serviceTest.getAllInfo(memId);
                    params.setWriter(member.getNickName());
                }
            }


            boolean isRegistered = mainCommentService.registerComment(params);
            jsonObj.addProperty("result", isRegistered);

        } catch (DataAccessException e) {
            jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

        } catch (Exception e) {
            jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
        }

        return jsonObj;
    }

    @DeleteMapping(value="/maincomments/{idx}")
    public JsonObject deleteComment(@PathVariable("idx") final Long idx ){
        JsonObject jsonObj = new JsonObject();
        try{
            boolean isDeleted = mainCommentService.deleteComment(idx);
            jsonObj.addProperty("result",isDeleted);
        }catch (DataAccessException e){
            jsonObj.addProperty("message","데이터베이스 처리 과정에 문제가 발생하였습니다.");
        }catch (Exception e){
            jsonObj.addProperty("message","시스템에 문제가 발생하였습니다.");
        }
        return jsonObj;
    }
}
