package com.shu.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@Data
@NoArgsConstructor

public class Page<T> {
    private List<T> content;
    private int totalElements;

    private int page;
    private int size;

    public Page(List<T> content, int totalElements, int page, int size) {
        this.content = content;
        this.totalElements = totalElements;
        this.page = page;
        this.size = size;
    }

    // Getters and setters

    public void setContent(List<T> content) {
        this.content = content;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }
}