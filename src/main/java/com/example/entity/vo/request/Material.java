package com.example.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    private Integer MaterialId;
    private String shcool;
    private String major;
    private String subject;
    private Integer materialGrade;
    private Integer materialClickNum;
    private Integer materialDownloadNum;
    private String materialUploader;
    private String materialProfile;
    private String materialCoverUrl;
    private String materialCoverUuid;
    private String elecBookUuid;
    private Integer elecBookPoints;
    private String teachingPlanUuid;
    private Integer teachingPlanPoints;
    private String classPptUuid;
    private Integer classPptPoints;
    private String calendarVolumeUuid;
    private Integer calendarVolumePoints;
    private String anotherMaterial;
    private Integer anotherMaterialPoints;
    private LocalDate createTime;
    private LocalDate updateTime;
}
