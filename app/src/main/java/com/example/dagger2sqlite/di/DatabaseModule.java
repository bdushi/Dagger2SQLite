package com.example.dagger2sqlite.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dagger2sqlite.database.UserDao;
import com.example.dagger2sqlite.database.UserDataSource;
import com.example.dagger2sqlite.database.UserLocalDataSource;
import com.example.dagger2sqlite.model.User;

import java.util.List;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import static com.example.dagger2sqlite.database.UserHelper.CREATE_USER_TABLE;
import static com.example.dagger2sqlite.database.UserHelper.delete;
import static com.example.dagger2sqlite.database.UserHelper.insert;
import static com.example.dagger2sqlite.database.UserHelper.update;
import static com.example.dagger2sqlite.database.UserHelper.user;
import static com.example.dagger2sqlite.database.UserHelper.users;

@Module
public abstract class DatabaseModule {
    @Provides
    @Singleton
    public static SharedPreferences providesSharedPreferences(Context context) {
        return context.getSharedPreferences("demo_pref", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    public static SQLiteDatabase providesSQLiteDatabase(Context context) {
        return providesDatabaseHelper(context).getWritableDatabase();
    }

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

            }
        };
    }

    @Singleton
    @Binds
    public abstract UserDataSource provideUserLocalDataSource(UserLocalDataSource dataSource);


    @Singleton
    @Provides
    public static UserDao provideUserDao(final SQLiteOpenHelper db) {
        return new UserDao() {
            @Override
            public void insertUser(User user) {
                insert(db.getWritableDatabase(), user);
            }

            @Override
            public void deleteUser(int id) {
                delete(db.getWritableDatabase(), id);
            }

            @Override
            public void updateUser(User user) {
                update(db.getWritableDatabase(), user);
            }

            @Override
            public List<User> getUsers() {
                return users(db.getReadableDatabase());
            }

            @Override
            public User getUser(int id) {
                return user(db.getReadableDatabase(), id);
            }
        };
    }

}
