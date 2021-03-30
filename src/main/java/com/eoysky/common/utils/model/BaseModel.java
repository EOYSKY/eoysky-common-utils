/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * BaseModel
 *
 * @author Jonny.Chang
 * @version BaseModel.java, v 0.1 2021年03月30日 10:31 下午 jonny
 */
public abstract class BaseModel implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6158047154723612101L;

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
