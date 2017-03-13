package com.example.admin.eam.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.admin.eam.R;
import com.example.admin.eam.config.Constants;
import com.example.admin.eam.ui.activity.TriprePort_listActivity;
import com.example.admin.eam.ui.activity.Udcardrivelog_Listactivity;
import com.example.admin.eam.ui.activity.Udcarfuelcharge_Listactivity;
import com.example.admin.eam.ui.activity.Udcarmainlog_Listactivity;
import com.example.admin.eam.ui.activity.Udinspo_ListActivity;
import com.example.admin.eam.ui.activity.Udreport_ListActivity;
import com.example.admin.eam.ui.activity.Udrunlogr_ListActivity;
import com.example.admin.eam.ui.activity.UdstockActivity;
import com.example.admin.eam.ui.activity.stockQuery.StockQuery_listActivity;
import com.example.admin.eam.ui.activity.udpro.Udfeedback_listactivity;
import com.example.admin.eam.ui.activity.udpro.Udpro_ListActivity;
import com.example.admin.eam.ui.activity.udpro.Udprorunlog_listactivity;
import com.example.admin.eam.ui.activity.workorder.DebugWork_ListActivity;
import com.example.admin.eam.ui.activity.workorder.Work_ListActivity;

/**
 * Created by chris on 2017/3/2.
 */

public class FuntionFragment extends android.support.v4.app.Fragment {
    LinearLayout gzgd,tsgd,xjgd,djgd,pcgd,jggd,zysgd,xmtj,xmrb,wtlld,ccbg,yxjl,gztbd,xsjl,jyjl,clwx,kcpd,kccx,tb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fargment_function,container,false);

        gzgd=(LinearLayout)v.findViewById(R.id.id_gd_gz);
        tsgd=(LinearLayout)v.findViewById(R.id.id_gd_ts);
        xjgd=(LinearLayout)v.findViewById(R.id.id_gd_xj);
        djgd=(LinearLayout)v.findViewById(R.id.id_gd_dj);
        pcgd=(LinearLayout)v.findViewById(R.id.id_gd_pc);
        jggd=(LinearLayout)v.findViewById(R.id.id_gd_jg);
        zysgd=(LinearLayout)v.findViewById(R.id.id_gd_zys);

        xmtj=(LinearLayout)v.findViewById(R.id.id_xm_tj);
        xmrb=(LinearLayout)v.findViewById(R.id.id_xm_rb);
        wtlld=(LinearLayout)v.findViewById(R.id.id_xm_wt);
        ccbg=(LinearLayout)v.findViewById(R.id.id_xm_cc);

        yxjl=(LinearLayout)v.findViewById(R.id.id_yw_yxjl);
        gztbd=(LinearLayout)v.findViewById(R.id.id_yw_gztb);

        xsjl=(LinearLayout)v.findViewById(R.id.id_zy_xs);
        jyjl=(LinearLayout)v.findViewById(R.id.id_zy_jy);
        clwx=(LinearLayout)v.findViewById(R.id.id_zy_wx);
        kcpd=(LinearLayout)v.findViewById(R.id.id_zy_kcpd);
        kccx=(LinearLayout)v.findViewById(R.id.id_zy_kccx);
        tb=(LinearLayout)v.findViewById(R.id.id_zy_tb);

        initView();
        return v;
    }
    private void initView()
    {
        gzgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToWordOrder(Constants.FR);
            }
        });
        tsgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToWordOrder(Constants.DC);
            }
        });
        xjgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Udinspo_ListActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        djgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToWordOrder(Constants.WS);
            }
        });
        pcgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToWordOrder(Constants.SP);
            }
        });
        jggd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToWordOrder(Constants.TP);
            }
        });
        zysgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToWordOrder(Constants.AA);
            }
        });
        xmtj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Udpro_ListActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        xmrb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Udprorunlog_listactivity.class);
                startActivityForResult(intent, 0);
            }
        });
        wtlld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Udfeedback_listactivity.class);
                startActivityForResult(intent, 0);
            }
        });
        ccbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TriprePort_listActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        yxjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Udrunlogr_ListActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        gztbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Udreport_ListActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        xsjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Udcardrivelog_Listactivity.class);
                startActivityForResult(intent, 0);
            }
        });
        jyjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Udcarfuelcharge_Listactivity.class);
                startActivityForResult(intent, 0);
            }
        });
        clwx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Udcarmainlog_Listactivity.class);
                startActivityForResult(intent, 0);
            }
        });
        kcpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("这里","1");
                Intent intent = new Intent(getActivity(), UdstockActivity.class);
                startActivityForResult(intent, 0);
                Log.e("这里","2");
            }
        });
        kccx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StockQuery_listActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void jumpToWordOrder(String type)
    {
        if (!type.equals(Constants.DC)) {

            Intent intent = new Intent(getActivity(), Work_ListActivity.class);
            intent.putExtra("worktype", type);
            startActivity(intent);

        }else {

            Intent intent = new Intent(getActivity(), DebugWork_ListActivity.class);
            intent.putExtra("worktype", type);
            startActivity(intent);
        }
    }
}
