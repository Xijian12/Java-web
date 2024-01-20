package com.shu.entity.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer elecBookPoints;
    private String teachingPlanUuid;
    private Integer teachingPlanPoints;
    private String classPptUuid;
    private Integer classPptPoints;
    private String calendarVolumeUuid;
    private Integer calendarVolumePoints;
    private String anotherMaterialUuid;
    private Integer anotherMaterialPoints;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
