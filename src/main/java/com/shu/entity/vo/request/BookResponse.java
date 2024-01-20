package com.shu.entity.vo.request;

import java.util.List;

public class BookResponse {
    private int total;
    private List<Book> items;

    // 构造函数、Getter和Setter

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Book> getItems() {
        return items;
    }

    public void setItems(List<Book> items) {
        this.items = items;
    }
}