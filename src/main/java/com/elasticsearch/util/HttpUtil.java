package com.elasticsearch.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author quanroon.ysq
 * @version 1.0.0
 * @date 2020/2/29 17:57
 * @content
 */
public class HttpUtil {


    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    static final String charsetName = "UTF-8";

    public HttpUtil() {
    }

    public static String doGet(String url) throws MalformedURLException, IOException {

        logger.info("Get请求 url: " + url);


        HttpURLConnection urlconn = null;
        urlconn = (HttpURLConnection) (new URL(url)).openConnection();
        urlconn.getRequestProperties();
        urlconn.setRequestProperty("content-type", "text/html");
        urlconn.setRequestMethod("GET");
        urlconn.setConnectTimeout(10000);
        urlconn.setReadTimeout(10000);
        urlconn.setDoInput(true);
        BufferedReader rd = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "UTF-8"));
        String temp = null;
        StringBuffer sb = new StringBuffer();

        for (temp = rd.readLine(); temp != null; temp = rd.readLine()) {
            sb.append(temp);
        }

        rd.close();
        urlconn.disconnect();

        logger.info("Get请求响应: " + sb.toString());


        return sb.toString();
    }

    public static String doPost(String url, String value) throws MalformedURLException, IOException {
        logger.info("Post请求 url: " + url);
        logger.info("Post请求 body: " + value);

        HttpURLConnection urlconn = null;
        urlconn = (HttpURLConnection) (new URL(url)).openConnection();
        urlconn.getRequestProperties();
        urlconn.setRequestProperty("content-type", "application/json");
        urlconn.setRequestMethod("POST");
        urlconn.setConnectTimeout(10000);
        urlconn.setReadTimeout(10000);
        urlconn.setDoInput(true);
        urlconn.setDoOutput(true);
        urlconn.getOutputStream().write(value.getBytes());
        urlconn.getOutputStream().close();
        BufferedReader rd = new BufferedReader(new InputStreamReader(urlconn.getInputStream(), "UTF-8"));
        String temp = null;
        StringBuffer sb = new StringBuffer();

        for (temp = rd.readLine(); temp != null; temp = rd.readLine()) {
            sb.append(temp);
        }

        rd.close();
        urlconn.disconnect();
        logger.info("Post请求 响应: " + sb.toString());

        return sb.toString();
    }
}
