/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * WebRequest
 *
 * @author Jonny.Chang
 * @version WebRequest.java, v 0.1 2021年03月30日 10:30 下午 jonny
 */
public class WebRequest implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6901409366302145961L;

    /**
     * 请求字符集，默认UTF-8
     */
    private String charset;

    /**
     * org.apache.http.entity.ContentType  equals mimeType
     */
    private ContentType contentType;

    private int connectTimeout = 3000;
    private int readTimeout    = 5000;

    /**
     * 业务body对象
     */
    private Object body;

    /**
     * 请求头信息
     */
    private Map<String, String> headers = new HashMap<>();

    /**
     * 请求URL地址
     */
    private String reqUrl;

    /**
     * 不使用内置contentType
     */
    private boolean useSelfContentType = false;

    /**
     * body内容是否需要序列化
     */
    private boolean serialnizeBody = true;

    /**
     * Constructor.
     *
     * @param body the body
     */
    //默认From表单
    public WebRequest(Object body) {
        this.body = body;
        this.charset = "UTF-8";
        this.contentType = ContentType.FORM_URLENCODED;
    }

    /**
     * 指定字符集 contentType默认From表单
     *
     * @param body    请求体
     * @param charset 字符集
     */
    public WebRequest(Object body, String charset) {
        this.body = body;
        this.charset = charset;
        this.contentType = ContentType.FORM_URLENCODED;
    }

    /**
     * 指定字符集 ContentType的构造函数
     *
     * @param body        请求体
     * @param charset     字符集
     * @param contentType 内容类型
     */
    public WebRequest(Object body, String charset, ContentType contentType) {
        this.body = body;
        this.charset = charset;
        this.contentType = contentType;
    }

    /**
     * Gets get charset.
     *
     * @return the get charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * Sets set charset.
     *
     * @param charset the charset
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * Gets get content type.
     *
     * @return the get content type
     */
    public ContentType getContentType() {
        return contentType;
    }

    /**
     * Sets set content type.
     *
     * @param contentType the content type
     */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets get connect timeout.
     *
     * @return the get connect timeout
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Sets set connect timeout.
     *
     * @param connectTimeout the connect timeout
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * Gets get read timeout.
     *
     * @return the get read timeout
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Sets set read timeout.
     *
     * @param readTimeout the read timeout
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    /**
     * Gets get body.
     *
     * @return the get body
     */
    public Object getBody() {
        return body;
    }

    /**
     * Sets set body.
     *
     * @param body the body
     */
    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * Gets get headers.
     *
     * @return the get headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets set headers.
     *
     * @param headers the headers
     */
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Add header.
     *
     * @param key   the key
     * @param value the value
     */
    public void addHeader(String key, String value) {
        if (null == this.headers) {
            this.headers = new HashMap<>();
        }
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return;
        }
        this.headers.put(key, value);
    }

    /**
     * Gets get req url.
     *
     * @return the get req url
     */
    public String getReqUrl() {
        return reqUrl;
    }

    /**
     * Sets set req url.
     *
     * @param reqUrl the req url
     */
    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    /**
     * Is use self content type boolean.
     *
     * @return the boolean
     */
    public boolean isUseSelfContentType() {
        return useSelfContentType;
    }

    /**
     * Sets set use self content type.
     *
     * @param useSelfContentType the use self content type
     */
    public void setUseSelfContentType(boolean useSelfContentType) {
        this.useSelfContentType = useSelfContentType;
    }

    /**
     * Is serialnize body boolean.
     *
     * @return the boolean
     */
    public boolean isSerialnizeBody() {
        return serialnizeBody;
    }

    /**
     * Sets set serialnize body.
     *
     * @param serialnizeBody the serialnize body
     */
    public void setSerialnizeBody(boolean serialnizeBody) {
        this.serialnizeBody = serialnizeBody;
    }

    /**
     * ContentType
     *
     * @version $Id: ContentType.java, v 0.1 Jun 22, 2018 11:38:16 AM junhua.pan Exp $
     */
    public static enum ContentType {
        /**
         * form urlencoded
         */
        FORM_URLENCODED("form", "application/x-www-form-urlencoded"),

        /**
         * json
         */
        JSON("json", "application/json"),

        /**
         * textplatn
         */
        TEXTPLATN("textplain", "text/plain"),
        ;

        private String shortName;
        private String value;

        ContentType(String shortName, String value) {
            this.value = value;
            this.shortName = shortName;
        }

        /**
         * Gets get by short name.
         *
         * @param shortName the short name
         * @return the get by short name
         */
        public static ContentType getByShortName(String shortName) {
            for (ContentType contentType : ContentType.values()) {
                if (contentType.getShortName().equalsIgnoreCase(shortName)) {
                    return contentType;
                }
            }
            return null;
        }

        /**
         * Gets get short name.
         *
         * @return the get short name
         */
        public String getShortName() {
            return shortName;
        }

        /**
         * Sets set short name.
         *
         * @param shortName the short name
         */
        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        /**
         * Gets get value.
         *
         * @return the get value
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets set value.
         *
         * @param value the value
         */
        public void setValue(String value) {
            this.value = value;
        }

    }
}
