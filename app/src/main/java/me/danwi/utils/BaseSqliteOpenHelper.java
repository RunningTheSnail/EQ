package me.danwi.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RunningSnail on 16/6/14.
 */
public class BaseSqliteOpenHelper extends SQLiteOpenHelper {
    private static final String USER = "create table users(" +
            "id integer primary key autoincrement," +
            "name text," +
            "age integer)";

    private static final String People = "create table people(" +
            "id integer primary key autoincrement," +
            "name text," +
            "age integer)";

    public BaseSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (newVersion) {
            case 2:
                db.execSQL(People);
        }
    }
}
