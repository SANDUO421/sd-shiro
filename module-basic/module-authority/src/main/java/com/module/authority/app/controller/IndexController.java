package com.module.authority.app.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.module.authority.app.domain.UserDTO;
import com.module.authority.app.entity.SysUser;
import com.module.authority.app.security.domian.SocialUserInfo;
import com.module.authority.app.service.ISysUserService;
import com.module.authority.app.social.PreConnectionData;
import com.module.authority.app.social.SocialRedisHelper;
import com.module.authority.app.utils.Create;
import com.module.common.exception.ValidateCodeException;
import com.module.common.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 三多
 * @Time 2020/3/1
 */
@Api(value = "用户注册、登录、绑定")
@RestController
public class IndexController {


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @Autowired
    private SocialRedisHelper socialRedisHelper;

    @Value("${pre.url.address}")
    private String url;

    /**
     * 注册
     *
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestBody @Validated(Create.class) UserDTO userDTO) {
        Object verifyCode = redisTemplate.opsForValue().get(userDTO.getPhone());
        if (ObjectUtil.isNull(verifyCode)) {
            throw new ValidateCodeException("验证码已失效");
        }
        if (!userDTO.getSmsCode().toUpperCase().equals(verifyCode)) {
            throw new ValidateCodeException("短信验证码错误");
        }
        return R.ok(userService.register(userDTO));
    }

    /**
     * 绑定社交账号
     *
     * @param sysUser 系统用户
     * @return R
     */
    @PostMapping("/bind")
    public R register(@RequestBody @Validated(Create.class) SysUser sysUser) {
        return R.ok(userService.doPostSignUp(sysUser));
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @param request 请求
     * @return R
     */
    public R login(String username, String password, HttpServletRequest request){
        //社交快速登录
        String token =request.getParameter("token");
        if(StrUtil.isNotEmpty(token)){
            return R.ok(token);
        }
        return R.ok(userService.login(username,password));

    }

    /**
     * 获取信息图像
     * @return R
     */
    @RequestMapping("/info")
    public R info() {
        Map<String, Object> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("admin");
        map.put("roles", list);
        map.put("avatar", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561394014552&di=17b6c1233048e5276f48309b306c7699&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201804%2F29%2F20180429210111_gtsnf.jpg");
        map.put("name", "Super Admin");
        return R.ok(map);
    }

    /**
     * 使用jwt前后分离 只需要前端清除token即可 暂时返回success
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        return "success";
    }

    /**
     * 保存完信息然后跳转到绑定用户信息页面
     * @param request 请求对象
     * @param response 响应对象
     */
    @GetMapping("/socialSignUp")
    public void socialSignUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid  = IdWorker.get32UUID();
        Connection<?> connectionFromSession = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        SocialUserInfo userInfo = SocialUserInfo.builder()
                .nickname(connectionFromSession.getDisplayName())
                .headImg(connectionFromSession.getImageUrl())
                .providerId(connectionFromSession.getKey().getProviderId())
                .providerUserId(connectionFromSession.getKey().getProviderUserId())
                .build();
        ConnectionData connectionData = connectionFromSession.createData();
        PreConnectionData preConnectionData = new PreConnectionData();
        BeanUtil.copyProperties(connectionData,preConnectionData);
        socialRedisHelper.saveConnectionData(uuid,preConnectionData);
        // 跳转到用户绑定页面
        response.sendRedirect(url + "/bind?key="+ uuid);


    }
}
