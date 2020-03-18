package com.module.authority.app.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @description:    构建部门树vo
 * @author:         sanduo
 * @date:           2020/3/4 12:27
 * @version:        1.0
 */
@Setter
@Getter
@ToString
public class DeptTreeVo {

    private int id;
    private String label;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptTreeVo> children;

}
