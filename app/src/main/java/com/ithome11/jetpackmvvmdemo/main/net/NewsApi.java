package com.ithome11.jetpackmvvmdemo.main.net;


import com.ithome11.jetpackmvvmdemo.main.config.AppConfig;
import com.ithome11.jetpackmvvmdemo.main.net.converter.StringConverterFactory;
import com.ithome11.jetpackmvvmdemo.main.net.obj.BaseResponseObj;
import com.ithome11.jetpackmvvmdemo.main.net.response.ResponseTransformer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewsApi {

    private static final int DEFAULT_TIMEOUT = 60;

    private Retrofit retrofit;
    private NewstApiForm mNewstApiForm;

    private static NewsApi INSTANCE;

    //獲取單例
    public static NewsApi getInstance() {
        if (INSTANCE == null) {
            synchronized (NewsApi.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NewsApi();
                }
            }
        }
        return INSTANCE;
    }


    private NewsApi() {
        //設置okhttp且設定timeout
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).addInterceptor(new ResponseInterceptor());

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(StringConverterFactory.getInstance())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(AppConfig.getDomain())
                .build();

        mNewstApiForm = retrofit.create(NewstApiForm.class);
    }

    private static class ResponseInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());

            if (response.code() == 401 || response.code() == 403) {
                return response.newBuilder().code(200).build();
            } else return response;

        }
    }


    public Observable<BaseResponseObj> callGetNews(String apiKey) {
        return mNewstApiForm.forGetNews(apiKey)
                .compose(ResponseTransformer.handleResult(BaseResponseObj.class))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
