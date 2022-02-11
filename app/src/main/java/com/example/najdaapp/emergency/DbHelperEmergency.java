package com.example.najdaapp.emergency;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.najdaapp.contact.ContactModel;
import com.example.najdaapp.contact.DbHelper;

public class DbHelperEmergency  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactdata";

    // Country table name
    private static final String TABLE_NAME= "emergency";

    // Country Table Columns names
    private static final String KEY_ID = "id";
    private static final String NAME = "Name";
    private static final String PH = "PhoneNo";
    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DbHelperEmergency(@Nullable Context context){

            super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "+ PH + " TEXT, "+ NAME + " TEXT)";
//        Log.d("h i", CREATE_CONTACT_TABLE);

        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
//        onCreate(sqLiteDatabase);

    }
    public EmergencyModel getEmergency(String phone) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] {
                        NAME,PH }, null,
                null, null, null, null,"1");
        if (cursor != null)
            cursor.moveToFirst();


        EmergencyModel contact = new EmergencyModel(cursor.getString(1),
                cursor.getString(0));
        // return Emergency number
        return contact;
    }

    public void addEmergency(EmergencyModel contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelperEmergency.PH, contact.getPhoneNo());
        values.put(DbHelperEmergency.NAME, contact.getName());
        db.delete(TABLE_NAME,null,null);
        long newRowId = db.insert (DbHelperEmergency.TABLE_NAME, null, values);


//        Toast.makeText(get, "", Toast.LENGTH_SHORT).show();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        if (db != null && db.isOpen()){
            db.close();}

    }
}
