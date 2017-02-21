package com.example.admin.eam.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.admin.eam.R;
import com.example.admin.eam.model.UdprorunlogLine4;
import com.example.admin.eam.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 */
public class UdprorunlogLine4Adapter extends BaseQuickAdapter<UdprorunlogLine4> {
    public UdprorunlogLine4Adapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, UdprorunlogLine4 item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.item_num_title, mContext.getString(R.string.udprorunlogline4_runlogdate));
        helper.setText(R.id.item_desc_title, mContext.getString(R.string.udprorunlogline4_type2));
        helper.setText(R.id.item_num_text, item.getRUNLOGDATE());
        helper.setText(R.id.item_desc_text, item.getTYPE2());

    }


}
