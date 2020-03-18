package com.yulin.library.util;

import cn.hutool.core.map.MapUtil;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.yulin.library.util.push.entity.PushData;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PushTest {

    protected static final Logger LOG = LoggerFactory.getLogger(PushTest.class);

    private String APP_KEY="8ff91ec287d477dae94f4a95";
    private String MASTER_SECRET="4e2670a347dcae9424e4b23d";

    private static Map<String,Platform> platformMap=new HashMap<>();
    static {
        platformMap.put(Constants.PushPlatform.ALL,Platform.all());
        platformMap.put(Constants.PushPlatform.ANDROID,Platform.android());
        platformMap.put(Constants.PushPlatform.IOS,Platform.ios());
    }

    @Test
    public void pushTest(){
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushObject_android_and_ios();
//        // For push, all you need do is to build PushPayload object.
//        PushPayload payload = buildPushObject_all_alias_alert();
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
            System.out.println(result);
            // 如果使用 NettyHttpClient，需要手动调用 close 方法退出进程
            // If uses NettyHttpClient, call close when finished sending request, otherwise process will not exit.
            // jpushClient.close();
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

    public static PushPayload buildPushObject_android_and_ios() {
        Map<String, String> extras = new HashMap<String, String>();
        extras.put("test", "https://community.jiguang.cn/push");
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert("alert content")
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle("Android Title")
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();
    }

    @Test
    public void testSend() throws APIConnectionException, APIRequestException {
        PushData pushData=new PushData();
        pushData.setPlatform(Constants.PushPlatform.ALL);
        pushData.setAudienceType(Constants.PushAudience.REGISTRATION_ID);
        pushData.setRegistrationIds(Arrays.asList("1104a8979233662f116"));
        pushData.setAlert("alert");
        pushData.setTitle("title");
//        Map<String,String> custom=new HashMap<>();
//        custom.put("custom","customValue");
//        pushData.setCustom(custom);
        Map<String,String> extras=new HashMap<>();
        extras.put("extras","extrasValue");
        pushData.setExtras(extras);
        send(pushData);
    }

    public PushResult send(PushData pushData) throws APIConnectionException, APIRequestException {
        PushPayload.Builder builder = PushPayload.newBuilder();
        builder.setPlatform(platformMap.get(pushData.getPlatform()));
        if(Constants.PushAudience.ALL.equals(pushData.getAudienceType())){
            builder.setAudience(Audience.all());
        }else if(Constants.PushAudience.ALIAS.equals(pushData.getAudienceType())){
            builder.setAudience(Audience.alias(pushData.getAlias()));
        }else if(Constants.PushAudience.REGISTRATION_ID.equals(pushData.getAudienceType())){
            builder.setAudience(Audience.registrationId(pushData.getRegistrationIds()));
        }else if(Constants.PushAudience.TAG.equals(pushData.getAudienceType())){
            builder.setAudience(Audience.tag(pushData.getTags()));
        }else{
            throw new APIRequestException(new ResponseWrapper());
        }

        AndroidNotification.Builder androidBuilder = AndroidNotification.newBuilder();
        IosNotification.Builder iosBuilder = IosNotification.newBuilder();
        androidBuilder.setTitle(pushData.getTitle());
        if(!pushData.getCustom().isEmpty()){
            androidBuilder.addCustom(pushData.getCustom());
            iosBuilder.addCustom(pushData.getCustom());
        }
        if(!pushData.getExtras().isEmpty()){
            androidBuilder.addExtras(pushData.getExtras());
            iosBuilder.addExtras(pushData.getExtras());
        }
        iosBuilder.incrBadge(pushData.getBadgeAddNum());

        Notification notification = Notification.newBuilder()
                .setAlert(pushData.getAlert())
                .addPlatformNotification(androidBuilder.build())
                .addPlatformNotification(iosBuilder.build())
                .build();

        PushPayload pushPayload = builder.setNotification(notification).build();
        return send(pushPayload);
    }

    private PushResult send(PushPayload pushPayload) throws APIConnectionException, APIRequestException {
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        return jpushClient.sendPush(pushPayload);
    }

}
