package com.daelim.transactions.mapper;

import com.daelim.transactions.dto.BuyLikeDTO;
import com.daelim.transactions.dto.SaleLikeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DaoLikeAndView {
    //여기부터
    public void updateBoardViews(long boardIdx);
    public void updateBoardBuyLike(Map<String,Integer> map);
    public void insertBuyLike(BuyLikeDTO param);
    public void deleteBuyLike(BuyLikeDTO param);
    public BuyLikeDTO selectBuyLike(BuyLikeDTO param);
    public int selectBuyLikeCount(int idx);
    //여기까지 구해요 게시글 조회수 및 찜

    public void updateSaleBoardViews(long boardIdx);
    public void updateBoardSaleLike(Map<String,Integer> map);
    public void insertSaleLike(SaleLikeDTO param);
    public void deleteSaleLike(SaleLikeDTO param);
    public SaleLikeDTO selectSaleLike(SaleLikeDTO param);
    public int selectSaleLikeCount(int idx);
    public List<SaleLikeDTO> selectSaleLikeListByLoginId(String loginId);
    public int selectSaleLikeCountById(String loginId);
}
