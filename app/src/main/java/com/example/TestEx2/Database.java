package com.example.TestEx2;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {Departments.class, Employee.class}, version = 2, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract DepartmentsDao departmentsDao();

    public abstract EmployeeDao employeeDao();

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "department_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(instance).execute();

        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private DepartmentsDao departmentsDao;
        private EmployeeDao employeeDao;

        private PopulateDbAsyncTask(Database db) {
            departmentsDao = db.departmentsDao();
            employeeDao = db.employeeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            departmentsDao.addDepartnebts(new Departments("depName", "depDescription", "room"));
            employeeDao.addEmployee(new Employee(1, "firstName", "surname", "year", "position"));
            return null;
        }
    }
}
