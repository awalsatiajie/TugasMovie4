package com.awalsatiajie.tugasmovie4.api;

import okhttp3.ResponseBody;

public class JsonApiResponse {
    private ResponseBody responseBody;
    private Throwable throwable;

    public JsonApiResponse (ResponseBody responseBody){
        this.responseBody = responseBody;
        this.throwable = null;
    }

    public JsonApiResponse (Throwable throwable){
        this.responseBody = null;
        this.throwable = throwable;
    }

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
