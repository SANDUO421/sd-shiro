package com.module.authority.app.data.enmu;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据权限枚举
 * @author 三多
 * @Time 2020/3/5
 */
@Getter
@AllArgsConstructor
public enum DataScopeTypeEnum {
    /**全部*/
    ALL(1,"全部"),
    /**本级*/
    THIS_LEVEL(2,"本级"),
    /**本级以及子集*/
    THIS_LEVEL_CHILDREN(3,"本级以及子集"),
    /**自定义*/
    CUSTOMIZE(4,"自定义");

    /**类型*/
    private int type;
    /**描述*/
    private String desc;

    /**
     * 根据类型获取数据权限对象
     * @param type 类型
     * @return DataScopeTypeEnum
     */
    public static DataScopeTypeEnum valueOf(int type){
        for(DataScopeTypeEnum typeVal : DataScopeTypeEnum.values()){
            if(typeVal.getType() == type){
                return  typeVal;
            }
        }
        return ALL;
    }
}
