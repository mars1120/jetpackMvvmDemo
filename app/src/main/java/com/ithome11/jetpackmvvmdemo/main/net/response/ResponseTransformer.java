package com.ithome11.jetpackmvvmdemo.main.net.response;


import com.google.gson.Gson;
import com.ithome11.jetpackmvvmdemo.main.net.exception.ApiException;
import com.ithome11.jetpackmvvmdemo.main.net.obj.BaseResponseObj;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;


public class ResponseTransformer {
    public static <T> ObservableTransformer<String, T> handleResult(Class<T> clazz) {
        return (ObservableTransformer<String, T>) upstream ->
                upstream.flatMap(new ResponseFunction(clazz)).onErrorResumeNext(new ErrorResumeFunction());
    }

    private static class ResponseFunction<T> implements Function<String, ObservableSource> {
        Class<T> clazz;

        public ResponseFunction(Class clazz) {
            this.clazz = clazz;
        }

        @Override
        public ObservableSource apply(String tResponse) throws Exception {
            BaseResponseObj response = new Gson().fromJson(tResponse, BaseResponseObj.class);
            //攔截
            if (response != null && !response.getStatus().equals("ok")) {
                return Observable.error(new ApiException(400, response.getMessage()));
            } else if (clazz.equals(String.class))
                return Observable.just(tResponse);
            else
                return Observable.just(new Gson().fromJson(tResponse, clazz));
        }

    }

    //異常判斷
    private static class ErrorResumeFunction<T> implements Function<Throwable, ObservableSource<T>> {
        @Override
        public ObservableSource<T> apply(Throwable throwable) throws Exception {
            return Observable.error(ApiException.handleException(throwable));
        }
    }


}
