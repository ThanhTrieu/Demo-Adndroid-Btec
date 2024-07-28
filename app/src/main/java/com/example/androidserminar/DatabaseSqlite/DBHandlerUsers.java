package com.example.androidserminar.DatabaseSqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.androidserminar.Model.User;

public class DBHandlerUsers extends SQLiteOpenHelper {
    private static final String DB_NAME = "expenseManager";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "users";
    // below variable is for our id column.
    private static final String ID_COL = "id";
    private static final String USERNAME_COL = "username";
    private static final String PASSWORD_COL = "password";
    private static final String EMAIL_COL = "email";
    private static final String PHONE_COL = "phone";
    public DBHandlerUsers(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME_COL + " VARCHAR(60),"
                + PASSWORD_COL + " VARCHAR(200),"
                + EMAIL_COL + " VARCHAR(60),"
                + PHONE_COL + " VARCHAR(30))";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addNewUser(String username, String password, String email, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(USERNAME_COL, username);
        values.put(PASSWORD_COL, password);
        values.put(EMAIL_COL, email);
        values.put(PHONE_COL, phoneNumber);

        // after adding all values we are passing
        // content values to our table.
        long insert = db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
        return insert;
    }
    public boolean checkUser (String username , String password){
        String [] columns = { ID_COL };
        SQLiteDatabase database = this.getWritableDatabase();
        String selection = USERNAME_COL + " = ?" + " AND " + PASSWORD_COL + " = ?";
        String[] selectionArgs = { username , password };

        Cursor cursor = database.query(
                TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int cursorCount = cursor.getCount();
        cursor.close();
        database.close();

        return cursorCount > 0;

    }
    public User getSingleUserInfo(String username, String password){
        Cursor cursor = null;
        User user = new User();
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            String[] columns = {ID_COL, USERNAME_COL, EMAIL_COL, PHONE_COL};
            String selection = USERNAME_COL + " = ?" + " AND " + PASSWORD_COL + " = ?";
            String[] selectionArgs = {username, password};
            cursor = database.query(
                    TABLE_NAME,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                //setting related user info in User Object
                user.setId(cursor.getInt(cursor.getColumnIndex(ID_COL)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME_COL)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL_COL)));
                user.setPhone(cursor.getString(cursor.getColumnIndex(PHONE_COL)));
            }
            //close cursor & database
            database.close();
        } finally {
            assert cursor != null;
            cursor.close();
        }
        return user;
    }
}
