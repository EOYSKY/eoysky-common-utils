/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.enhanced;

import com.eoysky.common.utils.enums.ResultCodeEnum;
import com.eoysky.common.utils.model.BasePageResult;
import com.eoysky.common.utils.model.BaseResult;
import com.eoysky.common.utils.model.ServicePageResult;
import com.eoysky.common.utils.model.ServiceResult;

/**
 * ApiResultEnhanced
 *
 * @author Jonny.Chang
 * @version ApiResultEnhanced.java, v 0.1 2021年03月30日 10:33 下午 jonny
 */
public abstract class ApiResultEnhanced {

    /**
     * 通过 service 结果构建 api 返回结果
     *
     * @param serviceResult serviceResult
     * @param <T> T
     * @return BaseResult
     */
    public static <T> BaseResult<T> buildResByServiceRes(ServiceResult<T> serviceResult) {
        ResultCodeEnum resultCode = serviceResult.getResultCode();
        if (resultCode == ResultCodeEnum.UNAUTHORIZED_ACCESS) {
            return new BaseResult<>(false, resultCode.getCode(), serviceResult.getData(), serviceResult.getResultMsg());
        }
        return new BaseResult<>(ResultCodeEnum.SUCCESS == resultCode, resultCode.getCode(),
                serviceResult.getData(), resultCode.getMsg());
    }

    /**
     * 通过 service page 结果构建api返回结果
     *
     * @param serviceResult serviceResult
     * @param <T> T
     * @return BasePageResult
     */
    public static <T> BasePageResult<T> buildPageResByServiceRes(ServicePageResult<T> serviceResult) {
        ResultCodeEnum resultCode = serviceResult.getResultCode();
        return new BasePageResult<>(ResultCodeEnum.SUCCESS == resultCode, resultCode.getCode(),
                serviceResult.getData(), resultCode.getMsg(), serviceResult.getPageNo(), serviceResult.getPageSize(),
                serviceResult.getTotalCount());
    }
}
