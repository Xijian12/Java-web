package com.shu.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMaterialRequest {
    private String adminAccount;
    private String userEmail;
    private Integer type;
    private List<Integer> materialIds;
}
