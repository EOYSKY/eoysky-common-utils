/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.model;

import java.io.Serializable;

import com.eoysky.common.utils.enums.ResultCodeEnum;

/**
 * BaseResult
 *
 * @author Jonny.Chang
 * @version BaseResult.java, v 0.1 2021年03月30日 10:31 下午 jonny
 */
public class  BaseResult<T> implements Serializable {

    private static final long serialVersionUID = -6715588764153316573L;

    /**
     * 操作结果 默认false
     */
    private boolean success = false;

    /**
     * 操作结果码
     */
    private String resultCode;

    /**
     * 提示信息
     */
    private String resultMsg;

    /**
     * 对象
     */
    private T data;

    /**
     * Constructor.
     */
    public BaseResult() {
    }

    /**
     * Constructor.
     *
     * @param success    the success
     * @param resultCode the result code
     * @param data       the data
     * @param resultMsg  the msg
     */
    public BaseResult(boolean success, String resultCode, T data, String resultMsg) {
        this.success = success;
        this.resultCode = resultCode;
        this.data = data;
        this.resultMsg = resultMsg;
    }

    /**
     * Constructor.
     *
     * @param codeEnum the code enum
     */
    public BaseResult(ResultCodeEnum codeEnum) {
        this.success = codeEnum == ResultCodeEnum.SUCCESS;
        this.resultCode = codeEnum.getCode();
        this.resultMsg = codeEnum.getMsg();
    }

    /**
     * Constructor.
     *
     * @param codeEnum the code enum
     * @param data     the data
     */
    public BaseResult(ResultCodeEnum codeEnum, T data) {
        this.data = data;
        this.success = codeEnum == ResultCodeEnum.SUCCESS;
        this.resultCode = codeEnum.getCode();
        this.resultMsg = codeEnum.getMsg();
    }

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets set success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Gets get result code.
     *
     * @return the get result code
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Sets set result code.
     *
     * @param resultCode the result code
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Gets get data.
     *
     * @return the get data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets set data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets get msg.
     *
     * @return the get msg
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * Sets set msg.
     *
     * @param resultMsg the msg
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
