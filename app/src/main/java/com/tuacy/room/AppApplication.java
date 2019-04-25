package com.tuacy.room;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.tuacy.room.database.AppDatabase;

public class AppApplication extends Application {

    private AppDatabase mAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "room.db")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build();
    }

    public AppDatabase getAppDatabase() {
        return mAppDatabase;
    }

    /**
     * 数据库版本 2->3 user表格新增了age列
     */
//    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE book ADD COLUMN age integer NOT NULL DEFAULT 10");
//        }
//    };

    /**
     * 数据库版本 1->2 新增book表格
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `book` (`uid` INTEGER PRIMARY KEY autoincrement, `name` TEXT , 'time' INTEGER, `userId` INTEGER)");
        }
    };

//    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL(
//                    "CREATE TABLE IF NOT EXISTS `mini` (`id` INTEGER PRIMARY KEY autoincrement, `name` TEXT , `icon` TEXT, 'desc' TEXT," +
//                            " 'url' TEXT,'time' DATE)"
//            );
//        }
//    };

//    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL(
//                    "CREATE TABLE IF NOT EXISTS 'student_list' ('content_' TEXT,'id' INTEGER PRIMARY KEY AUTOINCREMENT,'name_' TEXT,'age_' TEXT," +
//                            " 'ext_1' TEXT ,'user_uid' INTEGER,'user_name' TEXT,'user_address' TEXT,'user_phone' TEXT,'user_age' INTEGER)"
//            );
//        }
//    };
}
