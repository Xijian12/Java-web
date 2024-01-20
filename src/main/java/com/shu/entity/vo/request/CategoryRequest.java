package com.shu.entity.vo.request;

public class CategoryRequest {
    private String categoryName;
    private String categoryAlias;

    // Getters and Setters
    // Getter和Setter方法
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
}