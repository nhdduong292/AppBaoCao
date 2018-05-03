package duongnh.com.appbaocao.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSet;

import duongnh.com.appbaocao.model.TaiKhoan;

/**
 * Created by Admin on 4/19/2018.
 */

public class TaiKhoanDataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "duong";
    public static final String TABLE_NAME = "taikhoan";
    public static final String ID = "id";
    public static final String TEN_DANG_NHAP = "tendangnhap";
    public static final String MATKHAU = "matkhau";
    public static final String TEN = "ten";
    public static final String TUOI = "tuoi";
    public static final String AVATAR = "avatar";
    private SQLiteDatabase db;
    private String query = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            TEN_DANG_NHAP + " TEXT, " +
            MATKHAU + " TEXT, " +
            TEN + " TEXT, " +
            TUOI + " TEXT, " +
            AVATAR + " TEXT)";
    public TaiKhoanDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addTaiKhoan(TaiKhoan t){
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEN_DANG_NHAP, t.getTenDN());
        contentValues.put(MATKHAU, t.getMatKhau());
        db.insert(TABLE_NAME, null,contentValues);
        db.close();
    }
    public boolean login(String username, String password) {
        String query = "select * from " + TABLE_NAME + " where tendangnhap like ? and matkhau like ?";
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }
    public boolean check_user(String username) {
        String query = "select * from " + TABLE_NAME + " where tendangnhap like ?";
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{username});
        if (cursor.moveToFirst()) {
            return false;
        }
        return true;
    }
    public TaiKhoan findTK(String s){
        TaiKhoan t = null;
        String query = "select * from "+TABLE_NAME+" where tendangnhap like ?";
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String []{s});
        if (cursor.moveToFirst()){
            t = new TaiKhoan(cursor.getString(1),cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
        }
        return t;
    }
}
