package com.shu.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCategory {
    private int categoryId;
    private String categoryName;
    private String categoryAlias;//别名
    private int categoryClickNum;
    private int categoryDownloadNum;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryAlias() {
        return categoryAlias;
    }

    public void setCategoryAlias(String categoryAlias) {
        this.categoryAlias = categoryAlias;
    }

    public int getCategoryClickNum() {
        return categoryClickNum;
    }

    public void setCategoryClickNum(int categoryClickNum) {
        this.categoryClickNum = categoryClickNum;
    }

    public int getCategoryDownloadNum() {
        return categoryDownloadNum;
    }

    public void setCategoryDownloadNum(int categoryDownloadNum) {
        this.categoryDownloadNum = categoryDownloadNum;
    }}


