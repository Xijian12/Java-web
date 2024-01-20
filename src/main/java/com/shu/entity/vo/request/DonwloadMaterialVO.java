package com.shu.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonwloadMaterialVO {
    private Integer materialId;
    private String userEmail;
    private Integer type;
    private String downloadUrl;
}
