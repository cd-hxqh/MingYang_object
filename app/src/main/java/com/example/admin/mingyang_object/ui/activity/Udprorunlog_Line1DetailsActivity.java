package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Udprorunlog;
import com.example.admin.mingyang_object.model.UdprorunlogLine1;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.model.Wpmaterial;


/**
 * Created by think on 2016/7/4.
 * 土建阶段报告详情页面
 */
public class Udprorunlog_Line1DetailsActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private UdprorunlogLine1 udprorunlogLine1 = new UdprorunlogLine1();
    private Udprorunlog udprorunlog;
    private int position;

    private TextView createdate;//
    private TextView personid;//
    private TextView funnum;//
    private TextView prophase;//
    private EditText land;//
    private EditText insideroad;//
    private EditText outsideroad;//
    private CheckBox villagerinvolved;//
    private EditText remark;//
    private TextView keypoint;//
    private TextView basestart;//
    private TextView baseplacing;//
    private TextView baseaog;//
    private TextView tameraog;//
    private TextView vehiclerecords;//
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button delete;//删除


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udprorunlog_line1_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        udprorunlogLine1 = (UdprorunlogLine1) getIntent().getSerializableExtra("udprorunlogline1");
        udprorunlog = (Udprorunlog) getIntent().getSerializableExtra("udprorunlog");
        position = getIntent().getIntExtra("position",0);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        createdate = (TextView) findViewById(R.id.udprorunlogline1_createdate);
        personid = (TextView) findViewById(R.id.udprorunlogline1_personid);
        funnum = (TextView) findViewById(R.id.udprorunlogline1_funnum);
        prophase = (TextView) findViewById(R.id.udprorunlogline1_prophase);
        land = (EditText) findViewById(R.id.udprorunlogline1_land);
        insideroad = (EditText) findViewById(R.id.udprorunlogline1_insideroad);
        outsideroad = (EditText) findViewById(R.id.udprorunlogline1_outsideroad);
        villagerinvolved = (CheckBox) findViewById(R.id.udprorunlogline1_villagerinvolved);
        remark = (EditText) findViewById(R.id.udprorunlogline1_remark);
        keypoint = (TextView) findViewById(R.id.udprorunlogline1_keypoint);
        basestart = (TextView) findViewById(R.id.udprorunlogline1_basestart);
        baseplacing = (TextView) findViewById(R.id.udprorunlogline1_baseplacing);
        baseaog = (TextView) findViewById(R.id.udprorunlogline1_baseaog);
        tameraog = (TextView) findViewById(R.id.udprorunlogline1_tameraog);
        vehiclerecords = (TextView) findViewById(R.id.udprorunlogline1_vehiclerecords);
//        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirm = (Button) findViewById(R.id.work_save);
        delete = (Button) findViewById(R.id.work_cancel);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText(getResources().getString(R.string.title_activity_wpmaterialdetails));
        delete.setText(R.string.work_delete);

        createdate.setText(udprorunlogLine1.CREATEDATE);
        personid.setText(udprorunlogLine1.PERSONID);
        funnum.setText(udprorunlogLine1.FUNNUM);
        prophase.setText(udprorunlogLine1.PROPHASE);
        land.setText(udprorunlogLine1.LAND);
        insideroad.setText(udprorunlogLine1.INSIDEROAD);
        outsideroad.setText(udprorunlogLine1.OUTSIDEROAD);
        villagerinvolved.setChecked(udprorunlogLine1.VILLAGERINVOLVED!=0);
        remark.setText(udprorunlogLine1.REMARK);
        keypoint.setText(udprorunlogLine1.KEYPOINT);
        basestart.setText(udprorunlogLine1.BASESTART);
        baseplacing.setText(udprorunlogLine1.BASEPLACING);
        baseaog.setText(udprorunlogLine1.BASEAOG);
        tameraog.setText(udprorunlogLine1.TAMERAOG);
        vehiclerecords.setText(udprorunlogLine1.VEHICLERECORDS);

        confirm.setOnClickListener(confirmOnClickListener);
        delete.setOnClickListener(deleteOnClickListener);
    }

    private UdprorunlogLine1 getUdprorunlogLine1() {
        UdprorunlogLine1 wpmaterial = this.udprorunlogLine1;

        return wpmaterial;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            Intent intent = getIntent();
//            if(wpmaterial.ITEMNUM.equals(itemnum.getText().toString())
//                    &&wpmaterial.ITEMDESC.equals(itemdesc.getText().toString())
//                    &&wpmaterial.ITEMQTY.equals(itemqty.getText().toString())
//                    &&wpmaterial.ORDERUNIT.equals(orderunit.getText().toString())
//                    &&wpmaterial.LOCATION.equals(location.getText().toString())
//                    &&wpmaterial.LOCDESC.equals(locdesc.getText().toString())) {//如果内容没有修改
//                intent.putExtra("wpmaterial",wpmaterial);
//            }else {
//                Wpmaterial wpmaterial = getWpmaterial();
//                if(wpmaterial.TYPE==null||!wpmaterial.TYPE.equals("add")) {
//                    wpmaterial.TYPE = "update";
//                }
//                intent.putExtra("wpmaterial", wpmaterial);
//                Toast.makeText(Udprorunlog_Line1DetailsActivity.this, "物料本地修改成功", Toast.LENGTH_SHORT).show();
//            }
//            intent.putExtra("position", position);
//            Udprorunlog_Line1DetailsActivity.this.setResult(2, intent);
//            finish();
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("position", position);
            if (!udprorunlogLine1.isUpload){
                Udprorunlog_Line1DetailsActivity.this.setResult(3, intent);
            }else {
                UdprorunlogLine1 wpmaterial = getUdprorunlogLine1();
                wpmaterial.TYPE = "delete";
                intent.putExtra("wpmaterial", wpmaterial);
                Udprorunlog_Line1DetailsActivity.this.setResult(4, intent);
            }
            finish();
        }
    };

    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;
        int optiontype;

        private LayoutOnClickListener(int requestCode, int optiontype) {
            this.requestCode = requestCode;
            this.optiontype = optiontype;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Udprorunlog_Line1DetailsActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        switch (requestCode) {
            case 1:
                option = (Option) data.getSerializableExtra("option");

                break;
            case 2:
                option = (Option) data.getSerializableExtra("option");

                break;
        }
    }
}
