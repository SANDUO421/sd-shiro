package com.yulin.library.model.entity;

import com.yulin.library.mongodb.model.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class DataDictionary extends BaseEntity {
    private static final long serialVersionUID = -3414079338439971407L;

    private String value;
    private String type;
    private Integer sortNumber;

}
