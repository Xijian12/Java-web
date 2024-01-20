package com.shu.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetail {
    private int bookId;
    private String categoryName;
    private String categoryAlias;
    private String bookName;
    private String bookAuthor;
    private String bookVersion;
    private String bookImg;
    private String createTime;
    private String updateTime;

    // 构造函数、Getter和Setter
}