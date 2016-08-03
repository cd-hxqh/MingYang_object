package com.example.admin.mingyang_object.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.UdprorunlogLine1;
import com.example.admin.mingyang_object.model.UdprorunlogLine2;
import com.example.admin.mingyang_object.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 */
public class UdprorunlogLine2Adapter extends BaseQuickAdapter<UdprorunlogLine2> {
    public UdprorunlogLine2Adapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, UdprorunlogLine2 item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.item_num_title, mContext.getString(R.string.udprorunlog_line2_createdate));
        helper.setText(R.id.item_desc_title, mContext.getString(R.string.udprorunlog_line2_personid));
        helper.setText(R.id.item_num_text, item.getCREATEDATE());
        helper.setText(R.id.item_desc_text, item.getCREATEBY()  );

    }


}
