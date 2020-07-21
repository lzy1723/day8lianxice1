package com.example.day8lianxice1.Model;

import android.util.Log;

import com.example.day8lianxice1.Base.BaseModel;
import com.example.day8lianxice1.Bean.JavaBean;
import com.example.day8lianxice1.Net.ApiService;
import com.example.day8lianxice1.Net.MainCallBack;
import com.example.day8lianxice1.Presenter.MainPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel extends BaseModel {
    public void getData(MainCallBack mainCallBack, SmartRefreshLayout sml, int page, int cid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Observable<JavaBean> javaBean = retrofit.create(ApiService.class).getJavaBean(page, cid);

        javaBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JavaBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JavaBean javaBean) {
                        mainCallBack.onSuccess(javaBean);
                        sml.finishLoadMore();
                        sml.finishRefresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainCallBack.onFail(e.getMessage());
                        Log.i("TAG",e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
