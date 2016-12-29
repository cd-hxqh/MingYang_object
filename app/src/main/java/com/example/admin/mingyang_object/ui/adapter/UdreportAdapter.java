package com.example.admin.mingyang_object.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Udreport;
import com.example.admin.mingyang_object.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 * 故障提报单
 */
public class UdreportAdapter extends BaseQuickAdapter<Udreport> {
    public UdreportAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, Udreport item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.item_num_title, mContext.getString(R.string.reportnum_text));
        helper.setText(R.id.item_desc_title, mContext.getString(R.string.woactivity_description));
        helper.setText(R.id.item_num_text, item.getREPORTNUM());
        helper.setText(R.id.item_desc_text, item.getDESCRIPTION());
    }


}
