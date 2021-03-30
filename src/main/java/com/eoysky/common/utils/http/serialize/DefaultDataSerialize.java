/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.http.serialize;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;

/**
 * DefaultDataSerialize
 *
 * @author Jonny.Chang
 * @version DefaultDataSerialize.java, v 0.1 2021年03月30日 10:30 下午 jonny
 */
public class DefaultDataSerialize implements DataSerialize {

    /**
     * @see DataSerialize#serialize(Object, Charset)
     */
    @Override
    public byte[] serialize(Object data, Charset charset) {

        if (null == data) {
            return null;
        }

        byte[] bytes = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {

            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(data);

            bytes = bo.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (oo != null) {
                    oo.close();
                }
                if (bo != null) {
                    bo.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
