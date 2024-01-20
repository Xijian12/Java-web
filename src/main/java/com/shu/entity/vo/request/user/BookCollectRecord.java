package com.shu.entity.vo.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCollectRecord {
    private Integer collectId;
    private Integer userId;
    private String username;
    private String userEmail;
    private String bookName;
    private String bookAuthor;
    private String bookCoverUrl;
    private Integer bookId;
    private Integer bookDownloadPoints;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
