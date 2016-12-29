package com.example.admin.mingyang_object.ui.activity.stockQuery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;

public class ConditionActivity extends AppCompatActivity {

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<String> LOCATIONs;
    private RelativeLayout backlayout;//
    private Button clear;
    private Button search;
    private EditText ITEMNUM;
    private EditText ITEMDESC;
    private EditText LOCATIONDESC;
    private TextView LOCATION;
    private TextView BINNUM;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condition);
        Intent i️ntent = getIntent();
        LOCATIONs = i️ntent.getStringArrayListExtra("LOCATIONs");
        findView();
        initView();

    }

    private void findView()
    {
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        clear = (Button) findViewById(R.id.title_clear);
        search = (Button) findViewById(R.id.title_search);
        ITEMNUM = (EditText) findViewById(R.id.edit_text_itemnum);
        ITEMDESC = (EditText) findViewById(R.id.edit_text_itemdesc);
        LOCATIONDESC = (EditText) findViewById(R.id.edit_text_locationdesc);
        LOCATION = (TextView) findViewById(R.id.edit_text_location);
        BINNUM = (TextView) findViewById(R.id.edit_text_binnum);
        SharedPreferences sp =getSharedPreferences("conditon", MODE_PRIVATE);

        ITEMNUM.setText(sp.getString("ITEMNUM",""));
        ITEMDESC.setText(sp.getString("ITEMDESC",""));
        LOCATIONDESC.setText(sp.getString("LOCATIONDESC",""));
        LOCATION.setText(sp.getString("LOCATION","不限"));
        BINNUM.setText(sp.getString("BINNUM","无限制"));

    }
    private void initView()
    {
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("库存查询","清空条件");
                SharedPreferences sp =getSharedPreferences("conditon", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

                ITEMNUM.setText("");
                ITEMDESC.setText("");
                LOCATIONDESC.setText("");
                LOCATION.setText("不限");
                BINNUM.setText("无限制");
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("库存查询","搜索");
                SharedPreferences sp =getSharedPreferences("conditon", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                if (ITEMNUM.getText().toString().length()>0)
                {
                    editor.putString("ITEMNUM",ITEMNUM.getText().toString());
                }
                else
                {
                    editor.remove("ITEMNUM");
                }
                if (ITEMDESC.getText().toString().length()>0)
                {
                    editor.putString("ITEMDESC",ITEMDESC.getText().toString());
                }
                else
                {
                    editor.remove("ITEMDESC");
                }
                if (LOCATIONDESC.getText().toString().length()>0)
                {
                    editor.putString("LOCATIONDESC",LOCATIONDESC.getText().toString());
                }
                else
                {
                    editor.remove("LOCATIONDESC");
                }
                if (LOCATION.getText().toString().equals("不限"))
                {
                    editor.remove("LOCATION");
                }
                else
                {
                    editor.putString("LOCATION",LOCATION.getText().toString());
                }
                if (BINNUM.getText().toString().equals("无限制"))
                {
                    editor.remove("BINNUM");
                }
                else
                {
                    editor.putString("BINNUM",BINNUM.getText().toString());
                }
                editor.commit();
                Log.e("库存查询","条件"+sp.getAll().toString());
                finish();
            }
        });
        LOCATION.setOnClickListener(new ConditionActivity.NormalListDialogOnClickListener(LOCATION));
        BINNUM.setOnClickListener(new ConditionActivity.NormalListDialogOnClickListener(BINNUM));
    }
    private class NormalListDialogOnClickListener implements View.OnClickListener {
        TextView textView;

        public NormalListDialogOnClickListener(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void onClick(View v) {
            NormalListDialog(textView);
        }
    }
    private void NormalListDialog(final TextView textView) {
        String[] types = new String[0];
        mMenuItems = new ArrayList<>();

        if(textView==BINNUM) {
            types = getResources().getStringArray(R.array.bin);
        }
        if (textView==LOCATION)
        {
            types =getLOTNUMs();
        }
        for (int i = 0; i < types.length; i++) {

            mMenuItems.add(new DialogMenuItem(types[i], 0));
        }

        final NormalListDialog dialog = new NormalListDialog(ConditionActivity.this, mMenuItems);

        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {

            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

                textView.setText(mMenuItems.get(position).mOperName);

                dialog.dismiss();
            }

        });
    }
    private String[] getLOTNUMs()
    {
        if (LOCATIONs!=null)
        {
            if (LOCATIONs.contains("不限"))
            {
                LOCATIONs.remove("不限");
            }
            LOCATIONs.add("不限");
            String[] strs = (String[]) LOCATIONs.toArray(new String[LOCATIONs.size()]);
            return strs;
        }
        else
        {
            return new  String[]{"不限"};
        }
    }
}
