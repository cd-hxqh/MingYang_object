package com.example.admin.mingyang_object.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Failurelist;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/8/17.
 */
public class Failurelist2Adapter extends RecyclerView.Adapter<Failurelist2Adapter.ViewHolder> {
    Context mContext;
    List<Failurelist> failurelistList = new ArrayList<>();
    public Failurelist2Adapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_failurelist2, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Failurelist failurelist = failurelistList.get(position);
        holder.itemNum.setText(failurelist.FAILURECODE);
        holder.itemDesc.setText(failurelist.CODEDESC);
        holder.itemLoc.setText(failurelist.LOCDESC);
    }

    @Override
    public int getItemCount() {
        return failurelistList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**
         * CardView*
         */
        public CardView cardView;
        /**
         * 编号*
         */
        public TextView itemNum;
        /**
         * 描述*
         */
        public TextView itemDesc;
        /**
         * 位置*
         */
        public TextView itemLoc;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);
            itemNum = (TextView) view.findViewById(R.id.num_text_id);
            itemDesc = (TextView) view.findViewById(R.id.desc_text_id);
            itemLoc = (TextView) view.findViewById(R.id.location_text_id);
        }
    }

    public void update(ArrayList<Failurelist> data, boolean merge) {
        if (merge && failurelistList.size() > 0) {
            for (int i = 0; i < failurelistList.size(); i++) {
                Failurelist workOrder = failurelistList.get(i);
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
        failurelistList = data;
        notifyDataSetChanged();
    }
//
    public void adddate(ArrayList<Failurelist> data){
        if(data.size()>0){
            for(int i = 0;i < data.size();i++){
                if(!failurelistList.contains(data.get(i))){
                    failurelistList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}
