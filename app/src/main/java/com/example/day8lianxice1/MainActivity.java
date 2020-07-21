package com.example.day8lianxice1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day8lianxice1.Adapter.TabAdapter;
import com.example.day8lianxice1.Fragment.HomeFragment;
import com.example.day8lianxice1.Fragment.MyFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tv_toolbar;
    private Toolbar toolbar;
    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTab();
    }

    private void initTab() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new MyFragment());

        strings = new ArrayList<>();
        strings.add("首页");
        strings.add("我的");

        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments, strings);
        vp.setAdapter(tabAdapter);
        tab.setupWithViewPager(vp);

        for (int i = 0; i < strings.size(); i++) {
            setTab(i);
        }
    }

    private void setTab(int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_item, null, false);
        ImageView iv_tab = view.findViewById(R.id.iv_tab);
        TextView tv_tab = view.findViewById(R.id.tv_tab);

        String s = strings.get(position);
        tv_tab.setText(s);

        switch (position){
            case 0:
                iv_tab.setImageResource(R.drawable.selector1);;
                break;
            case 1:
                iv_tab.setImageResource(R.drawable.selector2);
                break;
        }
        TabLayout.Tab tabAt = tab.getTabAt(position);
        tabAt.setCustomView(view);
    }

    private void initView() {
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

    }
}
