package com.shu.entity.vo.response;

import com.shu.entity.vo.request.Material;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialUploadVO {
    private int size;
    private List<Material> materials;
}
