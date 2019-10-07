package com.ithome11.jetpackmvvmdemo.net;

import android.util.Log;

import com.ithome11.jetpackmvvmdemo.main.net.NewsApi;
import com.ithome11.jetpackmvvmdemo.main.net.exception.ApiException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static com.ithome11.jetpackmvvmdemo.BuildConfig.NEWS_API_KEY;
import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 23, application = FakeApplication.class)
public class callApiTest {

    private static final String TAG = "connectApiTest";


    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        initRxJava2();
    }

    private void initRxJava2() {
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.reset();
        RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    String ResultMsg = "";
    String errorMsg = "";
    int ResultCode = 0;


    @Test
    public void checkSearchSucessTest() {
        NewsApi.getInstance().callGetNews(NEWS_API_KEY).subscribe(response -> {
            ResultMsg = response.getStatus();
        }, throwable -> {
            ResultCode = ((ApiException) throwable).getCode();
            errorMsg = ((ApiException) throwable).getDisplayMessage();
            Log.d(TAG, "fail: " + errorMsg);
        });
        assertEquals(ResultMsg, "ok");
    }

}
