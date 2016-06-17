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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.api.JsonUtils;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.ui.activity.BaseActivity;
import com.example.admin.mingyang_object.utils.DateTimeSelect;
import com.example.admin.mingyang_object.utils.WorkTitle;
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
public class Work_DetailsActivity extends BaseActivity {

    private TextView titlename;
    private ImageView menuImageView;
    private RelativeLayout backlayout;
    private PopupWindow popupWindow;

    /**
     * 工作计划*
     */
    private LinearLayout planLinearlayout;
//    /**
//     * 任务分配*
//     */
//    private LinearLayout taskLinearLayout;
    /**
     * 实际情况
     */
    private LinearLayout realinfoLinearLayout;
    /**
     * 故障汇报*
     */
    private LinearLayout reportLinearLayout;
    private WorkOrder workOrder;
    private LinearLayout work_numlayout;
    private TextView wonum;//工单号
    private EditText description;//工单描述
    private LinearLayout description_layout;


    private Button delete;
    private Button revise;
    private Button work_flow;

//    private ArrayList<Woactivity> woactivityList = new ArrayList<>();
//    private ArrayList<Labtrans> labtransList = new ArrayList<>();
//    private ArrayList<Failurereport> failurereportList = new ArrayList<>();

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_details);
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
        workOrder = (WorkOrder) getIntent().getSerializableExtra("workOrder");
    }

    @Override
    protected void findViewById() {
        titlename = (TextView) findViewById(R.id.title_name);
        menuImageView = (ImageView) findViewById(R.id.title_add);
        backlayout = (RelativeLayout) findViewById(R.id.title_back);

        work_numlayout = (LinearLayout) findViewById(R.id.work_numlayout);
        wonum = (TextView) findViewById(R.id.work_wonum);
        description = (EditText) findViewById(R.id.work_describe);
        description_layout = (LinearLayout) findViewById(R.id.work_describe_layout);
    }

    @Override
    protected void initView() {
        titlename.setText(WorkTitle.getTitle(workOrder.WORKTYPE));
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        menuImageView.setImageResource(R.drawable.ic_drawer);
        menuImageView.setVisibility(View.VISIBLE);
        menuImageView.setOnClickListener(menuImageViewOnClickListener);

        workOrder.isnew = false;

        wonum.setText(workOrder.WONUM);
        description.setText(workOrder.DESCRIPTION);




//        delete.setOnClickListener(deleteOnClickListener);
//        revise.setOnClickListener(reviseOnClickListener);
//        work_flow.setOnClickListener(approvalBtnOnClickListener);

        setLayout();
    }

    private View.OnClickListener udyxjOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NormalListDialog();
        }
    };

    private void NormalListDialog() {
        final NormalListDialog dialog = new NormalListDialog(Work_DetailsActivity.this, mMenuItems);
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
            new DateTimeSelect(Work_DetailsActivity.this, textView).showDialog();
        }
    }

    //按照工单类型修改布局
    private void setLayout() {
        switch (workOrder.WORKTYPE) {
            case "FR"://故障工单
                break;
            case "AA"://终验收工单

                break;
            case "DC"://调试工单

                break;
            case "SP"://排查工单

                break;
            case "TP"://技改工单

                break;
            case "WS"://定检工单
                break;
            default:
                break;
        }
    }

    //生成菜单
    private void decisionLayout() {
        switch (workOrder.WORKTYPE) {
            case "FR"://故障工单
                break;
            case "AA"://终验收工单

                break;
            case "DC"://调试工单

                break;
            case "SP"://排查工单

                break;
            case "TP"://技改工单

                break;
            case "WS"://定检工单
                break;
            default:
                break;
        }
    }


    private View.OnClickListener menuImageViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            showPopupWindow(menuImageView);
        }
    };

    /**
     * 初始化showPopupWindow*
     */
