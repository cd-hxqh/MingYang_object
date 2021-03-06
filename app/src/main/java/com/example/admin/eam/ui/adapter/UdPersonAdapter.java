package com.example.admin.eam.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.admin.eam.R;
import com.example.admin.eam.model.UdPerson;
import com.example.admin.eam.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 */
public class UdPersonAdapter extends BaseQuickAdapter<UdPerson> {
    public UdPersonAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, UdPerson item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.displayname_id, mContext.getString(R.string.displayname_text));
        helper.setText(R.id.personid_id, mContext.getString(R.string.departdesc_text));
        helper.setText(R.id.primaryphone_id, mContext.getString(R.string.primaryphone_text));
        helper.setText(R.id.udjbdescription_id, mContext.getString(R.string.email_text));

        helper.setText(R.id.displayname_text_id, item.getNAME());
        helper.setText(R.id.personid_text_id, item.getDEPTNAME());
        helper.setText(R.id.primaryphone_text_id, item.getPHONE());
        helper.setText(R.id.udjbdescription_text_id, item.getEMAIL());
    }


}
