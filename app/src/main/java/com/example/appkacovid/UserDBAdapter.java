package com.example.appkacovid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDBAdapter extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_ID = "COLUMN_ID";
    public static final String COLUMN_LOGIN = "COLUMN_LOGIN";
    public static final String COLUMN_HASLO = "COLUMN_HASLO";

    public UserDBAdapter(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_LOGIN + " TEXT," + COLUMN_HASLO + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_LOGIN,user.getLogin());
        contentValues.put(COLUMN_HASLO,user.getHaslo());

        long result = database.insert(USER_TABLE,null,contentValues);
        return result == -1 ? false : true;
    }

    public Boolean getData(String login,String haslo){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER_TABLE WHERE COLUMN_LOGIN= ? AND COLUMN_HASLO = ?",new String[]{login,haslo});
        if (cursor.getCount() == 1){
            return true;
        }else
            return false;
    }


}
