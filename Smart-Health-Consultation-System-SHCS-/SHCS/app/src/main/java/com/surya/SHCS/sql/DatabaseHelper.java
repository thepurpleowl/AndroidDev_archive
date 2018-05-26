package com.surya.SHCS.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.surya.SHCS.model.Record;
import com.surya.SHCS.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_DOCTOR = "doctor";
    private static final String TABLE_HOSPITAL = "hospital";
    private static final String TABLE_PHARMACY = "pharmacy";
    private static final String TABLE_STOCK = "stock";
    private static final String TABLE_APPOINTMENT = "appointment";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_ADMIN = "admin";
    private static final String COLUMN_USER_TYPE = "user_type";
    private static final String COLUMN_USER_VERIFIED = "verified";
    private static final String COLUMN_USER_REVIEWED = "reviewed";
    private static final String COLUMN_USER_LOCATION = "user_loc";
    private static final String COLUMN_DOCTOR_LOCATION = "doc_loc";
    private static final String COLUMN_DOCTOR_HOSPITAL = "doc_hospital";
    private static final String COLUMN_DOCTOR_SPECIALITY = "doc_spec";
    private static final String COLUMN_HOSPITAL_LOCATION = "hospital_loc";
    private static final String COLUMN_PHARMACY_LOCATION = "pharmacy_loc";
    //table appointment
    private static final String COLUMN_P_ID = "p_id";
    private static final String COLUMN_PATIENT_ID = "patient_id";
    private static final String COLUMN_DOCTOR_ID = "doctor_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_PROBLEM = "problem";
    private static final String COLUMN_PRESCRIPTION = "prescription";
    private static final String COLUMN_APP_REQUESTED = "app_requested";
    private static final String COLUMN_APP_ACCEPTED = "app_accepted";
    private static final String COLUMN_APP_COMPLETE = "app_complete";
    private static final String COLUMN_PRESCRIPTION_AMT = "presc_amt";
    private static final String COLUMN_PRESCRIPTION_PAID = "presc_paid";
    private static final String COLUMN_PHARMACY_AMT = "pharm_amt";
    private static final String COLUMN_PHARMACY_PAID = "pharm_paid";
    private static final String COLUMN_SEND_CLAIM = "send_claim";
    private static final String COLUMN_RESCHEDULE= "reschedule";

    private static final String COLUMN_STOCK_NAME = "stock_name";
    private static final String COLUMN_STOCK_AMOUNT = "stock_amt";
    private static final String COLUMN_STOCK_COST = "stock_cost";

    //user type
    private static final int TYPE_PATIENT = 0;
    private static final int TYPE_DOCTOR = 1;
    private static final int TYPE_HOSPITAL = 2;
    private static final int TYPE_PHARMACY = 3;

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT,"
            + COLUMN_USER_TYPE  + " INTEGER DEFAULT 0," + COLUMN_USER_REVIEWED + " INTEGER DEFAULT 0,"
            + COLUMN_USER_ADMIN + " INTEGER DEFAULT 0,"
            + COLUMN_USER_VERIFIED + " INTEGER DEFAULT 0,"
            + COLUMN_USER_LOCATION + " TEXT"+")";

    private String CREATE_DOCTOR_TABLE = "CREATE TABLE " + TABLE_DOCTOR + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_DOCTOR_LOCATION + " TEXT,"+ COLUMN_DOCTOR_HOSPITAL + " TEXT,"
            + COLUMN_DOCTOR_SPECIALITY + " TEXT" + ")";

    private String CREATE_HOSPITAL_TABLE = "CREATE TABLE " + TABLE_HOSPITAL + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_HOSPITAL_LOCATION + " TEXT"  + ")";

    private String CREATE_PHARAMCY_TABLE = "CREATE TABLE " + TABLE_PHARMACY + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY," + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_PHARMACY_LOCATION + " TEXT"  + ")";

    private String CREATE_APPOINTMENT_TABLE = "CREATE TABLE " + TABLE_APPOINTMENT + "("
            + COLUMN_P_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COLUMN_DATE + " DATE,"
            + COLUMN_PATIENT_ID	 + " INTEGER," +COLUMN_DOCTOR_ID + " INTEGER,"
            + COLUMN_PROBLEM + " TEXT," +  COLUMN_PRESCRIPTION + " TEXT,"
            + COLUMN_APP_REQUESTED + " INTEGER DEFAULT 1,"+ COLUMN_APP_ACCEPTED +" INTEGER DEFAULT 0,"
            + COLUMN_APP_COMPLETE + " INTEGER DEFAULT 0,"
            + COLUMN_PRESCRIPTION_AMT + " INTEGER," + COLUMN_PRESCRIPTION_PAID + " INTEGER DEFAULT 0,"
            + COLUMN_PHARMACY_AMT + " INTEGER,"+ COLUMN_PHARMACY_PAID+ " INTEGER DEFAULT 0,"
            + COLUMN_RESCHEDULE + " INTEGER DEFAULT 0,"
            + COLUMN_SEND_CLAIM + " INTEGER DEFAULT 0"+ ")";


    private String CREATE_STOCK_TABLE =  "CREATE TABLE " + TABLE_STOCK + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY," + COLUMN_STOCK_NAME + " TEXT,"
            + COLUMN_STOCK_AMOUNT + " INTEGER DEFAULT 0,"
            + COLUMN_STOCK_COST + " INTEGER DEFAULT 0"  + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_DOCTOR_TABLE = "DROP TABLE IF EXISTS " + TABLE_DOCTOR;
    private String DROP_HOSPITAL_TABLE = "DROP TABLE IF EXISTS " + TABLE_HOSPITAL;
    private String DROP_PHARAMCY_TABLE = "DROP TABLE IF EXISTS " + TABLE_PHARMACY;
    private String DROP_APPOINTMENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_APPOINTMENT;
    private String DROP_STOCK_TABLE = "DROP TABLE IF EXISTS " + TABLE_STOCK;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_DOCTOR_TABLE);
        db.execSQL(CREATE_HOSPITAL_TABLE);
        db.execSQL(CREATE_PHARAMCY_TABLE);
        db.execSQL(CREATE_APPOINTMENT_TABLE);
        db.execSQL(CREATE_STOCK_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_DOCTOR_TABLE);
        db.execSQL(DROP_HOSPITAL_TABLE);
        db.execSQL(DROP_PHARAMCY_TABLE);
        db.execSQL(DROP_APPOINTMENT_TABLE);
        db.execSQL(DROP_STOCK_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_LOCATION, user.getLocation());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    public void addDoctor(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_LOCATION, user.getLocation());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_TYPE,TYPE_DOCTOR);

        // Inserting Row
        db.insert(TABLE_USER, null, values);

        int u_id = getUserId(user.getEmail());

