package com.example.studyplanner_200050013_200050130;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseClass extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "tasksDB";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "tasks";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our task name column
    private static final String NAME_COL = "name_of_task";

    // below variable id for our task date column.
    private static final String DATE_COL = "date_of_task";

    // below variable id for our task time column.
    private static final String TIME_COL = "time_of_task";

    // below variable id for our task ordering column.
    private static final String ORDER_COL = "ordering_of_task";

    // below variable for our task description column.
    private static final String DESCRIPTION_COL = "description";

    // below variable for our task type column.
    private static final String TYPE_COL = "type";

    // creating a constructor for our database handler.
    public DatabaseClass(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + TIME_COL + " TEXT,"
                + TYPE_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + ORDER_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new task to our sqlite database.
    public void addNewTask(String taskName, String taskDate, String taskDescription, String taskTime, String taskType) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, taskName);
        values.put(DATE_COL, taskDate);
        values.put(DESCRIPTION_COL, taskDescription);
        values.put(TYPE_COL, taskType);
        values.put(TIME_COL, taskTime);

        try {
            Date date=new SimpleDateFormat("dd/MM/yyyy").parse(taskDate);
            Long ordering_value = date.getTime();
            String[] time = taskTime.split(":");
            ordering_value += Long.parseLong(time[0])*3600000+Long.parseLong(time[1])*60000;
            values.put(ORDER_COL, ordering_value);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // we have created a new method for reading all the tasks.
    public ArrayList<TasksModel> readTasks() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorTasks = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY "+ORDER_COL+ " ASC", null);

        // on below line we are creating a new array list.
        ArrayList<TasksModel> tasksModelArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorTasks.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                tasksModelArrayList.add(new TasksModel(cursorTasks.getString(1),
                        cursorTasks.getString(5),
                        cursorTasks.getString(2),
                        cursorTasks.getString(3),
                        cursorTasks.getString(4)));
            } while (cursorTasks.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorTasks.close();
        return tasksModelArrayList;
    }

    public ArrayList<TasksModel> readTasks(String type) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorTasks = db.rawQuery("SELECT * FROM " + TABLE_NAME+ " WHERE "+ TYPE_COL +" = ? ORDER BY "+ORDER_COL+ " ASC", new String[]{type});

        // on below line we are creating a new array list.
        ArrayList<TasksModel> tasksModelArrayList = new ArrayList<>();
        // moving our cursor to first position.
        if (cursorTasks.moveToFirst()) {
            do {

                // 1 title
                // 2 date
                // 3 time
                // 4 type
                // 5 description

                tasksModelArrayList.add(new TasksModel(cursorTasks.getString(1),
                        cursorTasks.getString(5),
                        cursorTasks.getString(2),
                        cursorTasks.getString(3),
                        cursorTasks.getString(4)));

            } while (cursorTasks.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorTasks.close();
        return tasksModelArrayList;
    }


    public ArrayList<TasksModel> readTasks(String type, String date) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorTasks = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+ DATE_COL +" = ? AND "+TYPE_COL+" = ? ORDER BY "+ORDER_COL+ " ASC", new String[]{date, type});

        // on below line we are creating a new array list.
        ArrayList<TasksModel> tasksModelArrayList = new ArrayList<>();
        // moving our cursor to first position.
        if (cursorTasks.moveToFirst()) {
            do {

                // on below line we are adding the data from cursor to our array list.

                // 1 title
                // 2 date
                // 3 time
                // 4 type
                // 5 description

                tasksModelArrayList.add(new TasksModel(cursorTasks.getString(1),
                        cursorTasks.getString(5),
                        cursorTasks.getString(2),
                        cursorTasks.getString(3),
                        cursorTasks.getString(4)));

            } while (cursorTasks.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorTasks.close();
        return tasksModelArrayList;
    }

    // below is the method for updating our courses
    public void updateTask(String originalTaskName,String taskName, String taskDate, String taskDescription, String taskTime, String taskType) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, taskName);
        values.put(DATE_COL, taskDate);
        values.put(DESCRIPTION_COL, taskDescription);
        values.put(TYPE_COL, taskType);
        values.put(TIME_COL, taskTime);

        try {
            Date date=new SimpleDateFormat("dd/MM/yyyy").parse(taskDate);
            Long ordering_value = date.getTime();
            String[] time = taskTime.split(":");
            ordering_value += Long.parseLong(time[0])*3600000+Long.parseLong(time[1])*60000;
            values.put(ORDER_COL, ordering_value);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our task which is stored in original name variable.
        db.update(TABLE_NAME, values, ""+NAME_COL+"=?", new String[]{originalTaskName});
        db.close();
    }


    // below is the method for deleting our course.
    public void deleteCourse(String taskName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete
        db.delete(TABLE_NAME, ""+NAME_COL+"=?", new String[]{taskName});
        db.close();
    }


}
