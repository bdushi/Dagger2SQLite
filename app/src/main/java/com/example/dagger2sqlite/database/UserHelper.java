package com.example.dagger2sqlite.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dagger2sqlite.model.User;

import java.util.ArrayList;
import java.util.List;

public abstract class UserHelper {
    private static final String ID = "_id";
    private static final String USERNAME = "_username";

    private static final String USER = "user";

    public static final String CREATE_USER_TABLE = "CREATE TABLE " + USER + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + USERNAME + " TEXT"
            + ")";

    public static long insert(SQLiteDatabase sqLite, User user) {
        return sqLite.replace(USER, null, userToContent(user));
    }

    public static long delete(SQLiteDatabase sqLite, long id) {
        return sqLite.delete(USER, "id=?", new String[]{String.valueOf(id)});
    }

    public static long update(SQLiteDatabase sqLite, User user) {
        return sqLite.update(USER, userToContent(user),"id=?", new String[]{String.valueOf(user.getId())});
    }

    public static User user(SQLiteDatabase sqLite, int id) {
        User user = null;
        Cursor cursor = sqLite.rawQuery("SELECT * FROM " + USER + " WHERE " + ID + "=? ORDER BY " + USERNAME + " DESC LIMIT 1", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            user = cursorToUser(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return user;
    }

    public static List<User> users(SQLiteDatabase sqLite) {
        List<User> users = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM " + USER, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            users.add(cursorToUser(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }

    public static void inserts(SQLiteDatabase sqLite, User... users) {
        for(User user : users) {
            sqLite.replace(USER, null, userToContent(user));
        }
    }

    private static ContentValues userToContent(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, user.getUsername());
        return contentValues;
    }

    private static User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(cursor.getColumnIndex(ID)));
        user.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME)));
        return user;
    }
}
