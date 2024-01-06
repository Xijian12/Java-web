package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCategory {
    private int id;
    private String name;
    private String alias;//别名
    private int clicks;
    private int downloads;

}
