package com.example.pojo;

import java.util.List;

public class BookResponse {
    private int total;
    private List<BookDetail> items;

    // 构造函数、Getter和Setter

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BookDetail> getItems() {
        return items;
    }

    public void setItems(List<BookDetail> items) {
        this.items = items;
    }
}