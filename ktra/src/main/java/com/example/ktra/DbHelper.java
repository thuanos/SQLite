package com.example.ktra;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, "QLSanPham", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE SanPham(MaSP INTEGER PRIMARY KEY AUTOINCREMENT, " + "TenSP TEXT, GiaTien REAL, Image TEXT)";
        db.execSQL(sql);
        String data = "INSERT INTO SanPham VALUES(1, 'Com chien', '300', 'https://www.tastefullyeclectic.com/wp-content/uploads/2017/06/Copycat-Berry-Almond-Salad.jpg')," +
                "(2, 'Mi goi', '100', 'https://www.advrider.com/wp-content/uploads/2021/03/20210303_160517-1200x1137.jpg')," +
                "(4, 'Pasta', '200', 'https://www.advrider.com/wp-content/uploads/2021/03/20210303_160517-1200x1137.jpg')," +
                "(3, 'Pizza', '150', 'https://wallpaperaccess.com/full/2237015.jpg')";
        db.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1){
            db.execSQL("DROP TABLE IF EXISTS SanPham");
            onCreate(db);
        }
    }
}

