package com.shu.entity.vo.request.user;

import com.shu.entity.vo.request.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTop {
    private List<Book> books;
    private int total;//实际获取的图书总数

}
