/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.http.serialize;

import java.nio.charset.Charset;

/**
 * DataSerialize
 *
 * @author Jonny.Chang
 * @version DataSerialize.java, v 0.1 2021年03月30日 10:30 下午 jonny
 */
public interface DataSerialize {

    /**
     * Serialize byte [ ].
     *
     * @param data    the data
     * @param charset the charset
     * @return the byte [ ]
     */
    byte[] serialize(Object data, Charset charset);
}
