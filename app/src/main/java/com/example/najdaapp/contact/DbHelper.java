package com.example.najdaapp.contact;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactdata";

    // Country table name
    private static final String TABLE_NAME= "contacts";

    // Country Table Columns names
    private static final String KEY_ID = "id";
    private static final String NAME = "Name";
    private static final String PHONENO = "PhoneNo";
    private static final String RELATION = "Relation";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the table for the first time
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + NAME + " TEXT, "
                + PHONENO + " TEXT, " +RELATION + " TEXT)";
//        Log.d("hi", CREATE_CONTACT_TABLE);
        Log.d("hi", "3333333333333333333333333333333333333");

        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // method to add the contact
    public void addcontact(ContactModel contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(NAME,contact.getName());
        c.put(PHONENO,contact.getPhoneNo());
        c.put(RELATION,contact.getRelation());
        db.insert(TABLE_NAME,null,c);
        db.close();
        Log.e("hi", "error");

//        Toast.makeText(get, "", Toast.LENGTH_SHORT).show();
    }

    // method to retrieve all the contacts in List
    public List<ContactModel> getAllContacts(){
        List<ContactModel> list=new ArrayList<>();
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery(query,null);
        if(c.moveToFirst()) {
            do {

                list.add(new ContactModel(c.getString(1),c.getString(2),"father"));

            } while (c.moveToNext());
        }
        return list;
    }

    // get the count of data, this will allow user
    // to not add more that five contacts in database
    public int count(){
        int count=0;
        String query="SELECT COUNT(*) FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c=db.rawQuery(query,null);
        if(c.getCount()>0){
            c.moveToFirst();
            count=c.getInt(0);
        }
        c.close();
        return count;
    }

    // Deleting single country
    public void deleteContact(ContactModel contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i=db.delete(TABLE_NAME,KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });

        db.close();
    }
}

