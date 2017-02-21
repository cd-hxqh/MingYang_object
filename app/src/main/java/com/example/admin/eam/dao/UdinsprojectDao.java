package com.example.admin.eam.dao;

import android.content.Context;

import com.example.admin.eam.OrmLiteOpenHelper.DatabaseHelper;
import com.example.admin.eam.model.Udinsproject;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2016/7/12.
 */
public class UdinsprojectDao {
    private Context context;
    private Dao<Udinsproject, Integer> UdinsprojectDaoOpe;
    private DatabaseHelper helper;

    public UdinsprojectDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            UdinsprojectDaoOpe = helper.getDao(Udinsproject.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量存储任务
     * @param list
     */
    public void create(final List<Udinsproject> list) {
        try {
            deleteall();
            UdinsprojectDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Udinsproject udinsproject : list) {
                        if (!isexitByNum(udinsproject.UDINSPROJECTID)){
                            UdinsprojectDaoOpe.createOrUpdate(udinsproject);
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Udinsproject udinsproject) {
        try {
            if (!isexitByNum(udinsproject.UDINSPROJECTID)) {
                UdinsprojectDaoOpe.createOrUpdate(udinsproject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param woactivity
     */
    public int Update(Udinsproject woactivity) {
        try {
            return UdinsprojectDaoOpe.create(woactivity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @return
     */
    public List<Udinsproject> queryForAll() {
        try {
            return UdinsprojectDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param id
     */
    public List<Udinsproject> queryById(int id) {
        try {
            return UdinsprojectDaoOpe.queryBuilder().where().eq("belongid", id ).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param insponum
     */
    public List<Udinsproject> queryByInsponum(String  insponum) {
        try {
            return UdinsprojectDaoOpe.queryBuilder().where().eq("INSPONUM", insponum ).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     */
    public void deleteall() {
        try {
            UdinsprojectDaoOpe.delete(UdinsprojectDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param list
     */
    public void deleteList(final List<Udinsproject> list) {
        try {
            UdinsprojectDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Udinsproject woactivity : list) {
                        UdinsprojectDaoOpe.delete(woactivity);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void deleteByWonum(int wonum) {
        try {
            UdinsprojectDaoOpe.delete(UdinsprojectDaoOpe.queryBuilder().where().eq("belongid", wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id
     * @return
     */
    public Udinsproject SearchByNum(int id) {
        try {
            return UdinsprojectDaoOpe.queryBuilder().where().eq("id", id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param
     * @return
     */
    public boolean isexitByNum(String UDINSPROJECTID) {
        try {
            List<Udinsproject> workOrderList = UdinsprojectDaoOpe.queryBuilder().where().eq("UDINSPROJECTID",UDINSPROJECTID).query();
            if (workOrderList.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
