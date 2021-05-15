package com.daelim.transactions.service.likeAndView;

import com.daelim.transactions.dto.BuyLikeDTO;
import com.daelim.transactions.dto.SaleLikeDTO;

public interface BuyLikeAndViewService {
    //--------여기부터
    public void addBuyBoardViews(long idx);
    public int addBuyLikes(BuyLikeDTO param);
    public int removeBuyLikes(BuyLikeDTO param);
    public boolean getBuyLikes(BuyLikeDTO param);
    public int buyLikeTotalCount(int idx);
    //--------여기까지 구해요 게시글 조회수 및 찜

    //--------여기부터
    public void addSaleBoardViews(long idx);
    public int addSaleLikes(SaleLikeDTO param);
    public int removeSaleLikes(SaleLikeDTO param);
    public boolean getSaleLikes(SaleLikeDTO param);
    public int SaleLikeTotalCount(int idx);
    //--------여기까지 팔아요 게시글 조회수 및찜
}
