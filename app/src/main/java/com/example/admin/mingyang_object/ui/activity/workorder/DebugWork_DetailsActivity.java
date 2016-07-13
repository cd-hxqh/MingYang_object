package com.example.admin.mingyang_object.ui.activity.workorder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.DebugWorkOrder;
import com.example.admin.mingyang_object.model.UddebugWorkOrderLine;
import com.example.admin.mingyang_object.model.WebResult;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.utils.AccountUtils;
import com.example.admin.mingyang_object.utils.DateTimeSelect;
import com.example.admin.mingyang_object.webserviceclient.AndroidClientService;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.entity.DialogMenuItem;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.listener.OnBtnEditClickL;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.flyco.dialog.widget.NormalEditTextDialog;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;


/**
 * Created by think on 2015/10/29.
 */
public class DebugWork_DetailsActivity extends BaseActivity {

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    private PopupWindow popupWindow;

    /**
     * 工作计划*
     */
    private LinearLayout planLinearlayout;

    private DebugWorkOrder workOrder;
    private TextView debugworkordernum;//工单号
    private EditText description;//描述
    private TextView pronum;//项目编号
    private TextView status;//状态
    private TextView createby;//创建人
    private Button delete;
    private Button revise;
    private Button work_flow;
    private Button save;
    private Button cancel;

    private ArrayList<UddebugWorkOrderLine> uddebugWorkOrderLines = new ArrayList<>();
//    private ArrayList<Labtrans> labtransList = new ArrayList<>();
//    private ArrayList<Failurereport> failurereportList = new ArrayList<>();
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debugwork_details);
        geiIntentData();
        findViewById();
        initView();
        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        addudyxjData();
    }

    /**
     * 获取数据*
     */
    private void geiIntentData() {
        workOrder = (DebugWorkOrder) getIntent().getSerializableExtra("debugworkOrder");
    }

    @Override
    protected void findViewById() {

        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);
        debugworkordernum = (TextView) findViewById(R.id.debugwork_debugworkordernum);
        description = (EditText) findViewById(R.id.debug_description);
        pronum = (TextView) findViewById(R.id.debug_pronum);
        status = (TextView) findViewById(R.id.debug_status);
        createby = (TextView) findViewById(R.id.debug_createby);
        save=(Button)findViewById(R.id.work_save);
        cancel=(Button)findViewById(R.id.work_cancel);
    }

    @Override
    protected void initView() {
        titlename.setText("调试工单详情");
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        menuImageView.setImageResource(R.mipmap.ic_more);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        if (!workOrder.isnew) {
            workOrder.isnew = false;
        }

        debugworkordernum.setText(workOrder.DEBUGWORKORDERNUM);
        description.setText(workOrder.DESCRIPTION);
        pronum.setText(workOrder.PRONUM);
        status.setText(workOrder.STATUS);
        createby.setText(workOrder.CREATEBY);
//        delete.setOnClickListener(deleteOnClickListener);
//        revise.setOnClickListener(reviseOnClickListener);
//        work_flow.setOnClickListener(approvalBtnOnClickListener);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("调试工单","调试工单详情 确定");
                submitDataInfo();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("调试工单","调试工单详情 取消");
                finish();
            }
        });
    }

    private View.OnClickListener udyxjOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalListDialog();
        }
    };

    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(DebugWork_DetailsActivity.this, mMenuItems);
        dialog.title("请选择")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {

//                udyxj.setText(mMenuItems.get(position).mOperName);

                dialog.dismiss();
            }
        });
    }

    /**
     * 添加数据*
     */
    private void addudyxjData() {
//        String[] lctypes = getResources().getStringArray(R.array.udyxj_tab_titles);

//        for (int i = 0; i < lctypes.length; i++)
//            mMenuItems.add(new DialogMenuItem(lctypes[i], 0));
    }

    //时间选择监听
    private class TimeOnClickListener implements View.OnClickListener{
        TextView textView;
        private TimeOnClickListener(TextView textView){
            this.textView = textView;
        }
        @Override
        public void onClick(View view) {
            new DateTimeSelect(DebugWork_DetailsActivity.this, textView).showDialog();
        }
    }

    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showPopupWindow(menuImageView);
        }
    };

    /**
     * 初始化showPopupWindow*
     */
    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(DebugWork_DetailsActivity.this).inflate(
                R.layout.debugwork_popup_window, null);


        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.popup_background_mtrl_mult));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(view);
        planLinearlayout = (LinearLayout) contentView.findViewById(R.id.debug_uddebugworkorderline);
        planLinearlayout.setOnClickListener(planOnClickListener);

    }

    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(DebugWork_DetailsActivity.this, DebugWork_UddebugWorkOrderLineActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("debugworkOrder", workOrder);
            bundle.putSerializable("uddebugWorkOrderLines", uddebugWorkOrderLines);
            intent.putExtra("pronum",pronum.getText().toString());
            intent.putExtras(bundle);
            startActivityForResult(intent,1000);
            popupWindow.dismiss();
        }
    };

