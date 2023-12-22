package com.example.ontapandroid2_4.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    static String DB_NAME = "ql_dv";
    static int VERSION = 1;

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String dichvu = "CREATE TABLE dichvu (\n" +
                "    madv      INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                      NOT NULL,\n" +
                "    noidung   TEXT    NOT NULL,\n" +
                "    ngay      TEXT    NOT NULL,\n" +
                "    thanhtien INTEGER NOT NULL\n" +
                ")";
        db.execSQL(dichvu);

        String insert = "Insert into dichvu(noidung, ngay, thanhtien)\n" +
                "values ('Thi ho', '12/12/2023', 100), ('Gui xe', '11/12/2023', 200);";
        db.execSQL(insert);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
