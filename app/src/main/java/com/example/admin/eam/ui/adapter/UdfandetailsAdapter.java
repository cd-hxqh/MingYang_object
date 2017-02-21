package com.example.admin.eam.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.admin.eam.R;
import com.example.admin.eam.model.Udfandetails;
import com.example.admin.eam.ui.widget.BaseViewHolder;

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
        helper.setText(R.id.item_num_title, mContext.getString(R.string.udfandetails_locnum));
        helper.setText(R.id.item_desc_title, mContext.getString(R.string.udfandetails_modeltype));
        helper.setText(R.id.item_num_text, item.getLOCNUM());
        helper.setText(R.id.item_desc_text, item.getMODELTYPE());
    }


}
