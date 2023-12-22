package com.example.ontapandroid2_4.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ontapandroid2_4.DTO.DichVuDTO;
import com.example.ontapandroid2_4.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class DichVuDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;

    public DichVuDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<DichVuDTO> getList() {
        ArrayList<DichVuDTO> list = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from dichvu", null);
        if (c.getCount()>0 && c != null) {
            c.moveToFirst();
            do {
                int madv = c.getInt(0);
                String noidung = c.getString(1);
                String ngay = c.getString(2);
                int thanhtien = c.getInt(3);
                DichVuDTO dto = new DichVuDTO(madv, noidung, ngay, thanhtien);
                list.add(dto);
            } while (c.moveToNext());
        }
        return list;
    }

    public int addRow(DichVuDTO dto) {
        ContentValues values = new ContentValues();
        values.put("noidung", dto.getNoidung());
        values.put("ngay", dto.getNgay());
        values.put("thanhtien", dto.getThanhtien());
        return (int) db.insert("dichvu", null, values);
    }

    public int updateRow(DichVuDTO dto) {
        ContentValues values = new ContentValues();
        values.put("noidung", dto.getNoidung());
        values.put("ngay", dto.getNgay());
        values.put("thanhtien", dto.getThanhtien());
        String[] dk = new String[]{String.valueOf(dto.getMadv())};
        return db.update("dichvu", values, "madv=?", dk);
    }

    public int deleteRow(DichVuDTO dto) {
        String[] dk = new String[]{String.valueOf(dto.getMadv())};
        return db.delete("dichvu", "madv=?", dk);
    }


}
