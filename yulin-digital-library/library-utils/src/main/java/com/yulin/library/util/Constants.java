package com.yulin.library.util;

public class Constants {

    public static final Integer NOT_DELETE=0;
    public static final Integer IS_DELETE=1;

    public static final class ReturnValue {
        public static final String SUCCESS_CODE = "200";
        public static final String SUCCESS_MSG = "success";
        public static final String ERROR_CODE = "500";
    }

    public static final class AuthenticationMethod{
        public static final String AUTH_TYPE="auth_type";
    }

    public static final class AuditState{
        public static final Integer WAITCONFIRM=0;
        public static final Integer INSANITY=1;
        public static final Integer AUDIT_FAILS=100;
    }

    public static final class PushAudience {
        public final static String ALL="all";
        public final static String TAG="tag";
        public final static String ALIAS="alias";
        public final static String REGISTRATION_ID="registration_id";
    }

    public static final class PushPlatform {
        public final static String ALL="all";
        public final static String ANDROID="android";
        public final static String IOS="ios";
    }
}
