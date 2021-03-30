/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.model;

import com.eoysky.common.utils.enums.ResultCodeEnum;

/**
 * ServicePageResult
 *
 * @author Jonny.Chang
 * @version ServicePageResult.java, v 0.1 2021年03月30日 10:32 下午 jonny
 */
public class ServicePageResult<T> extends ServiceResult<T> {

    /**
     * 当前页码
     */
    private int pageNo;

    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 总条目数
     */
    private int totalCount;

    /**
     * Constructor.
     */
    public ServicePageResult() {
    }

    /**
     * Constructor.
     *
     * @param data       the data
     * @param pageNo     the current page
     * @param pageSize   the page size
     * @param totalCount the total
     */
    public ServicePageResult(T data, int pageNo, int pageSize, int totalCount) {
        super(data);
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

    /**
     * Constructor.
     *
     * @param resultCode the result code
     */
    public ServicePageResult(ResultCodeEnum resultCode) {
        super(resultCode);
    }

    /**
     * Gets get page no.
     *
     * @return the get page no
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * Sets set page no.
     *
     * @param pageNo the page no
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * Gets get page size.
     *
     * @return the get page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets set page size.
     *
     * @param pageSize the page size
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Gets get total count.
     *
     * @return the get total count
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Sets set total count.
     *
     * @param totalCount the total count
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
