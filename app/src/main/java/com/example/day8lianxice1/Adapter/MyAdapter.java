package com.example.day8lianxice1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day8lianxice1.Bean.JavaBean;
import com.example.day8lianxice1.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<JavaBean.DataBean.DatasBean> list;
    private int ITEM_TYPE_ONE = 1;
    private int ITEM_TYPE_SIX = 6;
    private ImageView iv_home1;
    public OnMyClickListener onMyClickListener;

    public void setOnMyClickListener(OnMyClickListener onMyClickListener) {
        this.onMyClickListener = onMyClickListener;
    }

    public MyAdapter(Context context, ArrayList<JavaBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position != 6) {
            return ITEM_TYPE_ONE;
        } else {
            return ITEM_TYPE_SIX;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_ONE) {
            View view1 = LayoutInflater.from(context).inflate(R.layout.layout_home, parent, false);
            return new ViewHolder(view1);
        } else {
            View view2 = LayoutInflater.from(context).inflate(R.layout.layout_home2, parent, false);
            return new ViewHolder2(view2);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == ITEM_TYPE_ONE){
            ViewHolder holder1 = (ViewHolder) holder;
            holder1.tv_home1.setText(list.get(position).getDesc());
            Glide.with(context).load(list.get(position).getEnvelopePic()).into(holder1.iv_home1);
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMyClickListener.OnClick(position);
                }
            });
        }else {
            ViewHolder2 holder2 = (ViewHolder2) holder;
            Glide.with(context).load(list.get(position).getEnvelopePic()).into(holder2.iv_home2);
            holder2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMyClickListener.OnClick(position);
                }
            });
        }
    }

    public interface OnMyClickListener{
        void OnClick(int i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_home1;
        public TextView tv_home1;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_home1 = (ImageView) rootView.findViewById(R.id.iv_home1);
            this.tv_home1 = (TextView) rootView.findViewById(R.id.tv_home1);
        }

    }

    class ViewHolder2 extends ViewHolder {
        public View rootView;
        public ImageView iv_home2;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_home2 = (ImageView) rootView.findViewById(R.id.iv_home2);
        }

    }
}
