package com.example.admin.mingyang_object.ui.activity.workorder;

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
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.model.Wpmaterial;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.ui.activity.OptionActivity;
import com.example.admin.mingyang_object.utils.DateSelect;
import com.example.admin.mingyang_object.utils.DateTimeSelect;


/**
 * Created by think on 2016/6/30.
 * 物料详情页面
 */
public class WpmaterialDetailsActivity extends BaseActivity {
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
    private Button delete;//删除


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wpmaterial_details);

        geiIntentData();
        findViewById();
        initView();
    }

    private void geiIntentData() {
        wpmaterial = (Wpmaterial) getIntent().getSerializableExtra("wpmaterial");
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
        position = getIntent().getIntExtra("position",0);
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

        itemnum.setText(wpmaterial.ITEMNUM);
        itemdesc.setText(wpmaterial.ITEMDESC);
        itemqty.setText(wpmaterial.ITEMQTY);
        orderunit.setText(wpmaterial.ORDERUNIT);
        location.setText(wpmaterial.LOCATION);
        locdesc.setText(wpmaterial.LOCDESC);

        itemnum.setOnClickListener(new LayoutOnClickListener(1, Constants.ITEMCODE));
        location.setOnClickListener(new LayoutOnClickListener(2,Constants.LOCATIONCODE2));
        confirm.setOnClickListener(confirmOnClickListener);
        delete.setOnClickListener(deleteOnClickListener);
    }

    private Wpmaterial getWpmaterial() {
        Wpmaterial wpmaterial = this.wpmaterial;
        wpmaterial.ITEMNUM = itemnum.getText().toString();
        wpmaterial.ITEMDESC = itemdesc.getText().toString();
        wpmaterial.ITEMQTY = itemqty.getText().toString();
        wpmaterial.ORDERUNIT = orderunit.getText().toString();
        wpmaterial.LOCATION = location.getText().toString();
        wpmaterial.LOCDESC = locdesc.getText().toString();
        return wpmaterial;
    }

    private boolean isOK(){
        if (itemnum.getText().toString().equals("")){
            Toast.makeText(WpmaterialDetailsActivity.this,"物资编码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (itemqty.getText().toString().equals("")){
            Toast.makeText(WpmaterialDetailsActivity.this,"物资数量不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }else if (location.getText().toString().equals("")){
            Toast.makeText(WpmaterialDetailsActivity.this,"库房不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private View.OnClickListener confirmOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isOK()) {
                Intent intent = getIntent();
                if (wpmaterial.ITEMNUM.equals(itemnum.getText().toString())
                        && wpmaterial.ITEMDESC.equals(itemdesc.getText().toString())
                        && wpmaterial.ITEMQTY.equals(itemqty.getText().toString())
                        && wpmaterial.ORDERUNIT.equals(orderunit.getText().toString())
                        && wpmaterial.LOCATION.equals(location.getText().toString())
                        && wpmaterial.LOCDESC.equals(locdesc.getText().toString())) {//如果内容没有修改
                    intent.putExtra("wpmaterial", wpmaterial);
                } else {
                    Wpmaterial wpmaterial = getWpmaterial();
                    if (wpmaterial.TYPE == null || !wpmaterial.TYPE.equals("add")) {
                        wpmaterial.TYPE = "update";
                    }
                    intent.putExtra("wpmaterial", wpmaterial);
                    Toast.makeText(WpmaterialDetailsActivity.this, "物料本地修改成功", Toast.LENGTH_SHORT).show();
                }
                intent.putExtra("position", position);
                WpmaterialDetailsActivity.this.setResult(2, intent);
                finish();
            }
        }
    };

    private View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = getIntent();
            intent.putExtra("position", position);
            if (!wpmaterial.isUpload){
                WpmaterialDetailsActivity.this.setResult(3, intent);
            }else {
                Wpmaterial wpmaterial = getWpmaterial();
                wpmaterial.TYPE = "delete";
                intent.putExtra("wpmaterial", wpmaterial);
                WpmaterialDetailsActivity.this.setResult(4, intent);
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
            Intent intent = new Intent(WpmaterialDetailsActivity.this, OptionActivity.class);
            intent.putExtra("optiontype", optiontype);
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Option option;
        if (data != null) {
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
