package com.example.laptrinhandroid_roomdatabase_basic.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.laptrinhandroid_roomdatabase_basic.room.dao.StudentDAO;
import com.example.laptrinhandroid_roomdatabase_basic.room.entity.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class DatabaseRoom extends RoomDatabase {
    public abstract StudentDAO studentDAO();
}
