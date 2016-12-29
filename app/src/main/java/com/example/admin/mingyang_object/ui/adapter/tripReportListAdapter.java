package com.example.admin.mingyang_object.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.UdTriprePort;
import com.example.admin.mingyang_object.ui.activity.TriprePort_DetailActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 16/8/23.
 */
public class tripReportListAdapter extends RecyclerView.Adapter<tripReportListAdapter.ViewHolder> {

    Context mContext;

    List<UdTriprePort> triprePorts = new ArrayList<>();

    public tripReportListAdapter(Context context) {
        this.mContext = context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final UdTriprePort triprePortr = triprePorts.get(position);
        holder.itemNumTitle.setText("编号");
        holder.itemDescTitle.setText("描述");
        holder.itemNum.setText(triprePortr.getSERIALNUMBER());
        holder.itemDesc.setText(triprePortr.getDESCRIPTION());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TriprePort_DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("triprePortr", triprePortr);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return triprePorts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        /*
 BINNUM :
 CURBAL :
 ITEMNUM :
 LOCATIONDESC :
 LOTNUM : ;
 ITEMDESC :
 LOCATION :
 UNIT : 单位
 */
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

    public void update(ArrayList<UdTriprePort> data, boolean merge) {
        if (merge && triprePorts.size() > 0) {
            for (int i = 0; i < triprePorts.size(); i++) {
                UdTriprePort workOrder = triprePorts.get(i);
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
        triprePorts = data;
        notifyDataSetChanged();
    }
    //
    public void adddate(ArrayList<UdTriprePort> data){
        if(data.size()>0){
            for(int i = 0;i < data.size();i++){
                if(!triprePorts.contains(data.get(i))){
                    triprePorts.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}

