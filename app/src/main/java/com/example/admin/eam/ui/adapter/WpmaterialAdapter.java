package com.example.admin.eam.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.admin.eam.R;
import com.example.admin.eam.model.Wpmaterial;
import com.example.admin.eam.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 */
public class WpmaterialAdapter extends BaseQuickAdapter<Wpmaterial> {
    public WpmaterialAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, Wpmaterial item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.item_num_title, mContext.getString(R.string.wpmaterial_itemnum));
        helper.setText(R.id.item_desc_title, mContext.getString(R.string.wpmaterial_itemdesc));
        helper.setText(R.id.item_num_text, item.getITEMNUM());
        helper.setText(R.id.item_desc_text, item.getITEMDESC());

    }


}
