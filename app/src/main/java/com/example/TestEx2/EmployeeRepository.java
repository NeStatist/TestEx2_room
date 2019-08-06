package com.example.TestEx2;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EmployeeRepository {

    private EmployeeDao employeeDao;
    private List<Employee> allEmployee;

    public EmployeeRepository(Application application) {
        Database database = Database.getInstance(application);
        employeeDao = database.employeeDao();
        try {
            allEmployee = new GetAllEmplAsync(employeeDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(Employee employee) {
        new EmployeeRepository.InsertEmployeeAsyncTask(employeeDao).execute(employee);
    }

    public void update(Employee employee) {
        new EmployeeRepository.InsertEmployeeAsyncTask(employeeDao).execute(employee);
    }

    public void delete(Employee employee) {
        new EmployeeRepository.DeleteEmployeeAsyncTask(employeeDao).execute(employee);
    }

    public List<Employee> getEmpByDep(int depId) {
        return employeeDao.employeesDepId(depId);
    }

    public List<Employee> getAllEmployee() {
        return allEmployee;
    }

    private static class InsertEmployeeAsyncTask extends AsyncTask<Employee, Void, Void> {
        private EmployeeDao employeeDao;

        private InsertEmployeeAsyncTask(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDao.addEmployee(employees[0]);
            return null;
        }
    }

    private static class DeleteEmployeeAsyncTask extends AsyncTask<Employee, Void, Void> {
        private EmployeeDao employeeDao;

        private DeleteEmployeeAsyncTask(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDao.delete(employees[0]);
            return null;
        }
    }

    private static class UpdateEmployeeAsyncTask extends AsyncTask<Employee, Void, Void> {
        private EmployeeDao employeeDao;

        private UpdateEmployeeAsyncTask(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDao.update(employees[0]);
            return null;
        }
    }

    private static class GetAllEmplAsync extends AsyncTask<Void, Void, List<Employee>> {
        private EmployeeDao employeeDao;

        private GetAllEmplAsync(EmployeeDao employeeDao) {
            this.employeeDao = employeeDao;
        }


        @Override
        protected List<Employee> doInBackground(Void... employee) {
            List<Employee> allEmployee = employeeDao.getAllEmployee();
            return allEmployee;
        }
    }


}


