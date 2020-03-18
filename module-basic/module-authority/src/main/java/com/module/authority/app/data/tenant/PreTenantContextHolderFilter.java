package com.module.authority.app.data.tenant;

import com.module.common.constant.PreConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 多租户上下文过滤器 -设置加载顺序最高获取租户
 * @author 三多
 * @Time 2020/3/5
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class PreTenantContextHolderFilter extends OncePerRequestFilter {
    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 后面考虑存到redis
        String tenantId = request.getHeader(PreConstant.PRE_TENANT_KEY);
        //在没有提供tenantId的情况下返回默认的
        if(StringUtils.isNoneBlank(tenantId)){
            PreTenantContextHolder.setCurrentTenantId(Long.valueOf(tenantId));
        }else{
            PreTenantContextHolder.setCurrentTenantId(1L);
        }
        filterChain.doFilter(request,response);
    }
}
