package com.example.admin.mingyang_object.ui.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.CardView;

import com.example.admin.mingyang_object.R;
import com.example.admin.mingyang_object.model.UdPerson;
import com.example.admin.mingyang_object.model.Udvehicle;
import com.example.admin.mingyang_object.ui.widget.BaseViewHolder;

import java.util.List;


/**
 * Created by apple on 15/10/26
 */
public class UdvehicleAdapter extends BaseQuickAdapter<Udvehicle> {
    public UdvehicleAdapter(Context context, int layoutResId, List data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 5)
            anim.setStartDelay(index * 150);
    }

    @Override
    protected void convert(BaseViewHolder helper, Udvehicle item) {
        CardView cardView = helper.getView(R.id.card_container);
        helper.setText(R.id.displayname_id, "车牌号:");
        helper.setText(R.id.personid_id, "司机:");
        helper.setText(R.id.primaryphone_id, "累计行驶里程数:");
        helper.setVisible(R.id.udjbdescription_id, false);
        helper.setText(R.id.displayname_text_id, item.getLICENSENUM());
        helper.setText(R.id.personid_text_id, item.getDRIVER() == null || item.getDRIVER().equals("null") ? "" : item.getDRIVER());
        helper.setText(R.id.primaryphone_text_id, item.getTOTALMILEAGE());
        helper.setVisible(R.id.udjbdescription_text_id, false);
    }


}
