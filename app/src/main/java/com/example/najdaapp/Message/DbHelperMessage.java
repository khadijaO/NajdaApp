package com.example.najdaapp.Message;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.najdaapp.contact.ContactModel;
import com.example.najdaapp.emergency.EmergencyModel;

import java.util.ArrayList;
import java.util.List;

public class DbHelperMessage extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactdata";

    // Country table name
    private static final String TABLE_NAME= "messagesGPS";
//create table(id,salutation:hi, hello...,body:le messages principale
//    receiver:if u want to call the receiver by hu=is name
//     GPS boolean integrate GPS location or not
    // Country Table Columns names
    private static final String KEY_ID = "id";
    private static final String SALUTATION = "Salutation";
    private static final String BODY = "Body";
    private static final String RECEIVER = "Receiver";
    private static final String GPS = "GPS";

    private static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DbHelperMessage(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the table for the first time
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "+ SALUTATION + " TEXT, "
                + BODY + " TEXT, "+
                GPS + " BOOLEAN, "+
                RECEIVER + " BOOLEAN )";
//        Log.d("h i", CREATE_CONTACT_TABLE);
        Log.d("hi", "3333333333333333333333333333333333333");

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


//    // method to add the contact
    public void addMessageGPS(MessageModule messageModule){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete(TABLE_NAME,null,null);

        values.put(SALUTATION, messageModule.getSalutation());
        values.put(RECEIVER,messageModule.isReceiver());
        values.put(BODY, messageModule.getBody());
        values.put(GPS, messageModule.isGps());

        long newRowId = db.insert (TABLE_NAME, null, values);
    }

    public MessageModule getMessage(boolean GPS) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,null, null,
                null, null, null, null,"1");
        if (cursor != null)
            cursor.moveToFirst();
//    public MessageModule(String salutation, String body, boolean receiver, boolean gps) {
//

        MessageModule contact = new MessageModule(cursor.getString(1),cursor.getString(2),Boolean.parseBoolean(cursor.getString(3)),Boolean.parseBoolean(cursor.getString(4)));

        // return Emergency number
        return contact;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        if (db != null && db.isOpen()){
            db.close();}

    }

//
}


