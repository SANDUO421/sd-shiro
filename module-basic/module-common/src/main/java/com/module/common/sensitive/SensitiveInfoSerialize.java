package com.module.common.sensitive;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;

/**
 * 脱敏序列化类
 *
 * @author 三多
 * @Time 2020/3/1
 */
public class SensitiveInfoSerialize extends JsonSerializer<String> implements ContextualSerializer {
    private SensitiveType type;

    public SensitiveInfoSerialize() {
    }

    public SensitiveInfoSerialize(final SensitiveType type) {
        this.type = type;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        switch (this.type) {
            case CHINESE_NAME: {
                jsonGenerator.writeString(SensitiveInfoUtils.chineseName(s));
                break;
            }
            case ID_CARD: {
                jsonGenerator.writeString(SensitiveInfoUtils.idCardNum(s));
                break;
            }
            case FIXED_PHONE: {
                jsonGenerator.writeString(SensitiveInfoUtils.fixedPhone(s));
                break;
            }
            case MOBILE_PHONE: {
                jsonGenerator.writeString(SensitiveInfoUtils.mobilePhone(s));
                break;
            }
            case ADDRESS: {
                jsonGenerator.writeString(SensitiveInfoUtils.address(s, 4));
                break;
            }
            case EMAIL: {
                jsonGenerator.writeString(SensitiveInfoUtils.email(s));
                break;
            }
            case BANK_CARD: {
                jsonGenerator.writeString(SensitiveInfoUtils.bankCard(s));
                break;
            }
            case CNAPS_CODE: {
                jsonGenerator.writeString(SensitiveInfoUtils.cnapsCode(s));
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        return null;
    }
}
