package com.daelim.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class Criteria {
    /** 현재 페이지 번호 */
    private int currentPageNo;

    /** 페이지당 출력할 데이터 개수 */
    private int recordsPerPage;

    /** 화면 하단에 출력할 페이지 사이즈 */
    private int pageSize;

    /** 검색 키워드 */
    private String searchKeyword;

    /** 카테고리로 검색 */
    private String categoriesProduct;

    public Criteria() {
        this.currentPageNo = 1;
        this.recordsPerPage = 12;
        this.pageSize = 10;
    }

    /**
     * 
     * @param pageNo 사용자가 가고자 하는 페이지
     * @param searchType 1이면 카테고리로 검색, 2이면 검색창을 통한 검색
     * @return
     */
    public String makeQueryString(int pageNo, int searchType) {
        UriComponents uriComponents;
        if(searchType == 1){
             uriComponents = UriComponentsBuilder.newInstance()
                    .queryParam("currentPageNo", pageNo)
                     .queryParam("categoriesProduct", categoriesProduct)
                    //.queryParam("recordsPerPage", recordsPerPage)
                    //.queryParam("pageSize", pageSize)
                    //.queryParam("searchType", searchType)
                    .build()
                    .encode();
        }else{
            uriComponents = UriComponentsBuilder.newInstance()
                    .queryParam("currentPageNo", pageNo)
                    .queryParam("searchKeyword", searchKeyword)
                    //.queryParam("recordsPerPage", recordsPerPage)
                    //.queryParam("pageSize", pageSize)
                    //.queryParam("searchType", searchType)
                    .build()
                    .encode();
        }

        return uriComponents.toUriString();
    }

}
