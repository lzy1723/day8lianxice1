package com.example.day8lianxice1.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.day8lianxice1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {


    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        WebView wb_view = view.findViewById(R.id.wb_view);

        wb_view.loadUrl("https://www.baidu.com/");
        wb_view.setWebViewClient(new WebViewClient());
        wb_view.getSettings();
        return view;
    }

}
