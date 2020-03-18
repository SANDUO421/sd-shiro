package com.module.authority.app.data.strategy;

import com.module.authority.app.data.enmu.DataScopeTypeEnum;
import com.module.authority.app.domain.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略上下文
 * 创建环境角色Context
 *
 * @author 三多
 * @Time 2020/3/5
 */
@Service
public class DataScopeContext {

    @Autowired
    private final Map<String,AbstractDataScopeHandler> strategyMap = new ConcurrentHashMap<>();

    /**
     * Component里边的1是指定其名字，这个会作为key放到strategyMap里
     * @param strategyMap
     */
    public DataScopeContext(Map<String,AbstractDataScopeHandler> strategyMap) {
        strategyMap.forEach(this.strategyMap::put);
    }

    /**
     *
     * @param roleDto
     * @param type
     * @return
     */
    public List<Integer>  getDeptIdsForDataScope(RoleDTO roleDto,Integer type){
        return strategyMap.get(String.valueOf(type)).getDeptIds(roleDto, DataScopeTypeEnum.valueOf(type));
    }
}
