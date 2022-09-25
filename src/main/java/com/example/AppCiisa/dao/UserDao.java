package com.example.AppCiisa.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.AppCiisa.models.UserEntity;

@Dao
public interface UserDao {
    @Query("SELECT id, username, firstname, lastname, dateofbirth, height, email, password FROM user WHERE username = :username LIMIT 1")
    UserEntity findByUsername (String username);

    @Insert
    long insert(UserEntity user);
}
