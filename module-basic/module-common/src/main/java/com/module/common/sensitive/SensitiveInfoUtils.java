package com.module.common.sensitive;

import org.apache.commons.lang3.StringUtils;

/**
 * 脱敏处理工具类
 *
 * @author 三多
 * @Time 2020/3/1
 */
public class SensitiveInfoUtils {


    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**>
     *
     * @param fullName
     * @return
     */
    public static String chineseName(final String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        final String name = StringUtils.left(fullName, 1);
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
    }

    /**
     * [固定电话] 后四位，其他隐藏<例子：****1234>
     *
     * @param num
     * @return
     */
    public static String fixedPhone(final String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*");
    }

    /**
     * @description: [手机号码] 前三位，后四位，其他隐藏<例子:138******1234>
     * @author: sanduo
     * @date: 2020/3/1 12:38
     * @version: 1.0
     */
    public static String mobilePhone(final String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils
                .left(num, 3)
                .concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"), "**"));
    }

    /**
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护<例子：北京市海淀区****>
     *
     * @param address       地址
     * @param sensitiveSize 敏感信息长度
     * @return 返回值
     */
    public static String address(final String address, final int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return "";
        }
        final int length = StringUtils.length(address);
        return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
    }

    /**
     * [身份证号] 显示最后四位，其他隐藏。共计18位或者15位。<例子：*************5762>
     * @param id
     * @return
     */
    public static String idCardNum(final String id) {
        if (StringUtils.isBlank(id)) {
            return "";
        }

        return StringUtils
                .left(id, 3)
                .concat(StringUtils
                        .removeStart(StringUtils.leftPad(StringUtils.right(id, 3), StringUtils.length(id), "*"), "***"));
    }
    /**
     * @description: [电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示<例子:g**@163.com>
     * @author: sanduo
     * @date: 2020/3/1 13:15
     * @version: 1.0
     */
    public static String email(final String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        final int index = StringUtils.indexOf(email, "@");
        if (index <= 1) {
            return email;
        } else {
            return StringUtils.rightPad(StringUtils.left(email, 1), index, "*")
                    .concat(StringUtils.mid(email, index, StringUtils.length(email)));
        }
    }

    /**
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234>
     *
     * @param cardNum 卡号
     * @return 返回
     */
    public static String bankCard(final String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        return StringUtils
                .left(cardNum, 6)
                .concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
    }

    /**
     * [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号<例子:12********>
     *
     * @param code
     * @return
     */
    public static String cnapsCode(final String code) {
        if (StringUtils.isBlank(code)) {
            return "";
        }
        return StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");
    }

    public static void main(String[] args) {
        System.out.println(mobilePhone("18700191127"));
        System.out.println(email("123@163.com"));
        System.out.println(bankCard("6227000428010562722"));
        System.out.println(idCardNum("610124199210081816"));
    }


}
