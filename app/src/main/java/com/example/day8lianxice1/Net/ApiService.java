package com.example.day8lianxice1.Net;

import com.example.day8lianxice1.Bean.JavaBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL = "https://www.wanandroid.com/";

    @GET("project/list/{page}/json")
    Observable<JavaBean> getJavaBean(@Path("page") int page, @Query("cid") int cid);

}
