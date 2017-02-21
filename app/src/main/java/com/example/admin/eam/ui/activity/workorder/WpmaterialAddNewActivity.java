package com.example.admin.eam.ui.activity.workorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.eam.R;
import com.example.admin.eam.config.Constants;
import com.example.admin.eam.model.Option;
import com.example.admin.eam.model.WorkOrder;
import com.example.admin.eam.model.Wpmaterial;
import com.example.admin.eam.ui.activity.BaseActivity;
import com.example.admin.eam.ui.activity.OptionActivity;


/**
 * Created by think on 2016/7/1.
 * 故障工单物料新增页面
 */
public class WpmaterialAddNewActivity extends BaseActivity {
    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;

    private Wpmaterial wpmaterial = new Wpmaterial();
    private WorkOrder workOrder;
    private int position;

    private TextView itemnum;//物资编码
    private TextView itemdesc;//描述
    private EditText itemqty;//数量
    private TextView orderunit;//订购单位
    private TextView location;//库房
    private TextView locdesc;//库房描述
    private LinearLayout buttonlayout;
    private Button confirm;//确定
    private Button cancel;//取消


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpmaterial_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
//        woactivity = (Woactivity) getIntent().getSerializableExtra("woactivity");
//        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
//        position = getIntent().getIntExtra("position",0);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);

        itemnum = (TextView) findViewById(R.id.wpmaterial_itemnum);
        itemdesc = (TextView) findViewById(R.id.wpmaterial_itemdesc);
        itemqty = (EditText) findViewById(R.id.wpmaterial_itemqty);
        orderunit = (TextView) findViewById(R.id.wpmaterial_orderunit);
        location = (TextView) findViewById(R.id.wpmaterial_location);
        locdesc = (TextView) findViewById(R.id.wpmaterial_locdesc);

//        buttonlayout = (LinearLayout) findViewById(R.id.button_layout);
        confirm = (Button) findViewById(R.id.work_save);
        cancel = (Button) findViewById(R.id.work_cancel);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleTextView.setText("新增" + getResources().getString(R.string.title_activity_wpmaterialdetails));

        itemnum.setOnClickListener(new LayoutOnClickListener(1, Constants.ITEMCODE));
        location.setOnClickListener(new LayoutOnClickListener(2,Constants.LOCATIONCODE2));
        confirm.setOnClickListener(confirmOnClickListener);
        cancel.setOnClickListener(cancelOnClickListener);
    }

    private Wpmaterial getWpmaterial() {
        Wpmaterial wpmaterial = this.wpmaterial;
        wpmaterial.ITEMNUM = itemnum.getText().toString();
        wpmaterial.ITEMDESC = itemdesc.getText().toString();
        wpmaterial.ITEMQTY = itemqty.getText().toString();
        wpmaterial.ORDERUNIT = orderunit.getText().toString();//不上传，由ITEMNUM决定
        wpmaterial.LOCATION = location.getText().toString();
        wpmaterial.LOCDESC = locdesc.getText().toString();
        wpmaterial.TYPE = "add";
        return wpmaterial;
    }

    private boolean isOK(){
        if (itemnum.getText().toString().equals("")){
            Toast.makeText(WpmaterialAddNewActivity.this,"物资编码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (itemqty.getText().toString().equals("")){
            Toast.makeText(WpmaterialAddNewActivity.this,"物资数量不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (location.getText().toString().equals("")){
            Toast.makeText(WpmaterialAddNewActivity.this,"库房不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isOK()) {
                Intent intent = getIntent();
                intent.putExtra("wpmaterial", getWpmaterial());
                WpmaterialAddNewActivity.this.setResult(1, intent);
                Toast.makeText(WpmaterialAddNewActivity.this, "物料本地新增成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };

    private View.OnClickListener cancelOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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
            Intent intent = new Intent(WpmaterialAddNewActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data!=null) {
            switch (requestCode) {
                case 1:
                    option = (Option) data.getSerializableExtra("option");
                    itemnum.setText(option.getName());
                    itemdesc.setText(option.getDesc());
                    orderunit.setText(option.getValue1());
                    break;
                case 2:
                    option = (Option) data.getSerializableExtra("option");
                    location.setText(option.getName());
                    locdesc.setText(option.getDesc());
                    break;
            }
        }
    }
}
