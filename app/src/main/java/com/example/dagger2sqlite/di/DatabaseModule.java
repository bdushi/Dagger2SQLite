package com.example.dagger2sqlite.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;

import com.example.dagger2sqlite.AppExecutors;
import com.example.dagger2sqlite.database.UserDao;
import com.example.dagger2sqlite.database.UserDataSource;
import com.example.dagger2sqlite.database.UserHelper;
import com.example.dagger2sqlite.database.UserLocalDataSource;
import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import static com.example.dagger2sqlite.database.UserHelper.CREATE_USER_TABLE;
import static com.example.dagger2sqlite.database.UserHelper.INSERT_USER;
import static com.example.dagger2sqlite.database.UserHelper.bindUser;
import static com.example.dagger2sqlite.database.UserHelper.delete;
import static com.example.dagger2sqlite.database.UserHelper.update;
import static com.example.dagger2sqlite.database.UserHelper.user;

@Module
public abstract class DatabaseModule {

    @Singleton
    @Binds
    public abstract UserDataSource provideUserLocalDataSource(UserLocalDataSource dataSource);

    @Provides
    @Singleton
    public static SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    public static AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    @Provides
    @Singleton
    public static SQLiteDatabase providesSQLiteDatabase(SQLiteOpenHelper sqLiteOpenHelper) {
        return sqLiteOpenHelper.getWritableDatabase();
    }

    /*@Singleton
    @Provides
    public static User provideUser(UserLocalDataSource localDataSource) {
        return localDataSource.getUser(1);
    }*/


    @Provides
    @Singleton
    public static SQLiteOpenHelper providesDatabaseHelper(Context context) {
        return new SQLiteOpenHelper(context, "demo", null, 1) {

            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(CREATE_USER_TABLE);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                /*if(oldVersion < 3) {
                    db.execSQL(CREATE_USER_TABLE_TEMP);
                    db.execSQL("INSERT INTO user_temp SELECT " +
                            "_username, " +
                            "'Bruno' AS _description, " +
                            "'bruno' AS _password, " +
                            "'hash' AS _hash, " +
                            "'bruno' AS _sales_person, " +
                            "1 AS _line_discount, " +
                            "1 AS _invoice_discount, " +
                            "1 AS _profile, " +
                            "1 AS _not_active, " +
                            "1 AS _web, " +
                            "1 AS _not_active, " +
                            "'12-12-2012' AS _creation_date, " +
                            "1 AS _delete_status, " +
                            "1 AS _edit_status, " +
                            "1 AS _sales_order, " +
                            "1 AS _sales_invoice, " +
                            "1 AS _customer, " +
                            "1 AS _inventory, " +
                            "'bruno@bruno' AS _email " +
                            "FROM user");
                    db.execSQL("DROP TABLE user");
                    db.execSQL("ALTER TABLE user_temp RENAME TO user");
                }*/
            }
        };
    }

    @Singleton
    @Provides
    public static UserDao provideUserDao(final SQLiteDatabase sqLiteDatabase) {
        return new UserDao() {
            @Override
            public long insertUser(final User user) {
                return bindUser(sqLiteDatabase.compileStatement(INSERT_USER), user);
            }

            @Override
            public long insertUsers(User... users) {
                long id = 0;
                for(User user: users) {
                    id = bindUser(sqLiteDatabase.compileStatement(INSERT_USER), user);
                }
                return id;
            }

            @Override
            public long insertUsers(List<User> users) {
                long id = 0;
                for(User user: users) {
                    id = bindUser(sqLiteDatabase.compileStatement(INSERT_USER), user);
                }
                return id;
            }

            @Override
            public void deleteUser(int id) {
                delete(sqLiteDatabase, id);
            }

            @Override
            public void updateUser(User user) {
                update(sqLiteDatabase, user);
            }

            @Override
            public List<User> users() {
                return UserHelper.users(sqLiteDatabase);
            }

            @Override
            public User getUser(int id) {
                return user(sqLiteDatabase, id);
            }
        };
    }
}
