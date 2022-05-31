package com.graduate_ok.graduate_ok.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchHelper {

    private String srchType; // 검색 타입 (제목, 내용)
    private String srchKeyword; // 검색어

    /**
     * 페이징
     */
    private int pageSize = 10; // 한 페이지에 출력될 row 개수
    private int blockSize = 10; // 한 페이지에 페이징 번호가 몇 개까지 출력되게 할지

    private int page = 1; // 현재 페이지 번호
    private int block = 1; // 현재 블럭 번호

    private int totalRowCnt; // 전체 row(전체 게시물) 개수
    private int totalPageCnt; // 전체 페이지 수
    private int totalBlockCnt; // 총 블럭 수

    private int startPage = 1; // 블럭 시작 페이지 (블럭 시작할 때 1블럭부터 할 건지 2블럭부터 할 건지
    private int endPage = 1; // 블럭 마지막 페이지

    private int startIndex = 0; // sql 시작 index (쿼리문 limit 조건)

    private int prevBlock; // 이전 블럭의 마지막 페이지
    private int nextBlock; // 다음 블럭의 시작 페이지

    /**
     * 파라미터로 row 개수를 카운트해서 현재 페이지 번호를 넣기
     */
    public SearchHelper(int totalRowCnt, int page) {
        setPage(page); // 현재 페이지 번호
        setTotalRowCnt(totalRowCnt); // 전체 row 개수

        //전체 페이지 수 = totalRowCnt / 한 페이지에 출력될 row 개수
        setTotalPageCnt((int)Math.ceil(totalRowCnt * 1.0 / pageSize)); // Math.ceil 반올림

        // 총 블럭 수 = 총 페이지 수 / 페이지 숫자 목록을 한번에 몇 개까지 출력할지
        setTotalBlockCnt((int) Math.ceil(totalPageCnt * 1.0 / blockSize));

        // 현재 블럭 = 현재 페이지 번호 / 페이징 숫자 목록을 한번에 몇 개까지 출력할지
        setBlock((int) Math.ceil(page * 1.0 / blockSize));

        // 블럭 시작 페이지
        setStartPage((block - 1) * blockSize + 1);

        // 블럭 마이작 페이지
        setEndPage(startPage + blockSize - 1);

        // 마지막 페이지 번호가 전체 페이지 번호 카운트보다 크면 마지막 페이지에 전체 페이지 번호값을 삽입
        if (endPage > totalPageCnt) this.endPage = totalPageCnt;

        // 이전 블럭 클릭 시 이전 블럭의 마지막 페이지 나오게
        setPrevBlock((block * blockSize) - blockSize);

        // 이전 블럭 값이 1보다 작으면 prevBlock에 1 삽입
        if (prevBlock < 1) this.prevBlock = 1;

        // 다음 블럭 클릭 시 다음 블럭의 첫 페이지 나오게
        setNextBlock((block * blockSize) + 1);

        // 다음 블럭을 클릭 시 nextBlock 번호가 전체 페이지 수보다 크면 nextBlock에 전체 페이지 카운트 수 삽입
        if (nextBlock > totalPageCnt) this.nextBlock = totalPageCnt;

        // 첫 페이지의 index 값 알아내기
        setStartIndex((page - 1) * pageSize);
    }

}
