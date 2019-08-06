package com.example.TestEx2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert
    void addEmployee(Employee employee);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

    @Query("SELECT * FROM employee_table")
    List<Employee> getAllEmployee();

    @Query("SELECT * FROM employee_table" +
            " WHERE departmentId  = :id")
    List<Employee> employeesDepId(int id);


}
