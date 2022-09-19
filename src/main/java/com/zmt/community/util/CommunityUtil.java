package com.zmt.community.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class CommunityUtil {

    /**
     *  生成随机字符串,用于头像、文件命名，密码加密
     * @return 随机字符串
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     *  MD5加密
     * @param key 密码
     * @return 加密的密码
     */
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    /**
     * 根据参数信息生成JSON字符串，重载是为了处理某部分为null的情况
     * @param code 编码
     * @param msg 提示信息
     * @param map 业务数据
     * @return JSON字符串
     */
    public static String getJSONString(int code, String msg, Map<String, Object> map) {
        //jar包：Fastjson的API
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if (map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        return getJSONString(code, msg, null);
    }

    public static String getJSONString(int code) {
        return getJSONString(code, null, null);
    }


}
