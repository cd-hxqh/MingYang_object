package com.example.admin.eam.ui.activity.udpro;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.model.Udfandetails;
import com.example.admin.eam.ui.activity.BaseActivity;

/**
 * Created by think on 2016/7/7.
 * 风机型号详情
 */
public class Udfandetails_DetailActivity extends BaseActivity {
    private Udfandetails udfandetails;
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private TextView locnum;//机位号
    private TextView modeltype;//型号
    private TextView status;//风机状态
    private TextView empst;//机台号
    private TextView sapnum;//成品物料代码(SAP)
    private TextView sapdesc;//成品物料描述
    private TextView tjdate;//土建完成日期
    private TextView dzdate;//吊装完成日期
    private TextView tsdate;//调试完成日期
    private TextView djdate1;//首检完成日期
    private TextView jddate3;//全年检完成日期
    private TextView djdate2;//半年检完成日期
    private TextView bwdate;//并网运行日期
    private TextView djdate4;//下次定检日期
//    private TextView djdate5;//下次全年检日期
    private TextView xjdate;//上次巡检日期
    private TextView xjdate2;//下次巡检日期
    private TextView zyydate;//终验收完成日期
    private TextView yysdate;//预验收完成日期

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udfandetails_details);

        getIntentData();
        findViewById();
        initView();
    }

    private void getIntentData() {
        udfandetails = (Udfandetails) getIntent().getSerializableExtra("udfandetails");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        locnum = (TextView) findViewById(R.id.udfandetails_locnum);
        modeltype = (TextView) findViewById(R.id.udfandetails_modeltype);
        status = (TextView) findViewById(R.id.udfandetails_status);
        empst = (TextView) findViewById(R.id.udfandetails_empst);
        sapnum = (TextView) findViewById(R.id.udfandetails_sapnum);
        sapdesc = (TextView) findViewById(R.id.udfandetails_sapdesc);
        tjdate = (TextView) findViewById(R.id.udfandetails_tjdate);
        dzdate = (TextView) findViewById(R.id.udfandetails_dzdate);
        tsdate = (TextView) findViewById(R.id.udfandetails_tsdate);
        djdate1 = (TextView) findViewById(R.id.udfandetails_djdate1);
        jddate3 = (TextView) findViewById(R.id.udfandetails_jddate3);
        djdate2 = (TextView) findViewById(R.id.udfandetails_djdate2);
        bwdate = (TextView) findViewById(R.id.udfandetails_bwdate);
        djdate4 = (TextView) findViewById(R.id.udfandetails_djdate4);
//        djdate5 = (TextView) findViewById(R.id.udfandetails_djdate5);
        xjdate = (TextView) findViewById(R.id.udfandetails_xjdate);
        xjdate2 = (TextView) findViewById(R.id.udfandetails_xjdate2);
        zyydate = (TextView) findViewById(R.id.udfandetails_zyydate);
        yysdate = (TextView) findViewById(R.id.udfandetails_yysdate);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText(getResources().getString(R.string.udfandetails_details_title));

        locnum.setText(udfandetails.LOCNUM);
        modeltype.setText(udfandetails.MODELTYPE);
        status.setText(udfandetails.STATUS);
        empst.setText(udfandetails.EMPST);
        sapnum.setText(udfandetails.SAPNUM);
        sapdesc.setText(udfandetails.SAPDESC);
        tjdate.setText(udfandetails.TJDATE);
        dzdate.setText(udfandetails.DZDATE);
        tsdate.setText(udfandetails.TSDATE);
        djdate1.setText(udfandetails.DJDATE1);
        jddate3.setText(udfandetails.JDDATE3);
        djdate2.setText(udfandetails.DJDATE2);
        bwdate.setText(udfandetails.BWDATE);
        djdate4.setText(udfandetails.DJDATE4);
//        djdate5.setText(udfandetails.DJDATE5);
        xjdate.setText(udfandetails.XJDATE);
        xjdate2.setText(udfandetails.XJDATE2);
        zyydate.setText(udfandetails.ZYYDATE);
        yysdate.setText(udfandetails.YYSDATE);
    }
}
