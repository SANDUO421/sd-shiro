package com.module.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @description:    Json工具类
 * @author:         sanduo
 * @date:           2020/2/20 12:53
 * @version:        1.0
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public JsonUtils() {
    }

    /**
     * 序列化
     * @param json  字符串
     * @param clazz  对象实例
     * @param <T>  类型
     * @return  返回值
     */
    public static <T> T serializable(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return mapper.readValue(json, clazz);
            } catch (IOException var3) {
                return null;
            }
        }
    }

    /**
     * 序列化
     * @param json 字符串
     * @param reference 参数
     * @param <T> 类型
     * @return 返回值
     */
    public static <T> T serializable(String json, TypeReference<T> reference) {
        if (StringUtils.isEmpty(json)) {
            return null;
        } else {
            try {
                return mapper.readValue(json, reference);
            } catch (IOException var3) {
                return null;
            }
        }
    }

    /**
     * 反序列化
     * @param json 字符串
     * @return 返回值
     */
    public static String deserializer(Object json) {
        if (json == null) {
            return null;
        } else {
            try {
                return mapper.writeValueAsString(json);
            } catch (JsonProcessingException var2) {
                return null;
            }
        }
    }

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}