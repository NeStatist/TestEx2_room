package com.example.TestEx2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerEmployeeAdapter extends ListAdapter<Employee, RecyclerEmployeeAdapter.EmployeeItemViewHolder> {


    private RecyclerEmployeeAdapter.onEmployeeListener onEmployeeGlobalListener;
    private List<Employee> employee = new ArrayList<>();

    protected RecyclerEmployeeAdapter(onEmployeeListener onEmployeeGlobalListener) {
        super(Employee.DIFF_CALLBACK);
        this.onEmployeeGlobalListener = onEmployeeGlobalListener;
    }

    @Override
    public int getItemCount() {
        return employee.size();
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerEmployeeAdapter.EmployeeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_employee, parent, false);
        return new RecyclerEmployeeAdapter.EmployeeItemViewHolder(view, onEmployeeGlobalListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerEmployeeAdapter.EmployeeItemViewHolder holder, int position) {
        Employee currentEmployee = employee.get(position);
        if(employee != null) {
            holder.bindTo(currentEmployee);
        }
    }

    protected static class EmployeeItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView emplName, emplSurname, emplYear, emplSex;

        RecyclerEmployeeAdapter.onEmployeeListener onEmployeeLocalListener;

        public EmployeeItemViewHolder(View itemView, RecyclerEmployeeAdapter.onEmployeeListener onEmployeeLocalListener) {
            super(itemView);
            emplName = itemView.findViewById(R.id.empName);
            emplSurname = itemView.findViewById(R.id.emplLastName);
            emplYear = itemView.findViewById(R.id.emplYear);
            emplSex = itemView.findViewById(R.id.emplSex);

            this.onEmployeeLocalListener = onEmployeeLocalListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onEmployeeLocalListener.onEmployeeClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onEmployeeLocalListener.onEmployeeLongClick(getAdapterPosition());
            return true;
        }

        public void bindTo(Employee employee) {
            emplName.setText(employee.getFirstName());
            emplSurname.setText(employee.getSurname());
            emplYear.setText(employee.getYear());
            emplSex.setText(employee.getPosition());
        }
    }

    public interface onEmployeeListener {
        void onEmployeeClick(int id);
        void onEmployeeLongClick(int id);
    }
}
