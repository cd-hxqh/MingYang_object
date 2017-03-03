package com.example.admin.eam.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.admin.eam.R;
import com.example.admin.eam.ui.activity.MainActivity;

/**
 * Created by chris on 2017/3/2.
 */

public class TabbarFragment extends android.support.v4.app.Fragment {
    AppCompatImageView function;
    AppCompatImageView process;
    AppCompatImageView myself;

    RelativeLayout  functionRelativeLayout;
    RelativeLayout  processRelativeLayout;
    RelativeLayout  myselfRelativeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabbar,container,false);

        function =(AppCompatImageView)v.findViewById(R.id.appCompatImageButton0);
        process =(AppCompatImageView)v.findViewById(R.id.appCompatImageButton1);
        myself =(AppCompatImageView)v.findViewById(R.id.appCompatImageButton2);

        functionRelativeLayout = (RelativeLayout)v.findViewById(R.id.appCompatRelativeLayout0);
        processRelativeLayout = (RelativeLayout)v.findViewById(R.id.appCompatRelativeLayout1);
        myselfRelativeLayout = (RelativeLayout)v.findViewById(R.id.appCompatRelativeLayout2);

        initView();
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void initView()
    {
        final MainActivity ma = (MainActivity)getActivity();

        functionRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function.setImageResource(R.mipmap.ic_function_down);
                process.setImageResource(R.mipmap.ic_float);
                myself.setImageResource(R.mipmap.ic_me);
                ma.showFragment(1);
            }
        });
        processRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function.setImageResource(R.mipmap.ic_function);
                process.setImageResource(R.mipmap.ic_float_down);
                myself.setImageResource(R.mipmap.ic_me);
                ma.showFragment(2);
            }
        });
        myselfRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function.setImageResource(R.mipmap.ic_function);
                process.setImageResource(R.mipmap.ic_float);
                myself.setImageResource(R.mipmap.ic_me_down);
                ma.showFragment(3);
            }
        });
        functionRelativeLayout.performClick();
    }

}
