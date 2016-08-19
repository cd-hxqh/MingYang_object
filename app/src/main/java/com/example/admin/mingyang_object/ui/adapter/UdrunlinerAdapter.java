package com.example.admin.mingyang_object.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.UdprorunlogLine2;
import com.example.admin.mingyang_object.model.Udrunliner;
import com.example.admin.mingyang_object.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 */
public class UdrunlinerAdapter extends BaseQuickAdapter<Udrunliner> {
    public UdrunlinerAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, Udrunliner item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.displayname_id, mContext.getString(R.string.udrunliner_logdate));
        helper.setText(R.id.personid_id, mContext.getString(R.string.udrunliner_worknum));
        helper.setText(R.id.primaryphone_id, mContext.getString(R.string.udrunliner_workpg));
        helper.setText(R.id.udjbdescription_id, mContext.getString(R.string.udrunliner_workcron));
        helper.setText(R.id.displayname_text_id, item.getLOGDATE());
        helper.setText(R.id.personid_text_id, item.getWORKNUM());
        helper.setText(R.id.primaryphone_text_id, item.getWORKPG());
        helper.setText(R.id.udjbdescription_text_id, item.getWORKCRON());

    }


}
