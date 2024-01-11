package com.example.entity.vo.response;

import com.example.entity.vo.request.Material;
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
