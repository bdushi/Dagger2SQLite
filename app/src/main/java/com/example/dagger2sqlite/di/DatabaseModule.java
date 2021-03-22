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
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

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
    public static String providesDatabaseName() {
        return "demo";
    }

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
    public static SQLiteOpenHelper providesDatabaseHelper(Context context, String databaseName) {
        return new SQLiteOpenHelper(context, databaseName, null, 1) {

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
    public static UserDao provideUserDao(final SQLiteDatabase sqLite, final  AppExecutors appExecutors) {
        return new UserDao() {
            @Override
            public Maybe<Long> insertUser(final User user) {
                return Maybe.create(new MaybeOnSubscribe<Long>() {
                    @Override
                    public void subscribe(MaybeEmitter<Long> e) throws Exception {
                        e.onSuccess(bindUser(sqLite.compileStatement(INSERT_USER), user));
                    }
                });
            }

            @Override
            public Observable<Long> insertUsers(final User... users) {
                return Observable.create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> e) throws Exception {
                        for(User user: users) {
                            e.onNext(bindUser(sqLite.compileStatement(INSERT_USER), user));
                        }
                    }
                });
            }

            @Override
            public Observable<Long> insertUsers(final List<User> users) {
                return Observable.create(new ObservableOnSubscribe<Long>() {
                    @Override
                    public void subscribe(ObservableEmitter<Long> e) throws Exception {
                        for(User user: users) {
                            e.onNext(bindUser(sqLite.compileStatement(INSERT_USER), user));
                        }
                    }
                });
            }

            @Override
            public Single<Integer> deleteUser(final int id) {
                return Single.create(new SingleOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(final SingleEmitter<Integer> e) throws Exception {
                        appExecutors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                sqLite.beginTransaction();
                                try {
                                    e.onSuccess(delete(sqLite, id));
                                    sqLite.setTransactionSuccessful();
                                } finally {
                                    sqLite.endTransaction();
                                }
                            }
                        });
                    }
                });
                /*return Single.fromCallable(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        sqLite.beginTransaction();
                        try {
                            int result = delete(sqLite, id);
                            sqLite.setTransactionSuccessful();
                            return result;
                        } finally {
                            sqLite.endTransaction();
                        }
                    }
                });*/
            }

            @Override
            public Single<Integer> updateUser(final User user) {
                return Single.create(new SingleOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(final SingleEmitter<Integer> e) {
                        appExecutors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                e.onSuccess(update(sqLite, user));
                            }
                        });
                    }
                });
            }

            @Override
            public Single<List<User>> users() {
                return Single.create(new SingleOnSubscribe<List<User>>() {
                    @Override
                    public void subscribe(final SingleEmitter<List<User>> e) throws Exception {
                        appExecutors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                sqLite.beginTransaction();
                                try {
                                    e.onSuccess(UserHelper.users(sqLite));
                                    sqLite.setTransactionSuccessful();
                                } catch (Exception ex) {
                                    e.onError(ex);
                                } finally {
                                    sqLite.endTransaction();
                                }
                            }
                        });
                    }
                });
            }

            @Override
            public Single<User> getUser(final int id) {
                return Single.create(new SingleOnSubscribe<User>() {
                    @Override
                    public void subscribe(final SingleEmitter<User> e) {
                        appExecutors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                e.onSuccess(user(sqLite, id));
                            }
                        });
                    }
                });
            }
        };
    }
}
