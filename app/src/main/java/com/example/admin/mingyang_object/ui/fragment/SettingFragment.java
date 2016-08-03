package com.example.admin.mingyang_object.ui.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.dao.UdinspoDao;
import com.example.admin.mingyang_object.dao.UdinsprojectDao;
import com.example.admin.mingyang_object.dao.WoactivityDao;
import com.example.admin.mingyang_object.dao.WorkOrderDao;
import com.example.admin.mingyang_object.dao.WpmaterialDao;
import com.example.admin.mingyang_object.manager.AppManager;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.Utils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * 设置fragment
 */
public class SettingFragment extends Fragment {


    private static final String TAG = "SettingFragment";
    /**
     * 是否在线*
     */
    TextView isonline;
    /**
     * 清除缓存*
     */
    RelativeLayout clearRelativeLayout;
//    /**
//     * 网络设置*
//     */
//    RelativeLayout workRelativeLayout;
//    /**
//     * 关于我们*
//     */
//    RelativeLayout usRelativeLayout;


    /**
     * 缓存大小*
     */
    TextView cacheSize;

    private Button logout;//注销


    /**
     * 自定义进度条 *
     */
    private ProgressDialog progressDialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.setting_layout, container, false);
        findById(view);
        return view;
    }


    /**
     * 初始化界面控件*
     */
    private void findById(View view) {
        isonline = (TextView) view.findViewById(R.id.isonline_id);
        clearRelativeLayout = (RelativeLayout) view.findViewById(R.id.clear_cache_id);


        cacheSize = (TextView) view.findViewById(R.id.cache_size_id);
        logout = (Button) view.findViewById(R.id.user_logout);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

    }

    private void initView() {
        isonline.setText(AccountUtils.getIsOffLine(getActivity()) ? "离线登录" : "在线");
//        dataRelativeLayout.setOnClickListener(dataRelativeLayoutOnClickListener);

        clearRelativeLayout.setOnClickListener(clearRelativeLayoutOnClickListener);
        logout.setOnClickListener(logoutOnClickListener);

        cacheSize.setText(getDataFileSize() + "/M");
        cacheSize.setVisibility(View.VISIBLE);

    }

    private View.OnClickListener dataRelativeLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
//            intent.setClass(getActivity(), SettingDownloadActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener logoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AccountUtils.ClearPassWord(getActivity());
            AppManager.AppExit(getActivity());
        }
    };

    private View.OnClickListener clearRelativeLayoutOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialog();
        }
    };


    /**
     * 弹出对话框*
     */

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("提示");
        builder.setMessage("清除缓存将删除本地所有记录，确定要清除缓存吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                ClearCache();
//                if (clearCache()) {
//                    cacheSize.setText(getDataFileSize());
                Toast.makeText(getActivity(), "清除成功", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(), "清除失败", Toast.LENGTH_SHORT).show();
//                }

            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


    }


    /**
     * 获取数据库文件的大小*
     */
    private String getDataFileSize() {

        boolean isSdCard = Utils.isSdCard();
        String file;
        if (isSdCard) {
            file = Constants.PATH_DB + getActivity().getPackageName() + File.separator + Constants.TB_NAME;
        } else {
            file = Constants.NOT_SDCARD_PATH_DB + getActivity().getPackageName() + File.separator + Constants.TB_NAME;
        }
        File dir = new File(file);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        double size = Utils.getDirSize(dir);

        //保留2位小数
        DecimalFormat df = new DecimalFormat("######0.00");
        String size1 = df.format(size);

        return size1;
    }


    /**
     * 清除缓存*
     */
    private boolean clearCache() {
        boolean isSdCard = Utils.isSdCard();
        String file;
        if (isSdCard) {
            file = Constants.PATH_DB + getActivity().getPackageName() + File.separator + Constants.TB_NAME;
        } else {
            file = Constants.NOT_SDCARD_PATH_DB + getActivity().getPackageName() + File.separator + Constants.TB_NAME;
        }

        return Utils.deleteFile(file);
    }

    /**
     * 清除缓存*
     */
    private void ClearCache() {
        new WorkOrderDao(getActivity()).deleteall();
        new WoactivityDao(getActivity()).deleteall();
        new WpmaterialDao(getActivity()).deleteall();
        new UdinspoDao(getActivity()).deleteall();
        new UdinsprojectDao(getActivity()).deleteall();
    }
}
