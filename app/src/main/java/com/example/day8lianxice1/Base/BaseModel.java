package com.example.day8lianxice1.Base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {

    CompositeDisposable mCompositeDisposable;

    public void destory(){
        if (mCompositeDisposable != null & mCompositeDisposable.size() > 0){
            mCompositeDisposable.dispose();
        }
    }

    public void addDisposable(Disposable d){
        if (mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }

    public void removeDisposable(Disposable disposable){
        if (mCompositeDisposable != null){
            mCompositeDisposable.remove(disposable);
        }
    }

}
