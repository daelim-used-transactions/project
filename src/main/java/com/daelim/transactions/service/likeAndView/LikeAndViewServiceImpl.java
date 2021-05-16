package com.daelim.transactions.service.likeAndView;

import com.daelim.transactions.dto.BuyLikeDTO;
import com.daelim.transactions.dto.SaleLikeDTO;
import com.daelim.transactions.mapper.DaoLikeAndView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LikeAndViewServiceImpl implements LikeAndViewService {

    private final DaoLikeAndView daoLikeAndView;

    @Override
    public void addBuyBoardViews(long idx) {
        daoLikeAndView.updateBoardViews(idx);
    }

    @Override
    public int addBuyLikes(BuyLikeDTO param) {
        daoLikeAndView.insertBuyLike(param);
        Map<String, Integer> map = new HashMap<>();
        int count = daoLikeAndView.selectBuyLikeCount(param.getBoardIdx());
        map.put("idx", param.getBoardIdx());
        map.put("count", count);
        daoLikeAndView.updateBoardBuyLike(map);
        return count;
    }

    @Override
    public int removeBuyLikes(BuyLikeDTO param) {
        daoLikeAndView.deleteBuyLike(param);
        Map<String, Integer> map = new HashMap<>();
        int count = daoLikeAndView.selectBuyLikeCount(param.getBoardIdx());
        map.put("idx", param.getBoardIdx());
        map.put("count", count);
        daoLikeAndView.updateBoardBuyLike(map);
        return count;
    }

    @Override
    public boolean getBuyLikes(BuyLikeDTO param) {
        boolean check = false;
        BuyLikeDTO buyLike = daoLikeAndView.selectBuyLike(param);
        if(buyLike == null){
            return check;
        }
        else if(buyLike.getBoardIdx() == param.getBoardIdx() && buyLike.getLoginId().equals(param.getLoginId())) {
            check = true;
        }
        return check;
    }

    @Override
    public int buyLikeTotalCount(int idx) {
        return daoLikeAndView.selectBuyLikeCount(idx);
    }

    //-------------여기까지 구해요 게시글 조회수 및 찜------------------

    @Override
    public void addSaleBoardViews(long idx) {
        daoLikeAndView.updateSaleBoardViews(idx);
    }

    @Override
    public int addSaleLikes(SaleLikeDTO param) {
        daoLikeAndView.insertSaleLike(param);
        Map<String, Integer> map = new HashMap<>();
        int count = daoLikeAndView.selectSaleLikeCount(param.getBoardIdx());
        map.put("idx", param.getBoardIdx());
        map.put("count", count);
        daoLikeAndView.updateBoardSaleLike(map);
        return count;
    }

    @Override
    public int removeSaleLikes(SaleLikeDTO param) {
        daoLikeAndView.deleteSaleLike(param);
        Map<String, Integer> map = new HashMap<>();
        int count = daoLikeAndView.selectSaleLikeCount(param.getBoardIdx());
        map.put("idx", param.getBoardIdx());
        map.put("count", count);
        daoLikeAndView.updateBoardSaleLike(map);
        return count;
    }

    @Override
    public boolean getSaleLikes(SaleLikeDTO param) {
        boolean check = false;
        SaleLikeDTO saleLike = daoLikeAndView.selectSaleLike(param);
        if(saleLike == null){
            return check;
        }
        else if(saleLike.getBoardIdx() == param.getBoardIdx() && saleLike.getLoginId().equals(param.getLoginId())) {
            check = true;
        }
        return check;
    }

    @Override
    public int SaleLikeTotalCount(int idx) {
        return daoLikeAndView.selectSaleLikeCount(idx);
    }

    @Override
    public List<SaleLikeDTO> getSaleLikes(String loginId) {
        List<SaleLikeDTO> saleLikeList = Collections.emptyList();
        saleLikeList = daoLikeAndView.selectSaleLikeListByLoginId(loginId);
        return saleLikeList;
    }

    @Override
    public int SaleLikeTotalCount(String loginId) {
        return daoLikeAndView.selectSaleLikeCountById(loginId);
    }

}
