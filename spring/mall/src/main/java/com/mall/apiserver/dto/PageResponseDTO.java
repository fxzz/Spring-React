package com.mall.apiserver.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<E> {

    private List<E> dtoList;

    private List<Integer> pageNumList;

    private PageRequestDTO pageRequestDTO;

    private boolean prev, next;

    private int totalCount, prevPage, nextPage, totalPage, current;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount) {

        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int)totalCount;

        // 끝페이지
        int end = (int)(Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;

        // 첫페이지
        int start = end - 9;

        // 최고 마지막 페이지
        int last = (int)(Math.ceil((totalCount/(double)pageRequestDTO.getSize())));

        end =  end > last ? last : end;

        this.prev = start > 1;

        this.next =  totalCount > end * pageRequestDTO.getSize();

        this.pageNumList = IntStream.rangeClosed(start,end).boxed().collect(Collectors.toList());

        if(prev) {
            this.prevPage = start -1;
        }

        if(next) {
            this.nextPage = end + 1;
        }

        this.totalPage = this.pageNumList.size();

        this.current = pageRequestDTO.getPage();

    }
}
