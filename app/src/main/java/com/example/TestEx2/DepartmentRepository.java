package com.example.TestEx2;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DepartmentRepository {
    private DepartmentsDao departmentsDao;
    private List<Departments> allDepartments;

    public DepartmentRepository(Application application) {
        Database database = Database.getInstance(application);
        departmentsDao = database.departmentsDao();
        try {
            allDepartments = new GetAllAsync(departmentsDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(Departments departments) {
        new InsertDepartmentsAsyncTask(departmentsDao).execute(departments);
    }

    public void update(Departments departments) {
        new UpdateDepartmentsAsyncTask(departmentsDao).execute(departments);
    }

    public void delete(Departments departments) {
        new DeleteDepartmentsAsyncTask(departmentsDao).execute(departments);
    }

    public List<Departments> getAllDepartmens() {
        return allDepartments;
    }

    private static class InsertDepartmentsAsyncTask extends AsyncTask<Departments, Void, Void> {
        private DepartmentsDao departmentsDao;

        private InsertDepartmentsAsyncTask(DepartmentsDao departmentsDao) {
            this.departmentsDao = departmentsDao;
        }

        @Override
        protected Void doInBackground(Departments... departments) {
            departmentsDao.addDepartnebts(departments[0]);
            return null;
        }
    }

    private static class DeleteDepartmentsAsyncTask extends AsyncTask<Departments, Void, Void> {
        private DepartmentsDao departmentsDao;

        private DeleteDepartmentsAsyncTask(DepartmentsDao departmentsDao) {
            this.departmentsDao = departmentsDao;
        }

        @Override
        protected Void doInBackground(Departments... departments) {
            departmentsDao.delete(departments[0]);
            return null;
        }
    }

    private static class UpdateDepartmentsAsyncTask extends AsyncTask<Departments, Void, Void> {
        private DepartmentsDao departmentsDao;

        private UpdateDepartmentsAsyncTask(DepartmentsDao departmentsDao) {
            this.departmentsDao = departmentsDao;
        }

        @Override
        protected Void doInBackground(Departments... departments) {
            departmentsDao.update(departments[0]);
            return null;
        }
    }

    private static class GetAllAsync extends AsyncTask<Void, Void, List<Departments>> {
        private DepartmentsDao departmentsDao;

        private GetAllAsync(DepartmentsDao departmentsDao) {
            this.departmentsDao = departmentsDao;
        }


        @Override
        protected List<Departments> doInBackground(Void... departments) {
            List<Departments> allDepartments = departmentsDao.getAllDepartments();
            return allDepartments;
        }
    }


}

