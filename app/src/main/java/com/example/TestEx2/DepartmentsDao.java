package com.example.TestEx2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DepartmentsDao {
    @Insert
    void addDepartnebts(Departments departments);

    @Update
    void update(Departments departments);

    @Delete
    void delete(Departments departments);

    @Query("SELECT * FROM departments_table")
    List<Departments> getAllDepartments();

}
