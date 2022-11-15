package com.example.de2_ktgk_sqlite.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.de2_ktgk_sqlite.Model.BaiHat;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "songs_manager";
    private static final String TABLE_NAME = "songs";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String SINGER = "singer";
    private static final String TIME = "time";
    private static int VERSION = 1;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    private String SQLQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            SINGER + " TEXT, " +
            TIME + " TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addSong(BaiHat baiHat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, baiHat.getmName());
        values.put(SINGER, baiHat.getmSinger());
        values.put(TIME, baiHat.getmTime());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<BaiHat> getAllSong() {
        List<BaiHat> Listsong = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BaiHat student = new BaiHat();
                student.setMid(cursor.getInt(0));
                student.setmName(cursor.getString(1));
                student.setmSinger(cursor.getString(2));
                student.setmTime(cursor.getString(3));
                Listsong.add(student);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return Listsong;
    }

    public int updateSong(BaiHat baiHat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, baiHat.getmName());
        contentValues.put(SINGER, baiHat.getmSinger());
        contentValues.put(TIME, baiHat.getmTime());
        String where = ID + " = " + baiHat.getMid();
        return db.update(TABLE_NAME, contentValues, where, null);
    }

    public int DeleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = ID + " = " + id;
        return db.delete(TABLE_NAME, where, null);
    }

}
