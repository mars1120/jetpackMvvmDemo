package com.ithome11.jetpackmvvmdemo.main.net.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 錯誤訊息處理格式
 */

public class ApiException extends Exception {
    private int code;
    private String displayMessage;

    public ApiException(int code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    /**
     * 未定義
     */
    public static final int UNKNOWN = 1000;

    /**
     * parse錯誤
     */
    public static final int PARSE_ERROR = 1001;

    /**
     * 網路錯誤
     */
    public static final int NETWORK_ERROR = 1002;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //parse錯誤
            ex = new ApiException(ApiException.PARSE_ERROR, e.getMessage());
            return ex;
        } else if (e instanceof ConnectException) {
            //http錯誤
            ex = new ApiException(ApiException.NETWORK_ERROR, e.getMessage());
            return ex;
        } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
            //連接錯誤
            ex = new ApiException(ApiException.NETWORK_ERROR, e.getMessage());
            return ex;
        } else if (e instanceof ApiException) {
            return (ApiException) e;
        } else {
//            val errorJsonString = error.response().errorBody()?.string()
//            message = JsonParser().parse(errorJsonString)
//                    .asJsonObject["message"]
//                    .asString
//                    String errorJsonString =e.
            //未定義
            ex = new ApiException(ApiException.UNKNOWN, e.getMessage());
            return ex;
        }
    }


}