//    private View.OnClickListener reportOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            if (failurecode.getText().toString().equals("")) {
//                popupWindow.dismiss();
//                Toast.makeText(Work_detailsActivity.this, "请选选择故障子机构", Toast.LENGTH_SHORT).show();
//            } else {
//                Intent intent = new Intent(Work_detailsActivity.this, Work_FailurereportActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("workOrder", getWorkOrder());
//                bundle.putSerializable("failurereportList", failurereportList);
//                intent.putExtras(bundle);
//                startActivityForResult(intent, 3000);
//                popupWindow.dismiss();
//            }
//        }
//    };



    /**
     * 提交数据*
     */
    private void submitDataInfo() {
        final NormalDialog dialog = new NormalDialog(DebugWork_DetailsActivity.this);
        dialog.content("确定修改工单吗?")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        showProgressDialog("数据提交中...");
                        startAsyncTask();
                        dialog.dismiss();
                    }
                });
    }


    /**
     * 提交数据*
     */
    private void startAsyncTask() {
//        if (NetWorkHelper.isNetwork(Work_DetailsActivity.this)) {
//            MessageUtils.showMiddleToast(Work_DetailsActivity.this, "暂无网络,现离线保存数据!");
//            saveWorkOrder();
//        } else {
            String updataInfo = null;
////            if (workOrder.status.equals(Constants.WAIT_APPROVAL)) {
                updataInfo = JsonUtils.DebugWorkToJson(getWorkOrder(), uddebugWorkOrderLines);
////            } else if (workOrder.status.equals(Constants.APPROVALED)) {
////                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
////            }
            final String finalUpdataInfo = updataInfo;
            final String tmp_prnum= pronum.getText().toString();
            new AsyncTask<String, String, WebResult>() {
                @Override
                protected WebResult doInBackground(String... strings) {
                    WebResult reviseresult = AndroidClientService.UpdateWO(DebugWork_DetailsActivity.this,finalUpdataInfo,"DEBUGWORKORDER", "DEBUGWORKORDERNUM",tmp_prnum,Constants.WORK_URL);
                    return reviseresult;
                }

                @Override
                protected void onPostExecute(WebResult workResult) {
                    super.onPostExecute(workResult);
                    if (workResult==null) {
                        Toast.makeText(DebugWork_DetailsActivity.this, "修改工单失败", Toast.LENGTH_SHORT).show();
                    }else if (workResult.errorMsg.equals("成功!")) {
                        Toast.makeText(DebugWork_DetailsActivity.this, "修改工单成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DebugWork_DetailsActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
                    }
                    closeProgressDialog();
                }
            }.execute();
////        }

    }


    private class LayoutOnClickListener implements View.OnClickListener {
        int requestCode;

        private LayoutOnClickListener(int requestCode) {
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(Work_detailsActivity.this, OptionActivity.class);
//            intent.putExtra("requestCode", requestCode);
//            if (requestCode == Constants.JOBPLANCODE) {
//                intent.putExtra("AssetIsChoose", assetnum.getText().toString().equals(""));
//            } else if ((requestCode == Constants.LABORCODE1 || requestCode == Constants.LABORCODE2
//                    || requestCode == Constants.LABORCODE3) && !udqxbz.getText().toString().equals("")) {
//                intent.putExtra("udqxbz", udqxbz.getText().toString());
//            }
//            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 提交数据*
     */
    private void deleteDataInfo() {
        final NormalDialog dialog = new NormalDialog(DebugWork_DetailsActivity.this);
        dialog.content("确定删除工单吗?")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)//
                .show();
        dialog.setOnBtnClickL(
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        showProgressDialog("数据提交中...");
                        deleteAsyncTask();
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 提交数据*
     */
    private void deleteAsyncTask() {
//        new AsyncTask<String, String, WebResult>() {
//            @Override
//            protected WebResult doInBackground(String... strings) {
//                WebResult reviseresult = AndroidClientService.DeleteWO(wonum.getText().toString(), AccountUtils.getpersonId(Work_detailsActivity.this), Constants.WORK_URL);
//                return reviseresult;
//            }
//
//            @Override
//            protected void onPostExecute(WebResult workResult) {
//                super.onPostExecute(workResult);
//                if (workResult==null) {
//                    Toast.makeText(Work_detailsActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
//                }else if (workResult.errorMsg.equals("操作成功！")&&workResult.errorNo.equals("0")) {
//                    Toast.makeText(Work_detailsActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
//                    Work_detailsActivity.this.finish();
//                } else {
//                    Toast.makeText(Work_detailsActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
//                }
//                closeProgressDialog();
//            }
//        }.execute();
////        }

    }

    //工作流审批
    private View.OnClickListener approvalBtnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MaterialDialogOneBtn1();
        }
    };


    private void MaterialDialogOneBtn1() {//审批工作流
        final MaterialDialog dialog = new MaterialDialog(DebugWork_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content("是否填写输入意见")//
                .btnText("是", "否，直接提交")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnClickL() {//是
                    @Override
                    public void onBtnClick() {
                        EditDialog(true);
                        dialog.dismiss();
                    }
                },
                new OnBtnClickL() {//否
                    @Override
                    public void onBtnClick() {
                        wfgoon(workOrder.DEBUGWORKORDERNUM, "1", "");
                        dialog.dismiss();
                    }
                }
        );
    }


    private void EditDialog(final boolean isok) {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(DebugWork_DetailsActivity.this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.isTitleShow(false)//
                .btnNum(2)
                .content(isok ? "通过" : "不通过")//
                .btnText("提交", "取消")//
                .showAnim(mBasIn)//
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {
                        wfgoon(workOrder.DEBUGWORKORDERNUM, "1", text);

                        dialog.dismiss();
                    }
                },
                new OnBtnEditClickL() {
                    @Override
                    public void onBtnClick(String text) {

                        dialog.dismiss();
                    }
                }
        );
    }


    /**
     * 审批工作流
     *
     * @param id
     * @param zx
     */
    private void wfgoon(final String id, final String zx, final String desc) {
//        mProgressDialog = ProgressDialog.show(Work_DetailsActivity.this, null,
//                getString(R.string.inputing), true, true);
//        mProgressDialog.setCanceledOnTouchOutside(false);
//        mProgressDialog.setCancelable(false);
//        new AsyncTask<String, String, String>() {
//            @Override
//            protected String doInBackground(String... strings) {
//                String result = AndroidClientService.approve(Work_detailsActivity.this, "UDFJHWO", "WORKORDER", id, "WONUM" + "ID", zx, desc);
//
//                Log.i(TAG, "result=" + result);
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                if (s == null || s.equals("")) {
//                    Toast.makeText(Work_detailsActivity.this, "审批失败", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Work_detailsActivity.this, "审批成功", Toast.LENGTH_SHORT).show();
//                }
//                mProgressDialog.dismiss();
//            }
//        }.execute();
    }

    private DebugWorkOrder getWorkOrder() {
        DebugWorkOrder workOrder = this.workOrder;
        return workOrder;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Option option;
        switch (resultCode) {
//            case Constants.ASSETCODE:
//                option = (Option) data.getSerializableExtra("option");
//                assetnum.setText(option.getName());
//                workOrder.udassetbz = option.getValue();
//                break;
//            case Constants.JOBPLANCODE:
//                option = (Option) data.getSerializableExtra("option");
//                jpnum.setText(option.getName());
//                break;
//            case Constants.PERSONCODE:
//                option = (Option) data.getSerializableExtra("option");
//                reportedby.setText(option.getName());
//                break;
//            case Constants.LABORCODE:
//                option = (Option) data.getSerializableExtra("option");
//                lead.setText(option.getName());
//                break;
//            case Constants.LABORCODE1:
//                option = (Option) data.getSerializableExtra("option");
//                lead1.setText(option.getName());
//                break;
//            case Constants.LABORCODE2:
//                option = (Option) data.getSerializableExtra("option");
//                supervisor.setText(option.getName());
//                break;
//            case Constants.LABORCODE3:
//                option = (Option) data.getSerializableExtra("option");
//                udsupervisor2.setText(option.getName());
//                break;
//            case Constants.ALNDOMAINCODE:
//                option = (Option) data.getSerializableExtra("option");
//                udqxbz.setText(option.getName());
//                break;
//            case Constants.UDEVCODE:
//                option = (Option) data.getSerializableExtra("option");
//                udevnum.setText(option.getName());
//                break;
//            case Constants.PROJAPPR:
//                option = (Option) data.getSerializableExtra("option");
//                udprojapprnum.setText(option.getValue());
//                udbugnum.setText(option.getValue2());
//                break;
//            case Constants.PMCODE:
//                option = (Option) data.getSerializableExtra("option");
//                pmnum.setText(option.getName());
//                break;
//            case Constants.FAILURE_TYPE:
//                option = (Option) data.getSerializableExtra("option");
//                failurecode.setText(option.getName());
//                break;
//            case Constants.ALNDOMAIN2CODE:
//                option = (Option) data.getSerializableExtra("option");
//                udgzlbdm.setText(option.getName());
//                break;
            case 1000:
                uddebugWorkOrderLines = (ArrayList<UddebugWorkOrderLine>) data.getSerializableExtra("uddebugWorkOrderLines");
                break;
//            case 2000:
//                labtransList = (ArrayList<Labtrans>) data.getSerializableExtra("labtransList");
//                break;
//            case 3000:
//                failurereportList = (ArrayList<Failurereport>) data.getSerializableExtra("failurereportList");
//                break;
//            default:
//                break;
        }
    }
}
