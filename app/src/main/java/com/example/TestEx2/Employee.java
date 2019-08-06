package com.example.TestEx2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "employee_table")
public class Employee {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int departmentId;
    private String firstName;
    private String surname;
    private String year;
    private String position;

    public Employee() {}
    @Ignore
    public Employee(int departmentId, String firstName, String surname, String year, String position) {
        this.departmentId = departmentId;
        this.firstName = firstName;
        this.surname = surname;
        this.year = year;
        this.position = position;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static DiffUtil.ItemCallback<Employee> DIFF_CALLBACK = new DiffUtil.ItemCallback<Employee>() {
        @Override
        public boolean areItemsTheSame(@NonNull Employee item1, @NonNull Employee item2) {
            return item1.departmentId == item2.departmentId;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Employee item1, @NonNull Employee item2) {
            return item1.equals(item2);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }

        Employee employee = (Employee) obj;

        return employee.departmentId == this.departmentId && employee.firstName.equals(this.firstName) && employee.surname.equals(this.surname) && employee.year.equals(this.year) && employee.position.equals(this.position);
    }
}
