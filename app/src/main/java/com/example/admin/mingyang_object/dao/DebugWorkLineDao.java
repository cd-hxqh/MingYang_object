package com.example.admin.mingyang_object.dao;

/**
 * Created by chris on 16/7/27.
 */
import android.content.Context;

import com.example.admin.mingyang_object.OrmLiteOpenHelper.DatabaseHelper;
import com.example.admin.mingyang_object.model.UddebugWorkOrderLine;
import com.example.admin.mingyang_object.model.DebugWorkOrder;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DebugWorkLineDao {
    private Context context;
    private Dao<UddebugWorkOrderLine, Integer> UddebugWorkOrderLineDaoOpe;
    private DatabaseHelper helper;

    public DebugWorkLineDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            UddebugWorkOrderLineDaoOpe = helper.getDao(UddebugWorkOrderLine.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量存储任务
     * @param list
     */
    public void create(final List<UddebugWorkOrderLine> list) {
        try {
            deleteall();
            UddebugWorkOrderLineDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (UddebugWorkOrderLine uddebugWorkOrderLine : list) {
                        if (!isexitByNum(uddebugWorkOrderLine.DEBUGWORKORDERNUM,uddebugWorkOrderLine.UDDEBUGWORKORDERLINEID)){
                            UddebugWorkOrderLineDaoOpe.createOrUpdate(uddebugWorkOrderLine);
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create(UddebugWorkOrderLine uddebugWorkOrderLine) {
        try {
            if (!isexitByNum(uddebugWorkOrderLine.DEBUGWORKORDERNUM,uddebugWorkOrderLine.UDDEBUGWORKORDERLINEID)) {
                UddebugWorkOrderLineDaoOpe.createOrUpdate(uddebugWorkOrderLine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param uddebugWorkOrderLine
     */
    public int Update(UddebugWorkOrderLine uddebugWorkOrderLine) {
        try {
            return UddebugWorkOrderLineDaoOpe.create(uddebugWorkOrderLine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @return
     */
    public List<UddebugWorkOrderLine> queryForAll() {
        try {
            return UddebugWorkOrderLineDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param id
     */
    public List<UddebugWorkOrderLine> queryById(int id) {
        try {
            return UddebugWorkOrderLineDaoOpe.queryBuilder().where().eq("UDDEBUGWORKORDERLINEID", id ).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param wonum
     */
    public List<UddebugWorkOrderLine> queryByWonum(String  wonum) {
        try {
            return UddebugWorkOrderLineDaoOpe.queryBuilder().where().eq("DEBUGWORKORDERNUM", wonum ).query();
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
            UddebugWorkOrderLineDaoOpe.delete(UddebugWorkOrderLineDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param list
     */
    public void deleteList(final List<UddebugWorkOrderLine> list) {
        try {
            UddebugWorkOrderLineDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (UddebugWorkOrderLine woactivity : list) {
                        UddebugWorkOrderLineDaoOpe.delete(woactivity);
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
            UddebugWorkOrderLineDaoOpe.delete(UddebugWorkOrderLineDaoOpe.queryBuilder().where().eq("DEBUGWORKORDERNUM", wonum).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id
     * @return
     */
    public UddebugWorkOrderLine SearchByNum(int id) {
        try {
            return UddebugWorkOrderLineDaoOpe.queryBuilder().where().eq("id", id).queryForFirst();
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
            List<UddebugWorkOrderLine> workOrderList = UddebugWorkOrderLineDaoOpe.queryBuilder().where().eq("DEBUGWORKORDERNUM", wonum).and().eq("UDDEBUGWORKORDERLINEID",taskid).query();
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
