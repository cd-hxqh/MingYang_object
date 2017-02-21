package com.example.admin.eam.dao;

import android.content.Context;


import com.example.admin.eam.OrmLiteOpenHelper.DatabaseHelper;
import com.example.admin.eam.model.WorkOrder;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by think on 2016/7/12.
 * 工单
 */
public class WorkOrderDao {
    private Context context;
    private Dao<WorkOrder, Integer> WorkOrderDaoOpe;
    private DatabaseHelper helper;

    public WorkOrderDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            WorkOrderDaoOpe = helper.getDao(WorkOrder.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新工单
     *
     * @param workOrder
     */
    public void update(WorkOrder workOrder) {
        try {
            WorkOrderDaoOpe.createOrUpdate(workOrder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新工单信息
     *
     * @param list
     */
    public void update(final ArrayList<WorkOrder> list) {
        try {
            WorkOrderDaoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (WorkOrder workOrder : list) {
                        if (!isexitByNum(workOrder.WONUM)) {
                            WorkOrderDaoOpe.createOrUpdate(workOrder);
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
    public List<WorkOrder> queryForAll() {
        try {
            return WorkOrderDaoOpe.queryForAll();
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
    public List<WorkOrder> queryByType(String type, String status) {
        try {
            if (status.equals("全部")) {
                return WorkOrderDaoOpe.queryBuilder().orderBy("WONUM", false).where().eq("WORKTYPE", type).query();
            } else {
                return WorkOrderDaoOpe.queryBuilder().orderBy("WONUM", false).where().eq("WORKTYPE", type).and().eq("UDSTATUS", status).query();
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
    public List<WorkOrder> queryForLoc(String type,String search,String userid) {
        try {
            if (search.equals("")) {
                return WorkOrderDaoOpe.queryBuilder().orderBy("WONUM", false).where().eq("WORKTYPE", type).and().eq("belong",userid).query();
            } else {
                return WorkOrderDaoOpe.queryBuilder().orderBy("WONUM", false).where().eq("WORKTYPE", type).and().eq("belong",userid)
                        .and().like("WONUM", "%" + search + "%").or().like("DESCRIPTION", "%" + search + "%").query();
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
    public List<WorkOrder> queryByType2(String type, String search, String status) {
        try {
            if (status.equals("全部")) {
                return WorkOrderDaoOpe.queryBuilder().orderBy("WONUM", false).where().eq("WORKTYPE", type)
                        .and().like("WONUM", "%" + search + "%").or().like("DESCRIPTION", "%" + search + "%").query();
            } else {
                return WorkOrderDaoOpe.queryBuilder().orderBy("WONUM", false).where().eq("WORKTYPE", type).and().eq("UDSTATUS", status)
                        .and().like("WONUM", "%" + search + "%").or().like("DESCRIPTION", "%" + search + "%").query();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询维保工单
     *
     * @return
     */
    public List<WorkOrder> queryForPMAndCM(String username, String desc) {
        try {
            if (desc.equals("")) {
                return WorkOrderDaoOpe.queryBuilder().orderBy("date", false)
                        .where().eq("wordtype", "PM").and().eq("belong", username)
                        .and().eq("state", "APPR").or().eq("wordtype", "CM")
                        .and().eq("belong", username).query();
            } else {
                return WorkOrderDaoOpe.queryBuilder().orderBy("date", false)
                        .where().eq("wordtype", "PM").and().eq("belong", username)
                        .and().eq("state", "APPR").and().like("describe", "%" + desc + "%").or().eq("wordtype", "CM")
                        .and().eq("belong", username).and().like("describe", "%" + desc + "%").query();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除所有信息
     */
    public void deleteall() {
        try {
            WorkOrderDaoOpe.delete(WorkOrderDaoOpe.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据工单id删除信息
     */
    public void deleteById(int id) {
        try {
            WorkOrderDaoOpe.delete(WorkOrderDaoOpe.queryBuilder().where().eq("id", id).query());
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
    public WorkOrder SearchByNum(int id) {
        try {
            return WorkOrderDaoOpe.queryBuilder().where().eq("id", id).queryForFirst();
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
            List<WorkOrder> orderMainList = WorkOrderDaoOpe.queryBuilder().where().eq("WONUM", num).and().eq("belong", username).query();
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
            List<WorkOrder> orderMainList = WorkOrderDaoOpe.queryBuilder().where().eq("WONUM", num).query();
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
