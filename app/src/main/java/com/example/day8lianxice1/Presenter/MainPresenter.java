package com.example.day8lianxice1.Presenter;

import com.example.day8lianxice1.Base.BaseModel;
import com.example.day8lianxice1.Base.BasePresenter;
import com.example.day8lianxice1.Bean.JavaBean;
import com.example.day8lianxice1.Model.MainModel;
import com.example.day8lianxice1.Net.MainCallBack;
import com.example.day8lianxice1.View.MainView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MainPresenter extends BasePresenter<MainView> implements MainCallBack {

    private MainModel mMainModel;

    @Override
    protected void initModel() {
        mMainModel = new MainModel();
        addModel(mMainModel);
    }

    @Override
    public void onSuccess(JavaBean javaBean) {
        mView.setData(javaBean);
    }

    @Override
    public void onFail(String string) {
        mView.showToast(string);
    }

    public void getData(SmartRefreshLayout sml, int page, int cid) {
        mMainModel.getData(this,sml,page,cid);
    }
}
