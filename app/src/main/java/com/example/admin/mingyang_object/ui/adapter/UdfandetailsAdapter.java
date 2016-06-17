package com.example.admin.mingyang_object.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Udfandetails;
import com.example.admin.mingyang_object.model.Udpro;
import com.example.admin.mingyang_object.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 */
public class UdfandetailsAdapter extends BaseQuickAdapter<Udfandetails> {
    public UdfandetailsAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, Udfandetails item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.item_num_title, mContext.getString(R.string.locnum_text));
        helper.setText(R.id.item_desc_title, mContext.getString(R.string.modeltype_text));
        helper.setText(R.id.item_num_text, item.getLOCNUM());
        helper.setText(R.id.item_desc_text, item.getMODELTYPE());
    }


}
