package com.example.admin.eam.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.admin.eam.R;
import com.example.admin.eam.model.StockQuery;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by chris on 2016/12/12.
 */

public class StockQueryAdapter extends RecyclerView.Adapter<StockQueryAdapter.ViewHolder> {

    Context mContext;

    List<StockQuery> StockQuerys = new ArrayList<>();

    public StockQueryAdapter(Context context) {

        this.mContext = context;

    }
    @Override
    public StockQueryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_query_item, parent, false);

        return new StockQueryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final StockQueryAdapter.ViewHolder holder, final int position) {

        final StockQuery stockQuery = StockQuerys.get(position);

        holder.NUMBER.setText(position+1+"");
        holder.ITEMDESC.setText(stockQuery.getITEMDESC());
        holder.ITEMNUM.setText(stockQuery.getITEMNUM());
        holder.LOCATIONDESC.setText(stockQuery.getLOCATIONDESC());
        holder.LOCATION.setText(stockQuery.getLOCATION());
        holder.CURBAL.setText(stockQuery.getCURBAL());
        holder.UNIT.setText(stockQuery.getITEMUNIT());
        holder.BINNUM.setText(stockQuery.getBINNUM());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("库存查询","显示完整的物料名称");
            }
        });
    }

    @Override
    public int getItemCount() {
        return StockQuerys.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**
         * CardView*
         */
        public CardView cardView;
        /**
         * 物料名称*
         */
        public TextView ITEMDESC;
        /**
         * 序号*
         */
        public TextView NUMBER;
        /**
         * 物料编码*
         */
        public TextView ITEMNUM;
        /**
         * 仓库描述
         */
        public TextView LOCATIONDESC;
        /**
         * 仓库编码
         */
        public TextView  LOCATION;
         /**
         * 数量*
         */
        public TextView CURBAL;
        /**
         * 单位*
         */
        public TextView UNIT;
        /**
         * 属性*
         */
        public TextView BINNUM;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.stockcard_container);
            NUMBER =(TextView) view.findViewById(R.id.stock_query_number);
            ITEMDESC =(TextView) view.findViewById(R.id.stock_query_itemdesc);
            ITEMNUM =(TextView) view.findViewById(R.id.stock_query_itemnum);
            LOCATIONDESC =(TextView) view.findViewById(R.id.stock_query_locationdesc);
            LOCATION =(TextView) view.findViewById(R.id.stock_query_location);
            CURBAL =(TextView) view.findViewById(R.id.stock_query_curbal);
            UNIT =(TextView) view.findViewById(R.id.stock_query_unit);
            BINNUM =(TextView) view.findViewById(R.id.stock_query_binnum);
        }
    }

    public void update(ArrayList<StockQuery> data, boolean merge) {
        if (merge && StockQuerys.size() > 0) {
            for (int i = 0; i < StockQuerys.size(); i++) {
                StockQuery workOrder = StockQuerys.get(i);
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
        StockQuerys = data;
        notifyDataSetChanged();
    }
    //
    public void adddate(ArrayList<StockQuery> data){

        if(data.size()>0){
            for(int i = 0;i < data.size();i++){
                if(!StockQuerys.contains(data.get(i))){
                    StockQuerys.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }
}
