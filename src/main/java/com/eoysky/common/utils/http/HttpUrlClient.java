/*
  EOYSKY.COM Inc. Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.eoysky.common.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.eoysky.common.utils.http.serialize.DataSerialize;
import com.eoysky.common.utils.http.serialize.DefaultDataSerialize;
import com.eoysky.common.utils.http.serialize.FormDataSerialize;
import com.eoysky.common.utils.http.serialize.JsonDataSerialize;
import com.eoysky.common.utils.http.serialize.TextPlainDataSerialize;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * HttpURLClient
 *
 * @author Jonny.Chang
 * @version HttpUrlClient.java, v 0.1 2021年03月30日 10:30 下午 jonny
 */
public final class HttpUrlClient {

    /**
     * 请求报文序列化器配置集合
     */
    private final static Map<String, DataSerialize> DataSerializers = new HashMap<>(
            5);

    static {
        DataSerializers.put("default", new DefaultDataSerialize());
        DataSerializers.put("application/x-www-form-urlencoded", new FormDataSerialize());
        DataSerializers.put("application/json", new JsonDataSerialize());
        DataSerializers.put("text/plain", new TextPlainDataSerialize());
    }

    /**
     * 发送 post 请求
     *
     * @param reqUrl                请求URL
     * @param req                   请求体对象
     * @param headerKV              请求Header集合
     * @param unuseInnerContentType 是否使用内置的content-type： 为true时，不组装Content-Type和Content-Length到请求header中去
     * @return 返回报文
     */
    public static String post(String reqUrl, WebRequest req, Map<String, String> headerKV,
            boolean unuseInnerContentType) {
        try {
            HttpURLConnection connection = connectionAssemble(reqUrl, req);
            Charset.forName(req.getCharset());
            String charset = req.getCharset();
            String contentType = req.getContentType().getValue();

            Object body = req.getBody();
            DataSerialize serializer = DataSerializers.get(contentType);
            if (null == serializer) {
                serializer = DataSerializers.get("default");
            }
            byte[] data = serializer.serialize(body, Charset.forName(charset));

            if (!unuseInnerContentType && !req.isUseSelfContentType()) {
                connection.setRequestProperty("Content-Type", contentType + " ;charset=" + charset);
                connection.setRequestProperty("Content-Length", String.valueOf(data.length));
            }
            if (!CollectionUtils.isEmpty(Collections.singleton(headerKV))) {
                for (String key : headerKV.keySet()) {
                    connection.setRequestProperty(key, headerKV.get(key));
                }
            }

            OutputStream outStream = connection.getOutputStream();
            if (req.isSerialnizeBody()) {
                outStream.write(data);
            } else {
                if (null != body) {
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, charset));
                    out.print(body);
                    out.close();
                }
            }
            outStream.flush();
            outStream.close();

            String result = null;
            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                result = streamToString(inputStream, req.getCharset());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * POST 请求
     *
     * @param reqUrl   请求URL
     * @param req      请求body对象
     * @param headerKV 请求header集合
     * @return 字符串
     */
    public static String post(String reqUrl, WebRequest req, Map<String, String> headerKV) {
        return post(reqUrl, req, headerKV, false);
    }

    /**
     * Post for debugging string.
     *
     * @param reqUrl   the req url
     * @param req      the req
     * @param headerKV the header kv
     * @return the string
     * @throws Exception the exception
     */
    public static String postForDebugging(String reqUrl, WebRequest req,
            Map<String, String> headerKV) throws Exception {
        HttpURLConnection connection = connectionAssemble(reqUrl, req);
        Charset.forName(req.getCharset());
        String charset = req.getCharset();
        String contentType = req.getContentType().getValue();

        DataSerialize serializer = DataSerializers.get(contentType);
        if (null == serializer) {
            serializer = DataSerializers.get("default");
        }
        byte[] data = serializer.serialize(req.getBody(), Charset.forName(charset));

        connection.setRequestProperty("Content-Type", contentType + " ;charset=" + charset);
        connection.setRequestProperty("Content-Length", String.valueOf(data.length));
        if (!CollectionUtils.isEmpty(Collections.singleton(headerKV))) {
            for (String key : headerKV.keySet()) {
                connection.setRequestProperty(key, headerKV.get(key));
            }
        }

        OutputStream outStream = connection.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();

        String result = null;
        if (connection.getResponseCode() == 200) {
            result = streamToString(connection.getInputStream(), req.getCharset());
        } else {
            String error = streamToString(connection.getErrorStream(), req.getCharset());
            System.out.println("logger, \"({0})HttpURLClient Post调用出错：url={1}，请求参数:{2}，出错原因：{3}\",\n"
                    + "                    TracerContextUtil.getTraceId(), reqUrl, ToStringBuilder.reflectionToString(req), error");
        }
        return result;
    }

