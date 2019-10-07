package com.ithome11.jetpackmvvmdemo.main.config;

public class AppConfig {

    private final static String TestDomain = "https://newsapi.org/v1/";

    public static final String API_GET_NEWS = "articles?source=google-news";


    public static String getDomain() {
        return TestDomain;
    }
}

