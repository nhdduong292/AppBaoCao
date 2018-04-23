package duongnh.com.appbaocao.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import duongnh.com.appbaocao.model.Note;


/**
 * Created by Admin on 4/23/2018.
 */

public class NoteDataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "duongnote";
    public static final String TABLE_NAME = "ghichu";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CONTENT = "content";
    public static final String TIME = "time";
    private List<Note> arr;
    private SQLiteDatabase sql;
    private String query = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            NAME + " TEXT, " +
            CONTENT + " TEXT, " +
            TIME + " TEXT)";

    public NoteDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addNote(Note t){
        sql = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, t.getName());
        contentValues.put(CONTENT, t.getContent());
        contentValues.put(TIME, t.getTime());
        sql.insert(TABLE_NAME, null,contentValues);
        sql.close();
    }
    public List<Note> getAllNote() {
        arr = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        sql = getWritableDatabase();
        Cursor c = sql.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Note s = new Note();
                s.setId(c.getInt(0));
                s.setName(c.getString(1));
                s.setContent(c.getString(2));
                s.setTime(c.getString(3));
                arr.add(s);
            } while (c.moveToNext());
        }
        sql.close();
        return arr;
    }
    public int deleteNote(int id) {
        sql = getWritableDatabase();
        return sql.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }
}
