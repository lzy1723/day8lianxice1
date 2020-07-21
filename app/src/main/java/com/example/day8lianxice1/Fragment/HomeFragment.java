package com.example.day8lianxice1.Fragment;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day8lianxice1.Adapter.MyAdapter;
import com.example.day8lianxice1.Base.BaseFragment;
import com.example.day8lianxice1.Bean.JavaBean;
import com.example.day8lianxice1.Main2Activity;
import com.example.day8lianxice1.MainActivity;
import com.example.day8lianxice1.MyReceiver;
import com.example.day8lianxice1.Presenter.MainPresenter;
import com.example.day8lianxice1.R;
import com.example.day8lianxice1.View.MainView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<MainPresenter> implements MainView {


    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    private ArrayList<JavaBean.DataBean.DatasBean> list;
    private MyAdapter myAdapter;
    private int page = 1;
    private int cid = 294;
    private LocalBroadcastManager lbm;
    private MyReceiver myReceiver;
    private String desc;


    @Override
    protected void initListener() {
        sml.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                list.clear();
                initData();
            }
        });

        myAdapter.setOnMyClickListener(new MyAdapter.OnMyClickListener() {
            @Override
            public void OnClick(int i) {
                Intent intent = new Intent("Broadcast");
                desc = list.get(i).getDesc();
                intent.putExtra("desc", desc);
                lbm.sendBroadcast(intent);

                initNotification();
            }
        });
    }

    private void initNotification() {

        NotificationManager systemService = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "1";
        String channelName = "default";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            systemService.createNotificationChannel(channel);
        }

        Intent intent = new Intent(getActivity(), Main2Activity.class);
        PendingIntent activity = PendingIntent.getActivity(getActivity(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification build = new NotificationCompat.Builder(getActivity(), channelId)
                .setSmallIcon(R.mipmap.ic_launcher)//设置小图
                .setContentText(desc)//设置内容
                .setContentTitle("我是标题")//设置标题
                .setContentIntent(activity)//设置延时意图
                .setDefaults(Notification.DEFAULT_ALL)//设置提示效果
                .setAutoCancel(true)//设置点击自动消失
                .setNumber(3)//设置角标计数
                .build();

        systemService.notify(100,build);

    }

    @Override
    protected void initData() {
        mPresenter.getData(sml,page,cid);
    }

    @Override
    protected void initView() {
        lbm = LocalBroadcastManager.getInstance(getActivity());

        list = new ArrayList<>();

        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        myAdapter = new MyAdapter(getActivity(), list);
        rlv.setAdapter(myAdapter);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setData(JavaBean javaBean) {
        list.addAll(javaBean.getData().getDatas());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        myReceiver = new MyReceiver();
        IntentFilter broadcast = new IntentFilter("Broadcast");
        lbm.registerReceiver(myReceiver,broadcast);
    }

    @Override
    public void onPause() {
        super.onPause();
        lbm.unregisterReceiver(myReceiver);
    }
}
