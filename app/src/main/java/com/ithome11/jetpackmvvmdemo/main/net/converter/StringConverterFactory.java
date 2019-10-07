package com.ithome11.jetpackmvvmdemo.main.net.converter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class StringConverterFactory extends Converter.Factory {

    private static StringConverterFactory INSTANCE;

    //獲取單例
    public static StringConverterFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (StringConverterFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new StringConverterFactory();
                }
            }
        }
        return INSTANCE;
    }

    // 將ResponseBody 轉換成 String
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return StringConverter.getInstance();
        }
        //其餘不變
        return null;
    }

    private static class StringConverter implements Converter<ResponseBody, String> {

        private static StringConverter INSTANCE;

        //獲取單例
        public static StringConverter getInstance() {
            if (INSTANCE == null) {
                synchronized (StringConverter.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new StringConverter();
                    }
                }
            }
            return INSTANCE;
        }

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }


}
