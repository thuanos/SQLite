package com.example.ktra;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;

public class DAO {
    private final DbHelper dbHelper;
    public DAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<sanPham> getListSanPham() {
        ArrayList<sanPham> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM SanPham", null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    list.add(new sanPham(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getFloat(2),
                            cursor.getString(3)));
                } while (cursor.moveToNext());
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            Log.e(TAG, "getListSanPham" + e);
        } finally {
            database.endTransaction();
        }
        return list;
    }

    public boolean deleteSP(int MaSP) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int affectedRows = db.delete("SanPham", "MaSP=?", new String[]{String.valueOf(MaSP)});
        return affectedRows > 0;
    }
}