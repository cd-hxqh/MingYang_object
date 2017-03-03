package com.example.admin.eam.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin.eam.R;
import com.example.admin.eam.manager.AppManager;
import com.example.admin.eam.ui.fragment.FuntionFragment;
import com.example.admin.eam.ui.fragment.MyselfFragment;
import com.example.admin.eam.ui.fragment.TabbarFragment;
import com.example.admin.eam.ui.fragment.WfmentFragment;
import com.example.admin.eam.utils.AccountUtils;


public class MainActivity extends BaseActivity {

    /**
     * 功能中心\流程审批\个人设置*
     */
    FuntionFragment funtionFragment;
    WfmentFragment wfmentFragment;
    MyselfFragment myselfFragment;
    TabbarFragment tabbarFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
    }

    @Override
    protected void findViewById() {

        tabbarFragment = (TabbarFragment)getSupportFragmentManager().findFragmentById(R.id.id_fragment_content);
        funtionFragment = (FuntionFragment)getSupportFragmentManager().findFragmentById(R.id.id_fragment_frist);
        wfmentFragment = (WfmentFragment)getSupportFragmentManager().findFragmentById(R.id.id_fragment_second);
        myselfFragment = (MyselfFragment)getSupportFragmentManager().findFragmentById(R.id.id_fragment_third);

        showFragment(1);
    }
    @Override
    protected void initView() {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("登陆","用户名"+ AccountUtils.getuserName(MainActivity.this));
        Log.e("登陆","密码"+AccountUtils.getUserPassword(MainActivity.this));
        Log.e("登陆","服务器"+AccountUtils.getIpAddress(MainActivity.this));
        String password = AccountUtils.getUserPassword(MainActivity.this);
        if (null==password||password.length()==0)
        {
            AppManager.killAllActivity();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, getResources().getString(R.string.exit_text), Toast.LENGTH_LONG).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(MainActivity.this);
        }
    }
    public void showFragment(int index)
    {
        if (funtionFragment==null||wfmentFragment==null||myselfFragment==null)
        {
            return;
        }

        Log.e("Fragment",""+index);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        transaction.hide(funtionFragment);
        transaction.hide(wfmentFragment);
        transaction.hide(myselfFragment);
        switch (index)
        {
            case 1:{
                transaction.show(funtionFragment);
            }break;

            case 2:{
                transaction.show(wfmentFragment);
            }break;

            case 3:{
                transaction.show(myselfFragment);
            }break;

        }
        transaction.commit();
    }

}
