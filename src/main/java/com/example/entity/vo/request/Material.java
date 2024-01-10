package com.example.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    private Integer materialId;
    private String school;
    private String major;
    private String subject;
    private double materialGrade;
    private String materialUploader;
    private String materialProfile;
    private String materialCoverUrl;
    private String materialCoverUuid;
    private Integer materialClickNum;
    private Integer materialDownloadNum;
    private String elecBookUuid;
    private double elecBookPoints;
    private String teachingPlanUuid;
    private double teachingPlanPoints;
    private String classPptUuid;
    private double classPptPoints;
    private String calendarVolumeUuid;
    private double calendarVolumePoints;
    private String anotherMaterialUuid;
    private double anotherMaterialPoints;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
