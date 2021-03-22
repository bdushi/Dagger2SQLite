package com.example.dagger2sqlite.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.dagger2sqlite.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.example.dagger2sqlite.common.Common.boolToInt;
import static com.example.dagger2sqlite.common.Common.intToBool;

public abstract class UserHelper {
    private static final String ID = "_id";
    private static final String USERNAME = "_username";
    private static final String DESCRIPTION = "_description";
    private static final String PASSWORD = "_password";
    private static final String HASH = "_hash";
    private static final String SALES_PERSON = "_sales_person";
    private static final String LINE_DISCOUNT = "_line_discount";
    private static final String INVOICE_DISCOUNT = "_invoice_discount";
    private static final String NOT_ACTIVE = "_not_active";
    private static final String PROFILE = "_profile";
    private static final String WEB = "_web";
    private static final String CREATION_DATE = "_creation_date";
    private static final String DELETE_STATUS = "_delete_status";
    private static final String EDIT_STATUS = "_edit_status";
    private static final String SALES_ORDER = "_sales_order";
    private static final String SALES_INVOICE = "_sales_invoice";
    private static final String CUSTOMER = "_customer";
    private static final String INVENTORY = "_inventory";
    private static final String EMAIL = "_email";

    private static final String USER = "user";


    public static final String CREATE_USER_TABLE = "CREATE TABLE " + USER + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + USERNAME + " TEXT,"
            + DESCRIPTION + " TEXT,"
            + PASSWORD + " TEXT,"
            + HASH + " TEXT,"
            + SALES_PERSON + " TEXT,"
            + LINE_DISCOUNT + " INTEGER,"
            + INVOICE_DISCOUNT + " TEXT,"
            + NOT_ACTIVE + " INTEGER,"
            + PROFILE + " INTEGER,"
            + WEB + " INTEGER,"
            + CREATION_DATE + " TEXT,"
            + DELETE_STATUS + " INTEGER,"
            + EDIT_STATUS + " INTEGER,"
            + SALES_ORDER + " INTEGER,"
            + SALES_INVOICE + " INTEGER,"
            + CUSTOMER + " INTEGER,"
            + INVENTORY + " INTEGER,"
            + EMAIL + " TEXT"
            + ")";

    public static int delete(SQLiteDatabase sqLite, long id) {
        return sqLite.delete(USER, "id=?", new String[]{String.valueOf(id)});
    }

    public static int update(SQLiteDatabase sqLite, User user) {
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

    private static ContentValues userToContent(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, user.getUsername());
        contentValues.put(DESCRIPTION, user.getDescription());
        contentValues.put(PASSWORD, user.getPassword());
        contentValues.put(HASH, user.getHash());
        contentValues.put(SALES_PERSON, user.getSalesPersonCode());
        contentValues.put(LINE_DISCOUNT, user.isLineDiscount());
        contentValues.put(INVOICE_DISCOUNT, user.isInvoiceDiscount());
        contentValues.put(NOT_ACTIVE, user.isNotActive());
        contentValues.put(PROFILE, user.getProfile());
        contentValues.put(WEB, user.isWeb());
        contentValues.put(CREATION_DATE, user.getCreationDate());
        contentValues.put(DELETE_STATUS, user.isDeleteStatus());
        contentValues.put(EDIT_STATUS, user.isEditStatus());
        contentValues.put(SALES_ORDER, user.isSalesOrder());
        contentValues.put(SALES_INVOICE, user.isSalesInvoice());
        contentValues.put(CUSTOMER, user.isCustomer());
        contentValues.put(INVENTORY, user.isInventory());
        contentValues.put(EMAIL, user.getEmail());
        return contentValues;
    }

    private static User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(cursor.getColumnIndex(ID)));
        user.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME)));
        user.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
        user.setHash(cursor.getString(cursor.getColumnIndex(HASH)));
        user.setSalesPersonCode(cursor.getString(cursor.getColumnIndex(SALES_PERSON)));
        user.setLineDiscount(intToBool(cursor.getInt(cursor.getColumnIndex(LINE_DISCOUNT))));
        user.setInvoiceDiscount(intToBool(cursor.getInt(cursor.getColumnIndex(INVOICE_DISCOUNT))));
        user.setNotActive(intToBool(cursor.getInt(cursor.getColumnIndex(NOT_ACTIVE))));
        user.setProfile(cursor.getInt(cursor.getColumnIndex(PROFILE)));
        user.setWeb(intToBool(cursor.getInt(cursor.getColumnIndex(WEB))));
        user.setCreationDate(cursor.getString(cursor.getColumnIndex(CREATION_DATE)));
        user.setDeleteStatus(intToBool(cursor.getInt(cursor.getColumnIndex(DELETE_STATUS))));
        user.setEditStatus(intToBool(cursor.getInt(cursor.getColumnIndex(EDIT_STATUS))));
        user.setSalesOrder(intToBool(cursor.getInt(cursor.getColumnIndex(SALES_ORDER))));
        user.setSalesInvoice(intToBool(cursor.getInt(cursor.getColumnIndex(SALES_INVOICE))));
        user.setCustomer(intToBool(cursor.getInt(cursor.getColumnIndex(CUSTOMER))));
        user.setInventory(intToBool(cursor.getInt(cursor.getColumnIndex(INVENTORY))));
        user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
        return user;
    }

    public static final String INSERT_USER = "INSERT INTO " + USER + "("
            + DESCRIPTION + ", "
            + USERNAME + ", "
            + PASSWORD + ", "
            + HASH + ", "
            + SALES_PERSON + ", "
            + LINE_DISCOUNT + ", "
            + INVOICE_DISCOUNT + ", "
            + NOT_ACTIVE + ", "
            + PROFILE + ", "
            + WEB + ", "
            + CREATION_DATE + ", "
            + DELETE_STATUS + ", "
            + EDIT_STATUS + ", "
            + SALES_ORDER + ", "
            + SALES_INVOICE + ", "
            + CUSTOMER + ", "
            + INVENTORY + ", "
            + EMAIL +
            ")"
            + " VALUES(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17, ?18)";

    public static long bindUser(SQLiteStatement sqLiteStatement, User user) {
        sqLiteStatement.bindString(1, user.getDescription());
        sqLiteStatement.bindString(2, user.getUsername());
        sqLiteStatement.bindString(3, user.getPassword());
        sqLiteStatement.bindString(4, user.getHash());
        sqLiteStatement.bindString(5, user.getSalesPersonCode());
        sqLiteStatement.bindLong(6, boolToInt(user.isLineDiscount()));
        sqLiteStatement.bindDouble(7, boolToInt(user.isInvoiceDiscount()));
        sqLiteStatement.bindLong(8, boolToInt(user.isNotActive()));
        sqLiteStatement.bindLong(9, user.getProfile());
        sqLiteStatement.bindDouble(10, boolToInt(user.isWeb()));
        sqLiteStatement.bindString(11, user.getCreationDate());
        sqLiteStatement.bindDouble(12, boolToInt(user.isDeleteStatus()));
        sqLiteStatement.bindLong(13, boolToInt(user.isEditStatus()));
        sqLiteStatement.bindLong(14, boolToInt(user.isSalesOrder()));
        sqLiteStatement.bindLong(15, boolToInt(user.isSalesInvoice()));
        sqLiteStatement.bindLong(16, boolToInt(user.isCustomer()));
        sqLiteStatement.bindLong(17, boolToInt(user.isInventory()));
        sqLiteStatement.bindString(18, user.getEmail());
        return sqLiteStatement.executeInsert();
    }
}
