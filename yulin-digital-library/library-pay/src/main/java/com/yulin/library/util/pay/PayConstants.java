package com.yulin.library.util.pay;

public class PayConstants {
    public static final class PayState {
        public final static Integer NOT_PAY=0;
        public final static Integer HAVE_PAY=1;
    }

    public static final class PayModel{
        public final static Integer ZHI_FU_BAO=1;
        public final static Integer WEI_XIN=2;
        public final static Integer YU_E=3;
        public final static Integer OUTHER=99;
    }

    public static final class OrderType{
        public final static String USER_BOOK_ORDER="userBookOrder";
        public final static String USER_RECHARGE_ORDER="userRechargeOrder";
        public final static String USER_VIP_ORDER="userVipOrder";
    }

    public static final class VipState{
        public final static Integer VIP=1;
        public final static Integer NOT_VIP=0;
    }
}
