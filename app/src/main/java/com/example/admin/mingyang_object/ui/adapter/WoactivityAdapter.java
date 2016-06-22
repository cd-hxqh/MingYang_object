package com.example.admin.mingyang_object.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.config.Constants;
import com.example.admin.mingyang_object.model.Udfandetails;
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 */
public class WoactivityAdapter extends BaseQuickAdapter<Woactivity> {
    String worktype;
    public WoactivityAdapter(Context context, int layoutResId, List data,String worktype) {
        super(context, layoutResId, data);
        this.worktype = worktype;
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, Woactivity item) {
        CardView cardView = helper.getView(R.id.card_container);
        if (worktype.equals(Constants.AA)) {
            helper.setText(R.id.item_num_title, mContext.getString(R.string.work_taskid));
            helper.setText(R.id.item_desc_title, mContext.getString(R.string.woactivity_udzyscorn));
            helper.setText(R.id.item_num_text, item.getTASKID());
            helper.setText(R.id.item_desc_text, item.getUDZYSCORN());
        }else {
            helper.setText(R.id.item_num_title, mContext.getString(R.string.work_taskid));
            helper.setText(R.id.item_desc_title, mContext.getString(R.string.woactivity_description));
            helper.setText(R.id.item_num_text, item.getTASKID());
            helper.setText(R.id.item_desc_text, item.getDESCRIPTION());
        }
    }


}
