package com.xm.jy.job_51.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacksonUtil {

    private static final ObjectMapper defaultObjectMapper = new ObjectMapper();

    static {
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        defaultObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
    }

    public static String toJsonString(Object source) {
        try {
            return defaultObjectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            log.error("toJsonString Error: ", e);
            return null;
        }
    }

    public static <T> T toObj(String str, Class<T> clazz) {
        try {
            return defaultObjectMapper.readValue(str, clazz);
        } catch (Exception e) {
            log.error("toObj Error: {},{}", str, e);
            return null;
        }
    }
}
