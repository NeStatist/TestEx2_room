package com.example.TestEx2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerDepartmentAdapter  extends ListAdapter<Departments, RecyclerDepartmentAdapter.DepartmentItemViewHolder> {

    private onDepartmentListener onDepartmentGlobalListener;
    private List<Departments> departments = new ArrayList<>();

    protected RecyclerDepartmentAdapter(onDepartmentListener onDepartmentGlobalListener) {
        super(Departments.DIFF_CALLBACK);
        this.onDepartmentGlobalListener = onDepartmentGlobalListener;
    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    @NonNull
    @Override
    public DepartmentItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_department, parent, false);
        return new DepartmentItemViewHolder(view, onDepartmentGlobalListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentItemViewHolder holder, int position) {
        Departments currentDepartments = departments.get(position);

        if(departments != null) {
            holder.bindTo(currentDepartments);
        }
    }

    public void setDepartment(List<Departments> departments) {
        this.departments = departments;
        notifyDataSetChanged();
    }

    protected static class DepartmentItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView depName, depNumOfCab;
        onDepartmentListener onDepartmentLocalListener;

        public DepartmentItemViewHolder(View itemView, onDepartmentListener onDepartmentListener) {
            super(itemView);
            depName = itemView.findViewById(R.id.depName);
            depNumOfCab = itemView.findViewById(R.id.depNumOfCab);

            this.onDepartmentLocalListener = onDepartmentListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onDepartmentLocalListener.onDepartmentClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onDepartmentLocalListener.onDepartmentLongClick(getAdapterPosition());
            return true;
        }

        public void bindTo(Departments departments) {
            depName.setText(departments.getDepName());
            depNumOfCab.setText(departments.getRoom());
        }
    }



    public interface onDepartmentListener {
        void onDepartmentClick(int id);
        void onDepartmentLongClick(int id);
    }
}
