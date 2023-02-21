package com.xm.jy.job_51.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @description 接口调用工具
 * @author albert.fang
 * @date 2020-10-22 13:43
 * @version V1.0
 */
public class RequestUtil {

    public static JSONObject httpClientGet(String url, JSONObject params) throws Exception {
        Instant begin = Instant.now();
        JSONObject jsonObject = new JSONObject();
        try {
            if (null != params) {
                StringBuilder sb = new StringBuilder();
                sb.append(url).append("?");
                params.forEach((key, value) -> sb.append(key).append("=").append(value).append("&"));
                sb.deleteCharAt(sb.length() - 1);
                url = sb.toString();
            }
            // 开始调用接口
            HttpClient httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            httpClient.getParams().setContentCharset("utf-8");
            GetMethod method = new GetMethod(url);
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 3000);
            httpClient.executeMethod(method);
            if(method.getStatusCode() >= 400){
                throw new RuntimeException(String.format("调用接口异常，响应状态码：%s。%s",method.getStatusCode(),method.getStatusLine().toString()));
            }
            String responseMsg = method.getResponseBodyAsString().trim();
            jsonObject = JSON.parseObject(responseMsg);
            Instant end = Instant.now();
        } catch (Exception e) {
            throw e;
        }
        return jsonObject;
    }

}
