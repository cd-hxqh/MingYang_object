package com.example.admin.eam.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.eam.R;
import com.example.admin.eam.config.Constants;
import com.example.admin.eam.model.WorkOrder;
import com.example.admin.eam.ui.activity.workorder.Work_DetailsActivity;
import com.example.admin.eam.ui.activity.workorder.Work_ListActivity;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by think on 2015/8/17.
 */
public class WorkListAdapter extends RecyclerView.Adapter<WorkListAdapter.ViewHolder> {
    Work_ListActivity mContext;
    String worktype;
    List<WorkOrder>workOrderList = new ArrayList<>();
    public WorkListAdapter(Work_ListActivity context,String worktype) {
        this.mContext = context;
        this.worktype = worktype;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WorkOrder workOrder = workOrderList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.work_wonum));
        holder.itemDescTitle.setText(mContext.getString(R.string.work_describe));
        holder.itemNum.setText(workOrder.WONUM);
        holder.itemDesc.setText(workOrder.DESCRIPTION);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!worktype.equals(Constants.DC)) {
                    Intent intent = new Intent(mContext, Work_DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("workOrder", workOrder);
                    intent.putExtras(bundle);
                    mContext.startActivityForResult(intent,0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return workOrderList.size();
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

    public void update(ArrayList<WorkOrder> data, boolean merge) {
        if (merge && workOrderList.size() > 0) {
            for (int i = 0; i < workOrderList.size(); i++) {
                WorkOrder workOrder = workOrderList.get(i);
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
        workOrderList = data;
        notifyDataSetChanged();
    }
//
    public void adddate(ArrayList<WorkOrder> data){
        if(data.size()>0){
            for(int i = 0;i < data.size();i++){
                if(!workOrderList.contains(data.get(i))){
                    workOrderList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}
