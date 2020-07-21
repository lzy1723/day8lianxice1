package com.example.day8lianxice1.Base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.day8lianxice1.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment <P extends BasePresenter> extends Fragment implements BaseView {

    public P mPresenter;
    private Unbinder unbinder;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(initLayout(), container, false);
        unbinder = ButterKnife.bind(this,view);
        initPresenter();
        if (mPresenter != null){
            mPresenter.bindView(this);
        }
        initView();
        initData();
        initListener();
        return view;
    }

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract int initLayout();

    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mPresenter.destroy();
        mPresenter = null;
    }

}
