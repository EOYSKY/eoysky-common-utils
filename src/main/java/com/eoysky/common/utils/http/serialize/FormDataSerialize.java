/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.http.serialize;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;

/**
 * FormDataSerialize
 *
 * @author Jonny.Chang
 * @version FormDataSerialize.java, v 0.1 2021年03月30日 10:30 下午 jonny
 */
public class FormDataSerialize implements DataSerialize {

    @Override
    public byte[] serialize(Object data, Charset charset) {
        if (null == data) {
            return new byte[0];
        }
        if (data instanceof String) {
            return ((String) data).getBytes(charset);
        }

        String jsonString = JSON.toJSONString(data);
        Map<String, Object> map = JSON.parseObject(jsonString,
                new TypeReference<Map<String, Object>>() {
                });

        List<String> params = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key).getClass().equals(String.class)) {
                params.add(key + "=" + map.get(key));
            } else {
                params.add(key + "=" + JSON.toJSONString(map.get(key)));
            }
        }
        return StringUtils.join(params.toArray(), "&").getBytes(charset);
    }

}
