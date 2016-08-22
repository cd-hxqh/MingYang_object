package com.example.admin.mingyang_object.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.ui.adapter.ImagePickerAdapter;
import com.example.admin.mingyang_object.ui.widget.GlideImageLoader;
import com.example.admin.mingyang_object.utils.MessageUtils;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

import org.kobjects.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;


public class WxDemoActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {

    private static final String TAG = "WxDemoActivity";
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList = new ArrayList<>();
    ; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数

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


    private int iscamera;


    ArrayList<ImageItem> imageItems;

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
        getInitData();
        findViewById();
        initView();
        initWidget();
        initImagePicker();
    }

    private void getInitData() {
        iscamera = getIntent().getExtras().getInt("isCamera");
        if (iscamera == 1) {
            ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
            Intent intent = new Intent(this, ImageGridActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        }

        ownertable = getIntent().getExtras().getString("ownertable");
        ownerid = getIntent().getExtras().getString("ownerid");
    }

    @Override
    protected void findViewById() {
        backImageView = (ImageView) findViewById(R.id.title_back_id);
        titleTextView = (TextView) findViewById(R.id.title_name);
        textView = (TextView) findViewById(R.id.text_id);
        submitBtn = (Button) findViewById(R.id.submit_btn);
        chooseImageBtn = (Button) findViewById(R.id.picker_btn);
    }

    @Override
    protected void initView() {
        backImageView.setOnClickListener(backImageViewOnClickListener);
        titleTextView.setText(getString(R.string.work_commit));
        chooseImageBtn.setOnClickListener(chooseImageBtnOnClickListener);
        submitBtn.setVisibility(View.VISIBLE);
        submitBtn.setOnClickListener(submitBtnOnClickListener);

    }

    private View.OnClickListener backImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
//            case IMAGE_ITEM_ADD:
//                //打开选择,本次允许选择的数量
//                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
//                Intent intent = new Intent(this, ImageGridActivity.class);
//                startActivityForResult(intent, REQUEST_CODE_SELECT);
//                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "resultCode=" + resultCode + "requestCode=" + requestCode);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                selImageList.addAll(imageItems);
                adapter.setImages(selImageList);
                if (adapter.getItemCount() != 0) {
                    textView.setVisibility(View.GONE);
                } else {
                    textView.setVisibility(View.VISIBLE);
                }

            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
               ArrayList<ImageItem> imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);

                selImageList.clear();
                selImageList.addAll(imageItems);
                adapter.setImages(selImageList);
                adapter.notifyDataSetChanged();
                Log.i(TAG, "size=" + adapter.getItemCount());
            }
        }
    }


    /**
     * 选择照片的事件
     **/
    private View.OnClickListener chooseImageBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //打开选择,本次允许选择的数量
            ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
            Intent intent = new Intent(WxDemoActivity.this, ImageGridActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        }
    };

    /**
     * 删除
     **/
    private View.OnClickListener submitBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (imageItems == null || imageItems.size() == 0) {
                MessageUtils.showMiddleToast(WxDemoActivity.this, "请选择需要上传的图片");
            } else {
                showProgressDialog("提交数据中");
                progressDialog.setCancelable(false);
                for (int i = 0; i < imageItems.size(); i++) {
                    startAsyncTask(imageItems.get(i).path);
                }
            }
        }
    };


    /**
     * 测试图片上传*
     */
    private String getuploadBuffer(String fileName) {

        try {
            File f = new File(fileName);
            if (f.exists() && f.isFile()) {
                Log.i(TAG, "size=" + f.length());
            } else {
            }


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
                String reviseresult = AndroidClientService.connectWebService(WxDemoActivity.this,
                        finalname, finalUpdataInfo, ownertable, ownerid, Constants.WORK_URL);
                return reviseresult;
            }

            @Override
            protected void onPostExecute(String workResult) {
                super.onPostExecute(workResult);
                if (workResult == null) {
                    MessageUtils.showMiddleToast(WxDemoActivity.this, "图片上传失败");
                } else {
                    MessageUtils.showMiddleToast(WxDemoActivity.this, "图片上传成功");
                    finish();
                }
                closeProgressDialog();
            }
        }.execute();

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

        Log.i(TAG, "name=" + name);
        return name;
    }


}
