package com.example.TestEx2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

    private EmployeeRepository repository;

    private List<Employee> allEmployee;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);

        repository = new EmployeeRepository(application);
        allEmployee = repository.getAllEmployee();
    }
    public void insert(Employee employee){
        repository.insert(employee);
    }
    public void update(Employee employee){
        repository.update(employee);
    }
    public void delete(Employee employee){
        repository.delete(employee);
    }
    public List<Employee> getAllEmployee(){
        return allEmployee;
    }
}
