package com.hrm.common.shiro.session;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 维护公共session获取
 * @author 三多
 * @Time 2020/4/15
 */
public class CustomerSessionManager extends DefaultWebSessionManager {
    private static final String AUTHOR_HEADER = "Authorization";
    public static final String HEADER_SESSION_ID_SOURCE = "header";

    /**
     * 头信息中具有sessionID
     *      请求头：Authorization：sessionId
     *      指定sessionId的获取方式
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取请求头中Authorization数据。sessionId
        String id = WebUtils.toHttp(request).getHeader(AUTHOR_HEADER);
        if (StringUtils.isEmpty(id)) {
            //如果没有生成新的sessionId
            return super.getSessionId(request, response);
        } else {
            //请求头信息 ：Bearer sessionId
            id = id.replace("Bearer ","");
            //返回SessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    HEADER_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return id;
        }
    }
}
