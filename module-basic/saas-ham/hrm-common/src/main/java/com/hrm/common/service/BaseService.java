package com.hrm.common.service;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 基本service
 * @author 三多
 * @Time 2020/3/18
 */
public class BaseService<T> {

    public Specification<T> getSpec(String companyId){
        Specification<T>  spec = new Specification<T>(){
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("companyId").as(String.class), companyId);
            }
        };
        return spec;
    }
}
