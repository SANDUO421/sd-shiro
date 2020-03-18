package com.yulin.library.util.push;

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
import com.yulin.library.util.Constants;
import com.yulin.library.util.push.entity.PushData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PushUtils {

    private static Map<String,Platform> platformMap=new HashMap<>();

    static {
        platformMap.put(Constants.PushPlatform.ALL,Platform.all());
        platformMap.put(Constants.PushPlatform.ANDROID,Platform.android());
        platformMap.put(Constants.PushPlatform.IOS,Platform.ios());
    }

    @Autowired
    private JPushClient jpushClient;

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
        return jpushClient.sendPush(pushPayload);
    }


}
