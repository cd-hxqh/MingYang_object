package com.example.admin.eam.dao;

import android.content.Context;

import com.example.admin.eam.OrmLiteOpenHelper.DatabaseHelper;
import com.example.admin.eam.model.Udinspo;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2016/7/12.
 * 巡检单
 */
public class UdinspoDao {
    private Context context;
    private Dao<Udinspo, Integer> DaoOpe;
    private DatabaseHelper helper;

    public UdinspoDao(Context context)
    {
        this.context = context;
        try
        {
            helper = DatabaseHelper.getHelper(context);
            DaoOpe = helper.getDao(Udinspo.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新巡检单
     * @param udinspo
     */
    public void update(Udinspo udinspo) {
        try
        {
            DaoOpe.createOrUpdate(udinspo);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 更新巡检单信息
     * @param list
     */
    public void update(final ArrayList<Udinspo> list) {
        try
        {
            DaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Udinspo udinspo : list) {
                        if (!isexitByNum(udinspo.getINSPONUM())) {
                            DaoOpe.createOrUpdate(udinspo);
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有巡检单
     * @return
     */
    public List<Udinspo> queryForAll(){
        try {
            return DaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按状态查询巡检单
     * @return
     */
    public List<Udinspo> queryForAllByNum(String status){
        try {
            if (status.equals("全部")) {
                return DaoOpe.queryBuilder().orderBy("INSPONUM", false).query();
            }else {
                return DaoOpe.queryBuilder().orderBy("INSPONUM", false).where().eq("STATUS", status).query();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按状态查询巡检单
     * @return
     */
    public List<Udinspo> queryForAllByNum(String search,String status){
        try {
            if (status.equals("全部")) {
                return DaoOpe.queryBuilder().orderBy("INSPONUM", false)
                        .where().like("INSPONUM", "%" + search + "%").or().like("DESCRIPTION", "%" + search + "%").query();
            }else {
                return DaoOpe.queryBuilder().orderBy("INSPONUM", false).where().eq("STATUS", status)
                        .and().like("INSPONUM", "%" + search + "%").or().like("DESCRIPTION", "%" + search + "%").query();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询本地修改过的工单
     *
     * @return
     */
    public List<Udinspo> queryForLoc(String search,String userid) {
        try {
            if (search.equals("")) {
                return DaoOpe.queryBuilder().orderBy("INSPONUM", false).where().eq("belong",userid).query();
            } else {
                return DaoOpe.queryBuilder().orderBy("INSPONUM", false).where().eq("belong",userid)
                        .and().like("INSPONUM", "%" + search + "%").or().like("DESCRIPTION", "%" + search + "%").query();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    /**
//     * 按照工单类型查询工单
//     * @return
//     */
//    public List<Udinspo> queryByType(String type){
//        try {
//            return DaoOpe.queryBuilder().orderBy("WONUM",false).where().eq("WORKTYPE",type).query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 删除所有信息
     */
    public void deleteall(){
        try {
            DaoOpe.delete(DaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据巡检单id删除信息
     */
    public void deleteById(int id){
        try {
            DaoOpe.delete(DaoOpe.queryBuilder().where().eq("id",id).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按照巡检单id查询工单
     * @param id
     * @return
     */
    public Udinspo SearchByNum(int id){
        try {
            return DaoOpe.queryBuilder().where().eq("id",id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照巡检单及用户查询本地是否存在此工单
     * @param num
     * @return
     */
    public boolean isexitByNum(String num,String username){
        try {
            List<Udinspo> orderMainList = DaoOpe.queryBuilder().where().eq("INSPONUM",num).and().eq("belong",username).query();
            if(orderMainList.size()>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 按照巡检单单号查询本地是否存在此巡检单单
     * @param num
     * @return
     */
    public boolean isexitByNum(String num){
        try {
            List<Udinspo> orderMainList = DaoOpe.queryBuilder().where().eq("INSPONUM",num).query();
            if(orderMainList.size()>0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
