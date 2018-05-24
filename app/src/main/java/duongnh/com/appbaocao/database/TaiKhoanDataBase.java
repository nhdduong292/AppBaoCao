package duongnh.com.appbaocao.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import duongnh.com.appbaocao.model.Note;
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
            ID + " NCHAR(36), " +
            TEN_DANG_NHAP + " TEXT, " +
            MATKHAU + " TEXT, " +
            TEN + " TEXT, " +
            TUOI + " TEXT, " +
            AVATAR + " BLOG NOT NULL)";

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

    public void addTaiKhoan(TaiKhoan t) {
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, t.getId());
        contentValues.put(TEN_DANG_NHAP, t.getTenDN());
        contentValues.put(MATKHAU, t.getMatKhau());
        contentValues.put(TEN, t.getTen());
        contentValues.put(TUOI, t.getTuoi());
        contentValues.put(AVATAR, t.getAvatar());
        db.insert(TABLE_NAME, null, contentValues);
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
    public List<TaiKhoan> getAllNote() {
        ArrayList<TaiKhoan> arr = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        db = getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                TaiKhoan s = new TaiKhoan();
                s.setId(c.getString(0));
                s.setTenDN(c.getString(1));
                s.setMatKhau(c.getString(2));
                arr.add(s);
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }
    public TaiKhoan getTaiKhoan(String studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, TEN_DANG_NHAP + " = ?", new String[] { studentId },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        TaiKhoan student = new TaiKhoan(cursor.getString(0), cursor.getString(1),
                cursor.getString(2));
        return student;
    }
    public TaiKhoan getTaiKhoanFull(String studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, TEN_DANG_NHAP + " = ?", new String[] { studentId },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        TaiKhoan student = new TaiKhoan(cursor.getString(0), cursor.getString(1),
                cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getBlob(5));
        return student;
    }

    public void updateTaiKhoan(TaiKhoan contact) {
        db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(TEN_DANG_NHAP, contact.getTenDN());
        value.put(MATKHAU, contact.getMatKhau());
        value.put(TEN, contact.getTen());
        value.put(TUOI, contact.getTuoi());
        value.put(AVATAR, contact.getAvatar());
        db.update(TABLE_NAME, value, ID + "=?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }
}
