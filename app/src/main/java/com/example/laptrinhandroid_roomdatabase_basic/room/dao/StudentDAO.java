package com.example.laptrinhandroid_roomdatabase_basic.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.laptrinhandroid_roomdatabase_basic.room.entity.Student;

import java.util.List;

@Dao
public interface StudentDAO {
    @Query("select * from student")
    List<Student> getAll();

    @Insert
    void insertAll(Student... students);

    @Delete
    void Delete(Student student);

}
