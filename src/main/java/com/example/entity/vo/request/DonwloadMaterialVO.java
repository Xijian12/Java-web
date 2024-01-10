package com.example.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonwloadMaterialVO {
    Integer materialId;
    String userEmail;
    Integer type;
}
