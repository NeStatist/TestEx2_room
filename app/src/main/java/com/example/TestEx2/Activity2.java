package com.example.TestEx2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity  implements AddEmployee.EmployeeDialogListener,
        RecyclerEmployeeAdapter.onEmployeeListener, DeleteEmployee.DeleteDialogListenerEmpl {

    private EmployeeViewModel employeeViewModel;

    private List<Employee> empList = new ArrayList<>();
    private RecyclerEmployeeAdapter adapter;
    private int depId;
    private TextView depDesc, depName;
    private int deleteId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        depId = getIntent().getIntExtra("employeeId", 0);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSecond);
        depDesc = findViewById(R.id.depDescription);
        depName = findViewById(R.id.depNameEmpl);

        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        adapter = new RecyclerEmployeeAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        empList = employeeViewModel.getAllEmployee();
        adapter.setEmployee(empList);

        FloatingActionButton fab = findViewById(R.id.fabEmployee);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeeDialog();
            }
        });
    }

    private void employeeDialog() {
        DialogFragment dialog = new AddEmployee();
        dialog.show(getSupportFragmentManager(), "Dialog");
    }

    @Override
    public void onDialogPositiveClick(Employee employee) {
        employeeViewModel.insert(employee);

    }

    private void employeeDialogDelete() {
        DialogFragment dialog = new DeleteEmployee();
        dialog.show(getSupportFragmentManager(), "Delete employee");
    }

    @Override
    public void onDialogNegativeClick() {

    }

    @Override
    public void onEmployeeClick(int id) {

    }

    @Override
    public void onEmployeeLongClick(int id) {
        deleteId = id;
        employeeDialogDelete();
    }


    @Override
    public void onPositiveClickDelete() {
        employeeViewModel.delete(empList.get(deleteId));
    }

    @Override
    public void onNegativeClickDelete() {

    }
}
