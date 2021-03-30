/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.http.serialize;

import java.nio.charset.Charset;

/**
 * TextPlainDataSerialize
 *
 * @author Jonny.Chang
 * @version TextPlainDataSerialize.java, v 0.1 2021年03月30日 10:31 下午 jonny
 */
public class TextPlainDataSerialize implements DataSerialize {

    @Override
    public byte[] serialize(Object data, Charset charset) {
        if (null == data) {
            return null;
        }
        String content = ((String) data);
        return content.getBytes(charset);
    }

}
