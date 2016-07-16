package com.example.admin.mingyang_object.OrmLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.admin.mingyang_object.model.Udinspo;
import com.example.admin.mingyang_object.model.Udinsproject;
import com.example.admin.mingyang_object.model.Woactivity;
import com.example.admin.mingyang_object.model.WorkOrder;
import com.example.admin.mingyang_object.model.Wpmaterial;
import com.example.admin.mingyang_object.utils.Utils;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by think on 2016/7/12.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final int DATABASE_VERSION = 13;
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private DatabaseHelper(Context context) {
        super(context, Utils.getFilePath(context), null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, WorkOrder.class);
            TableUtils.createTable(connectionSource, Woactivity.class);
            TableUtils.createTable(connectionSource, Wpmaterial.class);
            TableUtils.createTable(connectionSource, Udinspo.class);
            TableUtils.createTable(connectionSource, Udinsproject.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, WorkOrder.class, true);
            TableUtils.dropTable(connectionSource, Woactivity.class, true);
            TableUtils.dropTable(connectionSource, Wpmaterial.class, true);
            TableUtils.dropTable(connectionSource, Udinspo.class, true);
            TableUtils.dropTable(connectionSource, Udinsproject.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DatabaseHelper instance;

    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null)
                    instance = new DatabaseHelper(context);
            }
        }

        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            try {
                dao = super.getDao(clazz);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();

        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}
