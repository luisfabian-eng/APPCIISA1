package com.example.AppCiisa.lib;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.AppCiisa.dao.BmiDao;
import com.example.AppCiisa.dao.UserDao;
import com.example.AppCiisa.models.BmiEntity;
import com.example.AppCiisa.models.UserEntity;
import com.example.AppCiisa.utils.Converters;

@Database(entities = {UserEntity.class, BmiEntity.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppLobsangBarrigaDatabase extends RoomDatabase {
    private static final String DB_NAME = "AppLobsangBarriga";
    private static AppLobsangBarrigaDatabase instance;

    public static synchronized AppLobsangBarrigaDatabase getInstance(Context ctx) {
        if (instance == null) {
            instance = Room.databaseBuilder(ctx.getApplicationContext(), AppLobsangBarrigaDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
    public abstract BmiDao bmiDao();
}
