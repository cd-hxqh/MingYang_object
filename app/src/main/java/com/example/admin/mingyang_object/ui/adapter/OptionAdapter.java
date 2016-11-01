package com.example.admin.mingyang_object.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Failurelist;
import com.example.admin.mingyang_object.model.Item;
import com.example.admin.mingyang_object.model.JobPlan;
import com.example.admin.mingyang_object.model.Location;
import com.example.admin.mingyang_object.model.Option;
import com.example.admin.mingyang_object.model.Person;
import com.example.admin.mingyang_object.model.REGULARINSPECTIONPLANLINK;
import com.example.admin.mingyang_object.model.Uddept;
import com.example.admin.mingyang_object.model.Udfandetails;
import com.example.admin.mingyang_object.model.Udinvestp;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.model.Udvehicle;
import com.example.admin.mingyang_object.model.Udwd;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.ui.activity.OptionActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/8/17.
 */
public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {
    OptionActivity activity;
    List<Option> optionList = new ArrayList<>();
    boolean isNoDesc = false;
    public OptionAdapter(OptionActivity activity) {
        this.activity = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Option option = optionList.get(position);
        if (activity.optiontype== Constants.UDVEHICLE){
            holder.itemNumTitle.setText("车牌号:");
            holder.itemDescTitle.setText("司机:");
        }else {
            holder.itemNumTitle.setText("编号:");
            holder.itemDescTitle.setText("描述:");
        }

        if (isNoDesc){
            holder.itemDescTitle.setVisibility(View.GONE);
            holder.itemDesc.setVisibility(View.GONE);
        }
        holder.itemNum.setText(option.getName());
        holder.itemDesc.setText(option.getDesc());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.responseData(optionList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**
         * CardView*
         */
        public CardView cardView;
        /**
         * 编号名称*
         */
        public TextView itemNumTitle;
        /**
         * 描述名称*
         */
        public TextView itemDescTitle;
        /**
         * 编号*
         */
        public TextView itemNum;
        /**
         * 描述*
         */
        public TextView itemDesc;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);

            itemNumTitle=(TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle=(TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
        }
    }


    public void update(ArrayList<Option> data, boolean merge) {
        if (merge && optionList.size() > 0) {
            for (int i = 0; i < optionList.size(); i++) {
                Option workOrder = optionList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == workOrder) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(workOrder);
            }
        }
        optionList = data;
        notifyDataSetChanged();
    }
//
    public void addPersonDate(ArrayList<Person> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getPERSONID());
                option.setDesc(data.get(i).getDISPLAYNAME());
                option.setValue1(data.get(i).getPRIMARYPHONE());
                option.setValue2(data.get(i).getBRANCH());
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }

    public void addJobPlanDate(ArrayList<JobPlan> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getJPNUM());
                option.setDesc(data.get(i).getDESCRIPTION());
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }

    public void addUdproDate(ArrayList<Udpro> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getPRONUM());//项目编号
                option.setDesc(data.get(i).getDESCRIPTION());//项目描述
                option.setValue1(data.get(i).getBRANCH());//所属中心
                option.setValue2(data.get(i).getRESPONS());//责任人编号
                option.setValue3(data.get(i).getPROSTAGE());//项目当前阶段
                option.setValue4(data.get(i).getRESPONSNAME());//责任人描述
                option.setValue5(data.get(i).getRESPONSPHONE());//电话号码
                option.setValue6(data.get(i).getBRANCHDESC());//中心描述
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }

    public void addUdfandetailsDate(ArrayList<Udfandetails> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getLOCNUM());
                option.setDesc(data.get(i).getMODELTYPE());
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }

    public void addLocationDate(ArrayList<Location> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getLOCATION());
                option.setDesc(data.get(i).getDESCRIPTION());
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }

    public void addUdinvestpDate(ArrayList<Udinvestp> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getPLANNUM());
                option.setDesc(data.get(i).getPROJECTNUM());
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }

    public void addFailurelistDate(ArrayList<Failurelist> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getFAILURECODE());
                option.setDesc(data.get(i).getCODEDESC());
                option.setValue1(data.get(i).getFAILURELIST() + "");
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }

    public void addItemDate(ArrayList<Item> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getITEMNUM());
                option.setDesc(data.get(i).getDESCRIPTION());
                option.setValue1(data.get(i).getORDERUNIT());
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }
    public void addUdvehicleDate(ArrayList<Udvehicle> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getLICENSENUM());
                option.setDesc(data.get(i).getDRIVER());
                option.setValue1(data.get(i).getPRONUM());
                option.setValue2(data.get(i).getPRODESC());
                option.setValue3(data.get(i).getBRANCHDESC());
                option.setValue4(data.get(i).getVEHICLENAME());//车辆名称
                option.setValue5(data.get(i).getENDNUMBER());
                option.setValue6(data.get(i).getNUMBER1());//加油
                option.setValue7(data.get(i).getNUMBER2());//行驶
                option.setValue8(data.get(i).getNUMBER3());//维修
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }

    public void addWtcodeDate(ArrayList<String > data,boolean isNoDesc){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i));
                optionList.add(option);
            }
        }
        this.isNoDesc = isNoDesc;
        notifyDataSetChanged();
    }

    public void addWorkOrderDate(ArrayList<WorkOrder> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getWONUM());
                option.setDesc(data.get(i).getDESCRIPTION());
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }

    public void addUddeptDate(ArrayList<Uddept> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getDEPTNUM());
                option.setDesc(data.get(i).getDESCRIPTION());
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }

    public void addUdwdDate(ArrayList<Udwd> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getNUM());
                option.setDesc(data.get(i).getDESCRIPTION());
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }

    public void addRegularinspectionplanlinkDate(ArrayList<REGULARINSPECTIONPLANLINK> data){
        if(data.size()>0){
            Option option;
            for(int i = 0;i < data.size();i++){
                option = new Option();
                option.setName(data.get(i).getPLANNO());//定检计划编号
                option.setDesc(data.get(i).getPRODESC());//项目描述
                option.setValue1(data.get(i).getPRONUM());//项目编号
                option.setValue2(data.get(i).getBRANCH());//中心编号
                option.setValue3(data.get(i).getBRANCHDESC());//中心描述
                option.setValue4(data.get(i).getHEAD());//负责人
                option.setValue5(data.get(i).getHEADNAME());//负责人描述
                option.setValue6(data.get(i).getFJNO());//风机型号
                option.setValue7(data.get(i).getSTANDARDNUM());//定检标准编号
                option.setValue8(data.get(i).getJPDESC());//定检标准描述
                option.setValue9(data.get(i).getPLANSTARTTIME());//计划开始时间
                option.setValue10(data.get(i).getPLANENDTIME());//计划完成时间
                option.setValue11(data.get(i).getREGULARINSPECTIONTYPE());//定检类型
                optionList.add(option);
            }
        }
        notifyDataSetChanged();
    }
}
