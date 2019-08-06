package com.example.TestEx2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class DepartmentsViewModel extends AndroidViewModel {

    private DepartmentRepository repository;

    private List<Departments> allDepartments;

    public DepartmentsViewModel(@NonNull Application application) {
        super(application);
        repository = new DepartmentRepository(application);
        allDepartments = repository.getAllDepartmens();
    }

    public void insert(Departments departments){
        repository.insert(departments);
    }

    public void update(Departments departments){
        repository.update(departments);
    }

    public void delete(Departments departments){
        repository.delete(departments);
    }

    public List<Departments> getAllDepartments(OnGetAllDepartments listener){
        listener.onGetAllDepartments(allDepartments);
        return allDepartments;
    }

    public interface OnGetAllDepartments {
        void onGetAllDepartments(List<Departments> departments);
    }
}
