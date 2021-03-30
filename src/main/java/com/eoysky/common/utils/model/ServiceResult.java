/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.model;

import com.eoysky.common.utils.enums.ResultCodeEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * ServiceResult
 *
 * @author Jonny.Chang
 * @version ServiceResult.java, v 0.1 2021年03月30日 10:32 下午 jonny
 */
public class ServiceResult<T> {

    /**
     * 处理结果
     */
    private T data;

    /**
     * 结果码
     */
    private ResultCodeEnum resultCode;

    /**
     * 结果码扩展描述
     */
    private String resultMsg;

    /**
     * 默认构造函数
     */
    public ServiceResult() {
    }

    /**
     * 构造函数. 默认构建成功数据
     *
     * @param data the data
     */
    public ServiceResult(T data) {
        this.data = data;
        this.resultCode = ResultCodeEnum.SUCCESS;
    }

    /**
     * 构造函数. 多用于构建失败结果
     *
     * @param resultCode the result code
     */
    public ServiceResult(ResultCodeEnum resultCode) {
        this.resultCode = resultCode;
    }
    /**
     * 构造函数.
     *
     * @param data       the data
     * @param resultCode the result code
     */
    public ServiceResult(T data, ResultCodeEnum resultCode) {
        this.data = data;
        this.resultCode = resultCode;
    }

    /**
     * Constructor.
     *
     * @param resultCode the result code
     * @param resultMsg  the result msg
     */
    public ServiceResult(ResultCodeEnum resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess() {
        return ResultCodeEnum.SUCCESS.equals(this.resultCode);
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
     * Gets get result code.
     *
     * @return the get result code
     */
    public ResultCodeEnum getResultCode() {
        return resultCode;
    }

    /**
     * Sets set result code.
     *
     * @param resultCode the result code
     */
    public void setResultCode(ResultCodeEnum resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Gets get result msg.
     *
     * @return the get result msg
     */
    public String getResultMsg() {
        if (null != this.resultCode && StringUtils.isNotBlank(resultMsg)) {
            return this.resultCode.getMsg() + ":" + resultMsg;
        }
        return resultMsg;
    }

    /**
     * Sets set result msg.
     *
     * @param resultMsg the result msg
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}
