package com.example.admin.mingyang_object.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.Udinsproject;
import com.example.admin.mingyang_object.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 * 巡检项目
 */
public class UdinsprojectAdapter extends BaseQuickAdapter<Udinsproject> {
    public UdinsprojectAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, Udinsproject item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.jptask_id, mContext.getString(R.string.jptask_text));
        helper.setText(R.id.desc_id, mContext.getString(R.string.item_desc_title));
        helper.setText(R.id.result2_id, mContext.getString(R.string.result2_text));
        helper.setText(R.id.jptask_text_id, item.getJPTASK());
        helper.setText(R.id.item_desc_id, item.getDESCRIPTION());
        helper.setText(R.id.result2_text_id, (item.getOK()!=null&&item.getOK().equals("Y"))?"合格":"不合格");
    }


}
