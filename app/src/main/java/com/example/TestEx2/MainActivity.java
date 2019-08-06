package com.example.TestEx2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddDepartment.DepartmentDialogListener,
        RecyclerDepartmentAdapter.onDepartmentListener, DeleteDepartment.DeleteDialogListenerDepart, DepartmentsViewModel.OnGetAllDepartments {

    private DepartmentsViewModel departmentsViewModel;

    private List<Departments> departmentList = new ArrayList<>();
    private RecyclerDepartmentAdapter adapter;
    private int deleteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        departmentsViewModel = ViewModelProviders.of(this).get(DepartmentsViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerMain);

        adapter = new RecyclerDepartmentAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        departmentList = departmentsViewModel.getAllDepartments(this);
        adapter.setDepartment(departmentList);

        FloatingActionButton fab = findViewById(R.id.fabDep);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departmentDialog();
            }
        });
    }

    private void departmentDialog() {
        DialogFragment dialog = new AddDepartment();
        dialog.show(getSupportFragmentManager(), "Dialog");
    }


    @Override
    public void onDialogPositiveClick(Departments departments) {
        departmentsViewModel.insert(departments);
       // departmentsViewModel.getAllDepartments(this);
    }

    @Override
    public void onDepartmentClick(int id) {

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("departmentId", departmentList.get(id).getId());
        startActivity(intent);

    }

    private void departmentDeleteDialog() {
        DialogFragment dialog = new DeleteDepartment();
        dialog.show(getSupportFragmentManager(), "Delete department");
    }


    @Override
    public void onDepartmentLongClick(int id) {
        deleteId = id;
        departmentDeleteDialog();

    }


    @Override
    public void onPositiveClickDeletDep() {
        departmentsViewModel.delete(departmentList.get(deleteId));
    }

    @Override
    public void OnNegativeClickDeleteDEp() {

    }


    @Override
    public void onDialogNegativeClick() {

    }

    @Override
    public void onGetAllDepartments(List<Departments> departments) {
        adapter.submitList(departmentList);
    }
}
