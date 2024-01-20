package com.shu.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadBookFileVO {
    private String bookId;
    private String bookFileUuid;
}
