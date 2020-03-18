package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;

@Data
public class AppIcon extends BaseEntity {
    private static final long serialVersionUID = -8044344215877667195L;
    private String position;
    private String positionName;
    private String imageUrl;
    private Integer sortNum;
}
