package me.danwi.utils;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DataBaseContext dataBaseContext = new DataBaseContext(this, Environment.getExternalStorageDirectory().getPath() + "/database");
        BaseSqliteOpenHelper baseSqliteOpenHelper = new BaseSqliteOpenHelper(dataBaseContext, "data.db", null, 2);
        SQLiteDatabase sqLiteDatabase = baseSqliteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "RunningSnail");
        contentValues.put("age", 20);
        sqLiteDatabase.insert("users", null, contentValues);
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
