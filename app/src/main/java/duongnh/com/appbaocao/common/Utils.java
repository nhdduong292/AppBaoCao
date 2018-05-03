package duongnh.com.appbaocao.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Admin on 4/19/2018.
 */

public class Utils {
    public static void setSharePreValue(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Duongtaikhoan",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public static String getSharePreValue(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Duongtaikhoan",
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }

    public static void setUser(Context mContext, String key, String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Duongtaikhoan",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public static String getUser(Context mContext, String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("Duongtaikhoan",
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }
}
