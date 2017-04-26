package com.upcoding.buy.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Heboot on 2016/12/27.
 */

public class ApiRequest {

    private String httpUrl;

    private int method;

    private String debugTag;

    private boolean isGuide;

    private Map<String, String> params = new HashMap<>();

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getDebugTag() {
        return debugTag;
    }

    public void setDebugTag(String debugTag) {
        this.debugTag = debugTag;
    }

    public boolean isGuide() {
        return isGuide;
    }

    public void setGuide(boolean guide) {
        isGuide = guide;
    }

    public ApiRequest(String httpUrl, int method, boolean isGuide) {
        this.httpUrl = httpUrl;
        this.method = method;
        this.isGuide = isGuide;
    }

    public String matchMethod(int method) {
        String methodString = "GET";
        switch (method) {
            case 0:
                methodString = "GET";
                break;
            case 1:
                methodString = "POST";
                break;
            case 2:
                methodString = "PUT";
                break;
            case 3:
                methodString = "DELETE";
                break;
            case 4:
                methodString = "HEAD";
                break;
            case 5:
                methodString = "OPTIONS";
                break;
            case 6:
                methodString = "TRACE";
                break;
            case 7:
                methodString = "PATCH";
                break;
        }
        return methodString;
    }

    public interface Method {
        int DEPRECATED_GET_OR_POST = -1;
        int GET = 0;
        int POST = 1;
        int PUT = 2;
        int DELETE = 3;
        int HEAD = 4;
        int OPTIONS = 5;
        int TRACE = 6;
        int PATCH = 7;
    }

    public void addParams(String key, String value) {
        if (value != null) {
            params.put(key, value);
        }
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
