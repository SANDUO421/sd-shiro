package com.module.authority.app.social;

import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 三多
 * @Time 2020/3/2
 */
@Component
public class SocialRedisHelper {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    /**
     * 第三方社交账号信息进行与业务系统账号绑定
     *
     * @param mKey
     * @param userId
     */
    public void doPostSignUp(String mKey, Integer userId) {
        String key = getKey(mKey);
        if (ObjectUtil.isNotNull(redisTemplate) && !redisTemplate.hasKey(key)) {
            throw new RuntimeException("无法找到缓存的第三方社交账号信息");
        }
        PreConnectionData preConnectionData = (PreConnectionData) redisTemplate.opsForValue().get(key);
        ConnectionData connectionData = null;
        if (ObjectUtil.isNotNull(preConnectionData)) {
            connectionData = new ConnectionData(preConnectionData.getProviderId(), preConnectionData.getProviderUserId(), preConnectionData.getDisplayName(), preConnectionData.getProfileUrl(), preConnectionData.getImageUrl(), preConnectionData.getAccessToken(), preConnectionData.getSecret(), preConnectionData.getRefreshToken(), preConnectionData.getExpireTime());
        }
        Connection<?> connection =
                connectionFactoryLocator.getConnectionFactory(preConnectionData.getProviderId()).createConnection(connectionData);
        usersConnectionRepository.createConnectionRepository(String.valueOf(userId)).addConnection(connection);
        redisTemplate.delete(key);
    }

    /**
     * 获取key
     * @param key
     * @return
     */
    private String getKey(String key) {
        if(StringUtils.isEmpty(key)){
            throw new RuntimeException("key 不能为空");
        }
        return "pre:security:social.connect." + key;
    }

    /**
     * 缓存第三方用户信息
     * @param mKey  key
     * @param preConnectionData 数据
     */
    public void saveConnectionData(String mKey, PreConnectionData preConnectionData) {
        redisTemplate.opsForValue().set(getKey(mKey),preConnectionData,10, TimeUnit.MINUTES);
    }
}