//        SQLiteDatabase db2 = this.getWritableDatabase();

        values = new ContentValues();
        values.put(COLUMN_USER_ID,u_id);
        values.put(COLUMN_USER_NAME,user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_DOCTOR_LOCATION, user.getLocation());
        values.put(COLUMN_DOCTOR_SPECIALITY, user.getSpeciality());
        values.put(COLUMN_DOCTOR_HOSPITAL, user.getHospital());

        // Inserting Row
        db.insert(TABLE_DOCTOR, null, values);
        db.close();
    }

    public void addHospital(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_LOCATION, user.getLocation());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_TYPE,TYPE_HOSPITAL);

        // Inserting Row
        db.insert(TABLE_USER, null, values);

        int u_id = getUserId(user.getEmail());

//        SQLiteDatabase db2 = this.getWritableDatabase();

        values = new ContentValues();
        values.put(COLUMN_USER_ID,u_id);
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_HOSPITAL_LOCATION, user.getLocation());

        // Inserting Row
        db.insert(TABLE_HOSPITAL, null, values);
        db.close();
    }
    public void addPharamacy(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_LOCATION, user.getLocation());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_TYPE,TYPE_PHARMACY);

        // Inserting Row
        db.insert(TABLE_USER, null, values);

        int u_id = getUserId(user.getEmail());

