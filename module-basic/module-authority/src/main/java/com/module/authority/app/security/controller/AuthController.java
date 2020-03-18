package com.module.authority.app.security.controller;

import cn.hutool.core.util.ObjectUtil;
import com.module.authority.app.security.code.sms.AliYunSmsUtils;
import com.module.authority.app.security.code.sms.SmsResponse;
import com.module.common.constant.PreConstant;
import com.module.common.utils.R;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 认证
 * @author 三多
 * @Time 2020/3/7
 */
@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 生成验证码
     * @return
     * @throws IOException
     */
    @RequestMapping("/captcha.jpg")
    public R captcha() throws IOException {
        // 算术类型
        ArithmeticCaptcha captcha =  new ArithmeticCaptcha(111,36);
        //几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result = captcha.text();
        String key = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(PreConstant.PRE_IMAGE_KEY+key,result,3, TimeUnit.MINUTES);
        Map<String,Object> map = new HashMap<>();
        map.put("key",key);
        map.put("img",captcha.toBase64());
        return R.ok(map);
    }

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    @PostMapping("/sendCode/{phone}")
    public R sendSmsCode(@PathVariable("phone") String phone) {
        SmsResponse smsResponse = AliYunSmsUtils.sendSms(phone, "prex", "登录");
        if (ObjectUtil.isNull(smsResponse)) {
            return R.error("短信发送失败");
        }
        // 保存到验证码到 redis 有效期两分钟
        redisTemplate.opsForValue().set(phone, smsResponse.getSmsCode(), 2, TimeUnit.MINUTES);
        return R.ok();
    }

}
