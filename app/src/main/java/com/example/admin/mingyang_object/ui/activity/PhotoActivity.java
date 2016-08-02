package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.utils.MessageUtils;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.lling.photopicker.PhotoPickerActivity;
import com.lling.photopicker.utils.ImageLoader;
import com.lling.photopicker.utils.OtherUtils;

import org.kobjects.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片选择*
 */
public class PhotoActivity extends BaseActivity {

    private static final String TAG = "PhotoActivity";

    private static final int PICK_PHOTO = 1;

    /**
     * 返回按钮
     */
    private ImageView backImageView;
    /**
     * 标题
     */
    private TextView titleTextView;
    /**
     * text_hint*
     */
    private TextView textView;
    /**
     * 提交按钮*
     */
    private Button submitBtn;

    /**
     * 选择图片*
     */
    private Button chooseImageBtn;
    /**
     * 图片显示*
     */
    private GridView mGrideView;

    private List<String> mResults;

    private GridAdapter mAdapter;

    private int mColumnWidth;

    ArrayList<String> result = new ArrayList<String>();


    /**
     * 表名*
     */
    private String ownertable;
    /**
     * 主键ID*
     */
    private String ownerid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        int screenWidth = OtherUtils.getWidthInPx(getApplicationContext());
        mColumnWidth = (screenWidth - OtherUtils.dip2px(getApplicationContext(), 4)) / 3;
        getInitData();

        findViewById();
        initView();

    }

    private void getInitData() {
        ownertable = getIntent().getExtras().getString("ownertable");
        ownerid = getIntent().getExtras().getString("ownerid");
        Log.i(TAG, "ownertable=" + ownertable + ",ownerid=" + ownerid);
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        textView = (TextView) findViewById(R.id.text_id);
        submitBtn = (Button) findViewById(R.id.submit_btn);
        chooseImageBtn = (Button) findViewById(R.id.picker_btn);
        mGrideView = (GridView) findViewById(R.id.gridview);

    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.work_commit));
        chooseImageBtn.setOnClickListener(chooseImageBtnOnClickListener);
        submitBtn.setVisibility(View.VISIBLE);
        submitBtn.setOnClickListener(submitBtnOnClickListener);
        if (result == null || result.size() == 0) {
            textView.setVisibility(View.VISIBLE);
            mGrideView.setVisibility(View.GONE);

        } else {
            textView.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
        }

    }


    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener chooseImageBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int selectedMode = PhotoPickerActivity.MODE_MULTI;

            boolean showCamera = true;


            int maxNum = PhotoPickerActivity.DEFAULT_NUM;


            Intent intent = new Intent(PhotoActivity.this, PhotoPickerActivity.class);
            intent.putExtra(PhotoPickerActivity.EXTRA_SHOW_CAMERA, showCamera);
            intent.putExtra(PhotoPickerActivity.EXTRA_SELECT_MODE, selectedMode);
            intent.putExtra(PhotoPickerActivity.EXTRA_MAX_MUN, maxNum);
            startActivityForResult(intent, PICK_PHOTO);
        }
    };


    private View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (result == null || result.size() == 0) {
                MessageUtils.showMiddleToast(PhotoActivity.this, "请选择需要上传的图片");
            } else {
                showProgressDialog("提交数据中");
                progressDialog.setCancelable(false);
                for (int i = 0; i < result.size(); i++) {
                    startAsyncTask(result.get(i));
                }
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> result1 = data.getStringArrayListExtra(PhotoPickerActivity.KEY_RESULT);
                for (String r : result1) {
                    result.add(r);
                }

                showResult(result);
            }
        }
    }

    /**
     * 获取文件名称*
     */
    private String getFileName(String fileName) {
        File file = new File(fileName);
        String name = null;
        if (file.exists()) {
            name = file.getName();
        }
        return name;
    }


    private void showResult(ArrayList<String> paths) {
        if (mResults == null) {
            mResults = new ArrayList<String>();
        }
        mResults.clear();
        mResults.addAll(paths);

        if (mAdapter == null) {
            mAdapter = new GridAdapter(mResults);
            mGrideView.setAdapter(mAdapter);
        } else {
            mAdapter.setPathList(mResults);
            mAdapter.notifyDataSetChanged();
        }

        if (mResults == null || mResults.size() == 0) {
            textView.setVisibility(View.VISIBLE);
            mGrideView.setVisibility(View.GONE);

        } else {
            textView.setVisibility(View.GONE);
            mGrideView.setVisibility(View.VISIBLE);
        }
    }

    private class GridAdapter extends BaseAdapter {
        private List<String> pathList;

        public GridAdapter(List<String> listUrls) {
            this.pathList = listUrls;
        }

        @Override
        public int getCount() {
            return pathList.size();
        }

        @Override
        public String getItem(int position) {
            return pathList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void setPathList(List<String> pathList) {
            this.pathList = pathList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_image, null);
                imageView = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(imageView);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mColumnWidth, mColumnWidth);
                imageView.setLayoutParams(params);
            } else {
                imageView = (ImageView) convertView.getTag();
            }
            ImageLoader.getInstance(getApplicationContext()).display(getItem(position), imageView, mColumnWidth, mColumnWidth);
            return convertView;
        }
    }


    /**
     * 测试图片上传*
     */
    private String getuploadBuffer(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = fis.read(buffer)) >= 0) {
                baos.write(buffer, 0, count);
            }
            String uploadBuffer = new String(Base64.encode(baos.toByteArray()));  //进行Base64编码
            fis.close();
            return uploadBuffer;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


    /**
     * 提交数据*
     */
    private void startAsyncTask(String fileName) {
        String updataInfo = null;
        String name = null;
        updataInfo = getuploadBuffer(fileName);
        name = getFileName(fileName);

        Log.i(TAG, "updataInfo=" + updataInfo);
        final String finalUpdataInfo = updataInfo;
        final String finalname = name;
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String reviseresult = AndroidClientService.connectWebService(PhotoActivity.this,
                        finalname, finalUpdataInfo, ownertable, ownerid, Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(String workResult) {
                super.onPostExecute(workResult);
                if (workResult == null) {
                    MessageUtils.showMiddleToast(PhotoActivity.this, "图片上传失败");
                } else {
                    MessageUtils.showMiddleToast(PhotoActivity.this, "图片上传成功");
                    finish();
                }
                closeProgressDialog();
            }
        }.execute();

    }
}