package com.example.tanshuai.easy_http.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by RunningSnail on 16/6/14.
 */
public class DataBaseContext extends ContextWrapper {
    //db文件保存的路径
    private String path;

    private boolean isSdExist;

    public DataBaseContext(Context base, String path) {
        super(base);
        this.path = path;
        //判断是否存在sd卡
        isSdExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 将db文件保存在sd卡目录
     *
     * @param name
     * @return
     */
    @Override
    public File getDatabasePath(String name) {
        boolean flag = false;
        String dbPath = null;
        if (isSdExist) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
            }
            dbPath = path + "/" + name;
            File file = new File(dbPath);
            //标志文件是否创建成功
            if (!file.exists()) {
                try {
                    //创建db文件
                    flag = file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                flag = true;
            }
        }
        if (flag) {
            return new File(dbPath);
        }
        return super.getDatabasePath(name);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        if (isSdExist) {
            return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        }
        return super.openOrCreateDatabase(name, mode, factory);
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        if (isSdExist) {
            return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
        }
        return super.openOrCreateDatabase(name, mode, factory, errorHandler);
    }
}
