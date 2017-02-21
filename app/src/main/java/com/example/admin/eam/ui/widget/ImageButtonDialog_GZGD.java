package com.example.admin.eam.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.admin.eam.R;

/**
 * Created by chris on 2017/2/17.
 */

public class ImageButtonDialog_GZGD extends Dialog {

    private Context context;

    public ImageButtonDialog_GZGD(Context context) {
        super(context);
        this.context = context;
    }

    public ImageButtonDialog_GZGD(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.image_button_dialog_gzgd, null);
        setContentView(view);
    }

}
