package me.danwi.utils;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataBaseContext dataBaseContext = new DataBaseContext(this, Environment.getExternalStorageDirectory().getPath() + "/database");
        BaseSqliteOpenHelper baseSqliteOpenHelper = new BaseSqliteOpenHelper(dataBaseContext, "data.db", null, 2);

        BaseSqliteOpenHelper baseSqliteOpenHelper1 = new BaseSqliteOpenHelper(dataBaseContext, "data.db", null, 2);

        SQLiteDatabase sqLiteDatabase = baseSqliteOpenHelper.getWritableDatabase();

        SQLiteDatabase sqLiteDatabase1 = baseSqliteOpenHelper1.getWritableDatabase();

        String sql = "insert into users(name,age) values(?,?)";
        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
        sqLiteDatabase.beginTransaction();

        ContentValues contentValues = new ContentValues();

        long first = System.currentTimeMillis();
        try {
            for (int i = 0; i < 10000; i++) {
                sqLiteStatement.clearBindings();
                sqLiteStatement.bindString(1, "hello");
                sqLiteStatement.bindLong(2, i);
                sqLiteStatement.executeInsert();
//                contentValues.clear();
//                contentValues.put("name", "ss");
//                contentValues.put("age", 33);
//                sqLiteDatabase.insert("users", null, contentValues);
            }
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        } finally {
            sqLiteDatabase.endTransaction();
//            sqLiteDatabase.close();
        }
        Log.i("tag", System.currentTimeMillis() - first + "");

//        Cursor cursor = sqLiteDatabase.query("users", null, null, null, null, null, null);
//        if (cursor.moveToFirst()) {
//            do {
//                String name = cursor.getString(cursor.getColumnIndex("name"));
//                Log.i("tag", name);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
    }

}
