package com.example.admin.mingyang_object.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.ui.activity.workorder.Work_ListActivity;


/**
 * 工单的fragment
 */
public class WorkFragment extends Fragment {

    private LinearLayout fr_layout,aa_layout,dc_layout,sp_layout,
            tp_layout,ws_layout;

    public WorkFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container,
                false);

        findByIdView(view);
        setlistener();
        return view;
    }
    /**
     * 初始化界面组件*
     */
    private void findByIdView(View view) {
        fr_layout = (LinearLayout) view.findViewById(R.id.work_fr_id);
        aa_layout = (LinearLayout) view.findViewById(R.id.work_aa_id);
        dc_layout = (LinearLayout) view.findViewById(R.id.work_dc_id);
        sp_layout = (LinearLayout) view.findViewById(R.id.work_sp_id);
        tp_layout = (LinearLayout) view.findViewById(R.id.work_tp_id);
        ws_layout = (LinearLayout) view.findViewById(R.id.work_ws_id);
    }

    /**
     * 设置跳转监听
     */
    private void setlistener(){
        fr_layout.setOnClickListener(new intentOnclicklistener(Constants.FR));
        aa_layout.setOnClickListener(new intentOnclicklistener(Constants.AA));
        dc_layout.setOnClickListener(new intentOnclicklistener(Constants.DC));
        sp_layout.setOnClickListener(new intentOnclicklistener(Constants.SP));
        tp_layout.setOnClickListener(new intentOnclicklistener(Constants.TP));
        ws_layout.setOnClickListener(new intentOnclicklistener(Constants.WS));
    }

    private class intentOnclicklistener implements View.OnClickListener{
        private String type;
        private intentOnclicklistener(String type){
            this.type = type;
        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), Work_ListActivity.class);
            intent.putExtra("worktype",type);
            startActivity(intent);
        }
    }
}
