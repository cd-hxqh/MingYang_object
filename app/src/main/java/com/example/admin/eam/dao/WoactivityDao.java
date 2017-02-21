package com.example.admin.eam.dao;

import android.content.Context;

import com.example.admin.eam.OrmLiteOpenHelper.DatabaseHelper;
import com.example.admin.eam.model.Woactivity;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2016/7/12.
 */
public class WoactivityDao {
    private Context context;
    private Dao<Woactivity, Integer> WoactivityDaoOpe;
    private DatabaseHelper helper;

    public WoactivityDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            WoactivityDaoOpe = helper.getDao(Woactivity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量存储任务
     * @param list
     */
    public void create(final List<Woactivity> list) {
        try {
            deleteall();
            WoactivityDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Woactivity woactivity : list) {
                        if (!isexitByNum(woactivity.WONUM,woactivity.TASKID)){
                                WoactivityDaoOpe.createOrUpdate(woactivity);
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(Woactivity woactivity) {
        try {
            if (!isexitByNum(woactivity.WONUM,woactivity.TASKID)) {
                WoactivityDaoOpe.createOrUpdate(woactivity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param woactivity
     */
    public int Update(Woactivity woactivity) {
        try {
            return WoactivityDaoOpe.create(woactivity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @return
     */
    public List<Woactivity> queryForAll() {
        try {
            return WoactivityDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param id
     */
    public List<Woactivity> queryById(int id) {
        try {
            return WoactivityDaoOpe.queryBuilder().where().eq("belongid", id ).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param wonum
     */
    public List<Woactivity> queryByWonum(String  wonum) {
        try {
            return WoactivityDaoOpe.queryBuilder().where().eq("WONUM", wonum ).query();
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
            WoactivityDaoOpe.delete(WoactivityDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param list
     */
    public void deleteList(final List<Woactivity> list) {
        try {
            WoactivityDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (Woactivity woactivity : list) {
                        WoactivityDaoOpe.delete(woactivity);
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
            WoactivityDaoOpe.delete(WoactivityDaoOpe.queryBuilder().where().eq("belongid", wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id
     * @return
     */
    public Woactivity SearchByNum(int id) {
        try {
            return WoactivityDaoOpe.queryBuilder().where().eq("id", id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param wonum
     * @return
     */
    public boolean isexitByNum(String wonum,String taskid) {
        try {
            List<Woactivity> workOrderList = WoactivityDaoOpe.queryBuilder().where().eq("WONUM", wonum).and().eq("TASKID",taskid).query();
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