//        SQLiteDatabase db2 = this.getWritableDatabase();

        values = new ContentValues();
        values.put(COLUMN_USER_ID,u_id);
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_PHARMACY_LOCATION, user.getLocation());

        // Inserting Row
        db.insert(TABLE_PHARMACY, null, values);
        db.close();
    }

    public void addAppointment(int p_id, int d_id, String problem, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_PATIENT_ID, p_id);
        values.put(COLUMN_DOCTOR_ID, d_id);
        values.put(COLUMN_PROBLEM,problem);

        // Inserting Row
        db.insert(TABLE_APPOINTMENT, null, values);
        db.close();
    }

    public void addAppointmentStatus(int p_id, int status) {
        String selection = COLUMN_P_ID + " = ?";
        String[] selectionArgs = {""+p_id};

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        if(status == 0){
            values.put(COLUMN_APP_ACCEPTED,0);
            values.put(COLUMN_RESCHEDULE,1);
        }
        else if(status == 1){
            values.put(COLUMN_APP_ACCEPTED,1);
            values.put(COLUMN_RESCHEDULE,1);
        }

        // Inserting Row
        db.update(TABLE_APPOINTMENT,values,selection,selectionArgs);
        db.close();
    }

    public void addPrescription(int p_id, String text, int amount) {
        String selection = COLUMN_P_ID + " = ?";
        String[] selectionArgs = {""+p_id};

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRESCRIPTION,text);
        values.put(COLUMN_APP_COMPLETE,"1");
        values.put(COLUMN_PRESCRIPTION_AMT,amount);

        // Inserting Row
        db.update(TABLE_APPOINTMENT,values,selection,selectionArgs);
        db.close();
    }

    public void acceptUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_VERIFIED, 1);
        values.put(COLUMN_USER_REVIEWED, 1);
        // Updating Row
        db.update(TABLE_USER,values,selection,selectionArgs);
        db.close();
    }

    public void declineUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_VERIFIED, 0);
        values.put(COLUMN_USER_REVIEWED, 1);
        // Updating Row
        db.update(TABLE_USER,values,selection,selectionArgs);
        db.close();
    }

    public void changePassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_PASSWORD, password);
        // Updating Row
        db.update(TABLE_USER,values,selection,selectionArgs);
        db.close();
    }

    public void updateStock(String email, String med_name, int med_amt, int med_cost) {
        String[] columns = {
                COLUMN_USER_ID
        };

        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order
        // Traversing through all rows and adding to list
        int user_id =-1;
        if (cursor.getCount() >0 && cursor.moveToFirst()) {
            user_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)));
        }
        cursor.close();
        db.close();

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, user_id);
        values.put(COLUMN_STOCK_NAME, med_name);
        values.put(COLUMN_STOCK_AMOUNT, med_amt);
        values.put(COLUMN_STOCK_COST,med_cost);

        // Inserting Row
        db.insert(TABLE_STOCK, null, values);
        db.close();
    }
    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        // selection criteria
        String selection = COLUMN_USER_VERIFIED + " = ?"+" and "+ COLUMN_USER_REVIEWED+" = ? ";

        // selection argument
        String[] selectionArgs = {"0","0"};

        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<User> getAllLocationUser(String email) {
        // array of columns to fetch
        int admin_id = getUserId(email);
        String admin_loc = getUserLocation(admin_id);
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        // selection criteria
        String selection = COLUMN_USER_VERIFIED + " = ?"+" and "+ COLUMN_USER_REVIEWED+" = ? "
                                +" and "+ COLUMN_USER_LOCATION+" = ? ";

        // selection argument
        String[] selectionArgs = {"0","0",admin_loc};

        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<User> getAllDoctor(String hospital_name) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_DOCTOR_SPECIALITY,
                COLUMN_DOCTOR_HOSPITAL
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";

        String selection = COLUMN_DOCTOR_HOSPITAL + " = ?";

        // selection argument
        String[] selectionArgs = {hospital_name};

        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_DOCTOR, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.getCount() >0 && cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setHospital(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_HOSPITAL)));
                user.setSpeciality(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_SPECIALITY)));
                // Adding user record to list
                userList.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<User> getAllDoctorByProblem(String problem_name) {
        // array of columns to fetch

        Log.d("problem",problem_name);
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_DOCTOR_SPECIALITY,
                COLUMN_DOCTOR_HOSPITAL
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";

        String selection = COLUMN_DOCTOR_SPECIALITY + " = ?";

        // selection argument
        String[] selectionArgs = {problem_name};

        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_DOCTOR, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.getCount() >0 && cursor.moveToFirst()) {
            Log.d("problem",problem_name);
            do{
                User user = new User();
                user.setSpeciality(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_SPECIALITY)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setHospital(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_HOSPITAL)));
                // Adding user record to list
                userList.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<User> getAllDoctorByLocation(String location_name) {
        // array of columns to fetch
         Log.d("location",location_name);
        String[] columns = {
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_DOCTOR_SPECIALITY,
                COLUMN_DOCTOR_HOSPITAL
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";

        String selection = COLUMN_DOCTOR_LOCATION + " = ?";

        // selection argument
        String[] selectionArgs = {location_name};

        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_DOCTOR, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.getCount() >0 && cursor.moveToFirst()) {
            do{
                Log.d("location",location_name);
                User user = new User();
                user.setSpeciality(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_SPECIALITY)));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setHospital(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_HOSPITAL)));
                // Adding user record to list
                userList.add(user);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<Record> getAllDoctorAppointment(String email) {

        int user_id = getUserId(email);
        // array of columns to fetch
        String[] columns = {
                COLUMN_P_ID,
                COLUMN_PATIENT_ID,
                COLUMN_DOCTOR_ID,
                COLUMN_PROBLEM,
                COLUMN_DATE
        };
        // sorting orders
        String sortOrder =
                COLUMN_P_ID + " ASC";
        // selection criteria
        String selection = COLUMN_DOCTOR_ID + " = ?";

        // selection argument
        String[] selectionArgs = {""+user_id};

        List<Record> userList = new ArrayList<Record>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_APPOINTMENT, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int patient_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ID)));
                Record record = new Record();
                record.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_P_ID))));
                record.setProblem(cursor.getString(cursor.getColumnIndex(COLUMN_PROBLEM)));
                record.setPatient_name(getUserName(patient_id));
                record.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                // Adding user record to list
                userList.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<Record> getAllAppointment(String email) {

        int user_id = getUserId(email);
        // array of columns to fetch
        String[] columns = {
                COLUMN_P_ID,
                COLUMN_DOCTOR_ID,
                COLUMN_RESCHEDULE,
                COLUMN_APP_ACCEPTED,
                COLUMN_APP_COMPLETE,
                COLUMN_DATE
        };
        // sorting orders
        String sortOrder =
                COLUMN_P_ID + " ASC";
        // selection criteria
        String selection = COLUMN_PATIENT_ID + " = ?";

        // selection argument
        String[] selectionArgs = {""+user_id};

        List<Record> userList = new ArrayList<Record>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_APPOINTMENT, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int doctor_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_ID)));
                Record record = new Record();
                int accept = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_APP_ACCEPTED)));
                int complete = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_APP_COMPLETE)));
                int reschedule = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_RESCHEDULE)));
                if(reschedule ==1 && accept==0){
                    record.setStatus(0);
                }else if(reschedule ==1 && accept==1 && complete==0){
                    record.setStatus(1);
                }else if(reschedule ==1 && accept==1 && complete ==1){
                    record.setStatus(2);
                }else{
                    record.setStatus(3);
                }
                record.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_P_ID))));
                record.setSpeciality(getDoctorSpeciality(doctor_id));
                record.setDoctor_name(getUserName(doctor_id));
                record.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                // Adding user record to list
                userList.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<Record> getCurrentAppointment(String email) {

        int doctor_id = getUserId(email);
        // array of columns to fetch
        String[] columns = {
                COLUMN_P_ID,
                COLUMN_PATIENT_ID,
                COLUMN_PROBLEM,
                COLUMN_DATE
        };
        // sorting orders
        String sortOrder =
                COLUMN_P_ID + " ASC";
        // selection criteria
        String selection = COLUMN_APP_REQUESTED + " = ?"+" and "+COLUMN_APP_ACCEPTED + " = ?"
                +" and "+COLUMN_APP_COMPLETE + " = ?"+" and "+COLUMN_RESCHEDULE + " = ?";;

        // selection argument
        String[] selectionArgs = {"1","1","0","1"};

        List<Record> userList = new ArrayList<Record>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_APPOINTMENT, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int patient_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ID)));
                Record record = new Record();
                record.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_P_ID))));
                record.setProblem(cursor.getString(cursor.getColumnIndex(COLUMN_PROBLEM)));
                record.setPatient_name(getUserName(patient_id));
                record.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                // Adding user record to list
                userList.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<Record> getNewAppointment(String email) {

        int doctor_id = getUserId(email);
        // array of columns to fetch
        String[] columns = {
                COLUMN_P_ID,
                COLUMN_PATIENT_ID,
                COLUMN_PROBLEM,
                COLUMN_DATE
        };
        // sorting orders
        String sortOrder =
                COLUMN_P_ID + " ASC";
        // selection criteria
        String selection = COLUMN_APP_REQUESTED + " = ?"+" and "+COLUMN_APP_ACCEPTED + " = ?"
                            +" and "+COLUMN_APP_COMPLETE + " = ?"+" and "+COLUMN_RESCHEDULE + " = ?";

        // selection argument
        String[] selectionArgs = {"1","0","0","0"};

        List<Record> userList = new ArrayList<Record>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_APPOINTMENT, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int patient_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ID)));
                Record record = new Record();
                record.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_P_ID))));
                record.setProblem(cursor.getString(cursor.getColumnIndex(COLUMN_PROBLEM)));
                record.setPatient_name(getUserName(patient_id));
                record.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                // Adding user record to list
                userList.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }
    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public int getUserId(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        int u_id=-1;
        if (cursorCount > 0 && cursor.moveToFirst()) {
            u_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)));
        }
        cursor.close();
