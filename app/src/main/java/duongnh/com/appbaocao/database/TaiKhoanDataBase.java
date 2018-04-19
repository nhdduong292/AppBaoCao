package duongnh.com.appbaocao.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private String query = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            TEN_DANG_NHAP + " TEXT, " +
            MATKHAU + " TEXT)";
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
        SQLiteDatabase sql = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEN_DANG_NHAP, t.getTenDN());
        contentValues.put(MATKHAU, t.getMatKhau());
        sql.insert(TABLE_NAME, null,contentValues);
        sql.close();
    }
    public boolean login(String username, String password) {
        String query = "select * from " + TABLE_NAME + " where tendangnhap like ? and matkhau like ?";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        if (cursor.moveToFirst()) {
            return true;
        }
        return false;
    }
    public boolean check_user(String username) {
        String query = "select * from " + TABLE_NAME + " where tendangnhap like ?";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{username});
        if (cursor.moveToFirst()) {
            return false;
        }
        return true;
    }
}
