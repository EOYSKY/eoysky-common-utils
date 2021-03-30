/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.http.serialize;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JsonDataSerialize
 *
 * @author Jonny.Chang
 * @version JsonDataSerialize.java, v 0.1 2021年03月30日 10:30 下午 jonny
 */
public class JsonDataSerialize implements DataSerialize {

    @Override
    public byte[] serialize(Object data, Charset charset) {
        if (null == data) {
            return null;
        }
        return JSON.toJSONString(data, SerializerFeature.WRITE_MAP_NULL_FEATURES).getBytes(charset);
    }
}