//        db.close();

        //Log.d("userid",cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)));
//        if (cursorCount > 0 ) {
//            return u_id;
//        }

        return u_id;
    }

    public String getUserLocation(int user_id) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_LOCATION
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_ID + " = ?";

        // selection argument
        String[] selectionArgs = {""+user_id};
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        String u_name = null;
        if (cursorCount > 0 && cursor.moveToFirst()) {
            u_name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_LOCATION));
        }
        cursor.close();

        return u_name;
    }

    public String getUserName(int user_id) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_ID + " = ?";

        // selection argument
        String[] selectionArgs = {""+user_id};
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        String u_name = null;
        if (cursorCount > 0 && cursor.moveToFirst()) {
            u_name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
        }
        cursor.close();

        return u_name;
    }

    public String getUserPassword(int user_id) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_PASSWORD
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_ID + " = ?";

        // selection argument
        String[] selectionArgs = {""+user_id};
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        String u_name = null;
        if (cursorCount > 0 && cursor.moveToFirst()) {
            u_name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD));
        }
        cursor.close();

        return u_name;
    }
    public String getDoctorSpeciality(int user_id) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_DOCTOR_SPECIALITY
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_ID + " = ?";

        // selection argument
        String[] selectionArgs = {""+user_id};

        Cursor cursor = db.query(TABLE_DOCTOR, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        String speciality = null;
        if (cursorCount > 0 && cursor.moveToFirst()) {
            speciality = cursor.getString(cursor.getColumnIndex(COLUMN_DOCTOR_SPECIALITY));
        }
        cursor.close();

        return speciality;
    }

    public String getHospitalName(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_NAME,
                COLUMN_HOSPITAL_LOCATION
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@surya.com';
         */
        Cursor cursor = db.query(TABLE_HOSPITAL, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        String hospital_name = "NO";
        if (cursorCount > 0 && cursor.moveToFirst()) {
            hospital_name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
        }
        cursor.close();
        db.close();

        //Log.d("userid",cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)));
//        if (cursorCount > 0 ) {
//            return u_id;
//        }

        return hospital_name;
    }

    public int checkUserType(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_TYPE
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@surya.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        int u_type = -1;
        if (cursorCount > 0 && cursor.moveToFirst()) {
            u_type = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TYPE)));
        }
        cursor.close();
        db.close();

        return u_type;
    }

    public int isAdmin(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_TYPE,
                COLUMN_USER_ADMIN
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@surya.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        int is_admin = -1;
        if (cursorCount > 0 && cursor.moveToFirst()) {
            is_admin = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ADMIN)));
        }
        cursor.close();
        db.close();

        return is_admin;
    }


    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@surya.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public String checkPrescription(int p_id) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_P_ID,
                COLUMN_PATIENT_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_P_ID + " = ?";

        // selection argument
        String[] selectionArgs = {""+p_id};

        Cursor cursor = db.query(TABLE_APPOINTMENT, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();

        String patient_name = null;
        int patient_id = -1;
        if (cursorCount > 0 && cursor.moveToFirst()) {
            patient_id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PATIENT_ID)));
        }

        patient_name = getUserName(patient_id);
        cursor.close();
        db.close();
        return patient_name;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@surya.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