//    private void showPopupWindow(View view) {
//
//        // 一个自定义的布局，作为显示的内容
//        View contentView = LayoutInflater.from(Work_DetailsActivity.this).inflate(
//                R.layout.work_popup_window, null);
//
//
//        popupWindow = new PopupWindow(contentView,
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setTouchable(true);
//        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//                return false;
//                // 这里如果返回true的话，touch事件将被拦截
//                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
//            }
//        });
//
//        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
//        // 我觉得这里是API的一个bug
//        popupWindow.setBackgroundDrawable(getResources().getDrawable(
//                R.drawable.popup_background_mtrl_mult));
//
//        // 设置好参数之后再show
//        popupWindow.showAsDropDown(view);
//
//        planLinearlayout = (LinearLayout) contentView.findViewById(R.id.work_plan_id);
////        taskLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_task_id);
//        realinfoLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_realinfo_id);
//        reportLinearLayout = (LinearLayout) contentView.findViewById(R.id.work_report_id);
//        planLinearlayout.setOnClickListener(planOnClickListener);
////        taskLinearLayout.setOnClickListener(taskOnClickListener);
//        realinfoLinearLayout.setOnClickListener(realinfoOnClickListener);
//        reportLinearLayout.setOnClickListener(reportOnClickListener);
//        decisionLayout();
//
//    }
//
//    private View.OnClickListener planOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(Work_detailsActivity.this, Woactivity_Activity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("workOrder", workOrder);
//            bundle.putSerializable("woactivityList", woactivityList);
//            intent.putExtras(bundle);
//            startActivityForResult(intent,1000);
//            popupWindow.dismiss();
//        }
//    };
//
////    private View.OnClickListener taskOnClickListener = new View.OnClickListener() {
////        @Override
////        public void onClick(View view) {
////            Intent intent = new Intent(Work_detailsActivity.this, AssignmentActivity.class);
////            Bundle bundle = new Bundle();
////            bundle.putSerializable("workOrder", workOrder);
////            intent.putExtras(bundle);
////            startActivity(intent);
////            popupWindow.dismiss();
////        }
////    };
//
//    private View.OnClickListener realinfoOnClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(Work_detailsActivity.this, LabtransListActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("workOrder", workOrder);
//            bundle.putSerializable("woactivityList", woactivityList);
//            bundle.putSerializable("labtransList", labtransList);
//            intent.putExtras(bundle);
//            startActivityForResult(intent, 2000);
//            popupWindow.dismiss();
//        }
//    };
//
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
        final NormalDialog dialog = new NormalDialog(Work_DetailsActivity.this);
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
//            String updataInfo = null;
////            if (workOrder.status.equals(Constants.WAIT_APPROVAL)) {
//                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), woactivityList, labtransList, failurereportList);
////            } else if (workOrder.status.equals(Constants.APPROVALED)) {
////                updataInfo = JsonUtils.WorkToJson(getWorkOrder(), null, null, null, null, getLabtransList());
////            }
//            final String finalUpdataInfo = updataInfo;
//            new AsyncTask<String, String, WorkResult>() {
//                @Override
//                protected WorkResult doInBackground(String... strings) {
//                    WorkResult reviseresult = AndroidClientService.UpdateWO(finalUpdataInfo, AccountUtils.getpersonId(Work_detailsActivity.this), Constants.WORK_URL);
//                    return reviseresult;
//                }
//
//                @Override
//                protected void onPostExecute(WorkResult workResult) {
//                    super.onPostExecute(workResult);
//                    if (workResult==null) {
//                        Toast.makeText(Work_detailsActivity.this, "修改工单失败", Toast.LENGTH_SHORT).show();
//                    }else if (workResult.errorMsg.equals("成功!")) {
//                        Toast.makeText(Work_detailsActivity.this, "修改工单成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(Work_detailsActivity.this, workResult.errorMsg, Toast.LENGTH_SHORT).show();
//                    }
//                    closeProgressDialog();
//                }
//            }.execute();
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
        final NormalDialog dialog = new NormalDialog(Work_DetailsActivity.this);
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
//        new AsyncTask<String, String, WorkResult>() {
//            @Override
//            protected WorkResult doInBackground(String... strings) {
//                WorkResult reviseresult = AndroidClientService.DeleteWO(wonum.getText().toString(), AccountUtils.getpersonId(Work_detailsActivity.this), Constants.WORK_URL);
//                return reviseresult;
//            }
//
//            @Override
//            protected void onPostExecute(WorkResult workResult) {
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
        final MaterialDialog dialog = new MaterialDialog(Work_DetailsActivity.this);
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
                        wfgoon(workOrder.WONUM, "1", "");
                        dialog.dismiss();
                    }
                }
        );
    }


    private void EditDialog(final boolean isok) {//输入审核意见
        final NormalEditTextDialog dialog = new NormalEditTextDialog(Work_DetailsActivity.this);
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
                        wfgoon(workOrder.WONUM, "1", text);

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

    private WorkOrder getWorkOrder() {
        WorkOrder workOrder = this.workOrder;
        return workOrder;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Option option;
//        switch (resultCode) {
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
//            case 1000:
//                woactivityList = (ArrayList<Woactivity>) data.getSerializableExtra("woactivityList");
//                break;
//            case 2000:
//                labtransList = (ArrayList<Labtrans>) data.getSerializableExtra("labtransList");
//                break;
//            case 3000:
//                failurereportList = (ArrayList<Failurereport>) data.getSerializableExtra("failurereportList");
//                break;
//            default:
//                break;
//        }
    }
}
