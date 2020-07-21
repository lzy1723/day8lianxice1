package com.example.day8lianxice1.Base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {

    public V mView;

    public ArrayList<BaseModel> mModel = new ArrayList<>();

    public BasePresenter(){
        initModel();
    }

    public void addModel(BaseModel model){
        mModel.add(model);
    }

    protected abstract void initModel();

    public void bindView(V view){
        mView = view;
    }

    public void destroy(){
        mView = null;
        if (mModel != null && mModel.size()>0){
            for (int i = 0; i < mModel.size(); i++) {
                mModel.get(i).destory();
            }
            mModel.clear();
            mModel = null;
        }
    }
}