    /**
     * POST 请求
     *
     * @param reqUrl 请求URL
     * @param req    请求body对象
     * @return 字符串
     */
    public static String post(String reqUrl, WebRequest req) {
        return post(reqUrl, req, req.getHeaders());
    }

    /**
     * POST 请求
     *
     * @param req 请求body对象
     * @return 字符串
     */
    public static String post(WebRequest req) {
        return post(req.getReqUrl(), req, req.getHeaders());
    }

    /**
     * GET请求
     *
     * @param reqUrl      请求URL
     * @param queryString 请求QueryString
     * @param charset     字符集
     * @param headerKV    请求header集合
     * @return 结果内容字符串
     */
    public static String getRequestMethod(String reqUrl, String queryString, String charset,
            Map<String, String> headerKV) {

        HttpURLConnection connection;
        try {

            if (StringUtils.isNotEmpty(queryString)) {
                if (reqUrl.contains("?")) {
                    reqUrl += "&" + queryString;
                } else {
                    reqUrl += "?" + queryString;
                }
            }
            connection = getHttpURLConnection(reqUrl, headerKV);
            return streamToString(connection.getInputStream(), charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpURLConnection getHttpURLConnection(String reqUrl, Map<String, String> headerKV) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(reqUrl);

            if (reqUrl.startsWith("https")) {
                trustAllHttps();
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(8000);
            connection.setRequestMethod("GET");
            if (!CollectionUtils.isEmpty(Collections.singleton(headerKV))) {
                for (String key : headerKV.keySet()) {
                    connection.setRequestProperty(key, headerKV.get(key));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Gets get input stream.
     *
     * @param reqUrl      the req url
     * @param queryString the query string
     * @param headerKV    the header kv
     * @return the get input stream
     */
    public static InputStream getInputStream(String reqUrl, String queryString,
            Map<String, String> headerKV) {

        HttpURLConnection connection;
        try {
            if (StringUtils.isNotBlank(queryString)) {
                if (reqUrl.contains("?")) {
                    reqUrl += "&" + queryString;
                } else {
                    reqUrl += "?" + queryString;
                }
            }

            connection = getHttpURLConnection(reqUrl, headerKV);

            return connection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * GET请求
     *
     * @param reqUrl      请求URL
     * @param queryString 请求QueryString
     * @param charset     字符集
     * @return 结果内容字符串
     */
    public static String getRequestMethod(String reqUrl, String queryString, String charset) {
        return getRequestMethod(reqUrl, queryString, charset, null);
    }

    /**
     * GET请求 - 字符集默认为UTF-8
     *
     * @param reqUrl      请求URL
     * @param queryString 请求QueryString
     * @return 结果内容字符串
     */
    public static String getRequestMethod(String reqUrl, String queryString) {
        return getRequestMethod(reqUrl, queryString, "UTF-8", null);
    }

    /**
     * 输出流转换为字符串
     *
     * @param input   InputStream
     * @param charset 字符集
     * @return 结果字符串
     */
    private static String streamToString(InputStream input, String charset) {
        StringBuilder builder = new StringBuilder();
        try {
            if (null == charset || "".equals(charset.trim())) {
                charset = "UTF-8";
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(input, charset));
            String con;
            while ((con = br.readLine()) != null) {
                builder.append(con);
            }
            br.close();
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 信任所有https请求
     */
    private static void trustAllHttps() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[1];
            TrustManager tm = new SelfTrustManager();
            trustAllCerts[0] = tm;
            SSLContext sc = SSLContext.getInstance("TLSv1");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HttpURLConnection connectionAssemble(String reqUrl, WebRequest req) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(reqUrl);

            if (reqUrl.startsWith("https")) {
                trustAllHttps();
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }
            connection.setRequestMethod("POST");

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            connection.setConnectTimeout(req.getConnectTimeout());
            connection.setReadTimeout(req.getReadTimeout());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 自定义的忽略所有https证书
     *
     * @version $Id: SelfTrustManager.java, v 0.1 Sep 3, 2018 9:24:11 PM junhua.pan Exp $
     */
    public static class SelfTrustManager implements X509TrustManager {

        /**
         * @see X509TrustManager#checkClientTrusted(X509Certificate[], String)
         */
        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) {
        }

        /**
         * @see X509TrustManager#checkServerTrusted(X509Certificate[], String)
         */
        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) {
        }

        /**
         * @see X509TrustManager#getAcceptedIssuers()
         */
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
