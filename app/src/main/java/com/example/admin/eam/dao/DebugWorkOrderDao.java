package com.example.admin.eam.dao;

/**
 * Created by chris on 16/7/27.
 */

import android.content.Context;

import com.example.admin.eam.OrmLiteOpenHelper.DatabaseHelper;
import com.example.admin.eam.model.DebugWorkOrder;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DebugWorkOrderDao {
    private Context context;
    private Dao<DebugWorkOrder, Integer> DebugworkOrderDaoOpe;
    private DatabaseHelper helper;

    public DebugWorkOrderDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            DebugworkOrderDaoOpe = helper.getDao(DebugWorkOrder.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新工单
     *
     * @param debugWorkOrder
     */
    public void update(DebugWorkOrder debugWorkOrder) {
        try {
            DebugworkOrderDaoOpe.createOrUpdate(debugWorkOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新工单信息
     *
     * @param list
     */
    public void update(final ArrayList<DebugWorkOrder> list) {
        try {
            DebugworkOrderDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (DebugWorkOrder workOrder : list) {
                        if (!isexitByNum(workOrder.DEBUGWORKORDERNUM)) {
                            DebugworkOrderDaoOpe.createOrUpdate(workOrder);
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有工单
     *
     * @return
     */
    public List<DebugWorkOrder> queryForAll() {
        try {
            return DebugworkOrderDaoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照工单类型状态查询工单
     *
     * @return
     */
    public List<DebugWorkOrder> queryByType(String type, String status) {
        try {
            if (status.equals("全部")) {
                return DebugworkOrderDaoOpe.queryBuilder().orderBy("DEBUGWORKORDERNUM", false).query();
            } else {
                return DebugworkOrderDaoOpe.queryBuilder().orderBy("DEBUGWORKORDERNUM", false).where().eq("STATUS", status).query();
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
    public List<DebugWorkOrder> queryForLoc(String type,String search,String userid) {
        try {
            if (search.equals("")) {
                return DebugworkOrderDaoOpe.queryBuilder().orderBy("DEBUGWORKORDERNUM", false).where().eq("belong",userid).query();
            } else {
                return DebugworkOrderDaoOpe.queryBuilder().orderBy("DEBUGWORKORDERNUM", false).where().eq("belong",userid)
                        .and().like("DEBUGWORKORDERNUM", "%" + search + "%").or().like("DESCRIPTION", "%" + search + "%").query();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照工单类型及搜索字段查询工单
     *
     * @return
     */
    public List<DebugWorkOrder> queryByType2(String type, String search, String status) {
        try {
            if (status.equals("全部")) {
                return DebugworkOrderDaoOpe.queryBuilder().orderBy("DEBUGWORKORDERNUM", false).where().like("DEBUGWORKORDERNUM", "%" + search + "%").or().like("DESCRIPTION", "%" + search + "%").query();
            } else {
                return DebugworkOrderDaoOpe.queryBuilder().orderBy("DEBUGWORKORDERNUM", false).where().eq("STATUS", status)
                        .and().like("DEBUGWORKORDERNUM", "%" + search + "%").or().like("DESCRIPTION", "%" + search + "%").query();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    /**
//     * 查询维保工单
//     *
//     * @return
//     */
//    public List<DebugWorkOrder> queryForPMAndCM(String username, String desc) {
//        try {
//            if (desc.equals("")) {
//                return DebugworkOrderDaoOpe.queryBuilder().orderBy("date", false)
//                        .where().eq("wordtype", "PM").and().eq("belong", username)
//                        .and().eq("state", "APPR").or().eq("wordtype", "CM")
//                        .and().eq("belong", username).query();
//            } else {
//                return DebugworkOrderDaoOpe.queryBuilder().orderBy("date", false)
//                        .where().eq("wordtype", "PM").and().eq("belong", username)
//                        .and().eq("state", "APPR").and().like("describe", "%" + desc + "%").or().eq("wordtype", "CM")
//                        .and().eq("belong", username).and().like("describe", "%" + desc + "%").query();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 删除所有信息
     */
    public void deleteall() {
        try {
            DebugworkOrderDaoOpe.delete(DebugworkOrderDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据工单id删除信息
     */
    public void deleteById(int id) {
        try {
            DebugworkOrderDaoOpe.delete(DebugworkOrderDaoOpe.queryBuilder().where().eq("id", id).query());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按照工单id查询工单
     *
     * @param id
     * @return
     */
    public DebugWorkOrder SearchByNum(int id) {
        try {
            return DebugworkOrderDaoOpe.queryBuilder().where().eq("id", id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照工单及用户查询本地是否存在此工单
     *
     * @param num
     * @return
     */
    public boolean isexitByNum(String num, String username) {
        try {
            List<DebugWorkOrder> orderMainList = DebugworkOrderDaoOpe.queryBuilder().where().eq("DEBUGWORKORDERNUM", num).and().eq("belong", username).query();
            if (orderMainList.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 按照工单查询本地是否存在此工单
     *
     * @param num
     * @return
     */
    public boolean isexitByNum(String num) {
        try {
            List<DebugWorkOrder> orderMainList = DebugworkOrderDaoOpe.queryBuilder().where().eq("DEBUGWORKORDERNUM", num).query();
            if (orderMainList.size() > 0) {
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
