package com.yulin.library.util.model.entity;

import lombok.Data;

@Data
public class CurrentUser implements java.io.Serializable {
    private static final long serialVersionUID = -8360980368403423929L;

    private Long id;
    private String username;
    private String headPortraits;
    private String phone;
    private Integer sex;

}
