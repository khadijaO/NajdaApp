package com.example.najdaapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.najdaapp.contact.ContactModel;
import com.example.najdaapp.contact.DbHelper;

public class UserContact extends AppCompatActivity {
    Button existing_contact,add_contact,reset_form,manage_contact;
    EditText number_contact, name_contact, relation_contact;
    String numberContact, nameContact, relationContact;
    private static final int REQUEST_SELECT_CONTACT = 1;
    DbHelper d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                Log.d("hi", "-----------------------");
        d=new DbHelper(this);
        setContentView(R.layout.activity_user_contact);
        number_contact = findViewById(R.id.contact_phone);
        name_contact = findViewById(R.id.contact_name);

//        numberContact=number_contact.getText().toString();
//        nameContact=name_contact.getText().toString();

        /*----------------------------   RELATION SPINNER  ---------------------------*/
//        set relations
        final Spinner spinner = (Spinner) findViewById(R.id.relation_contact);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.relation, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        relationContact = spinner.getSelectedItem().toString();
        /*-----------------------------------------------------------------------------------*/

        /*----------------------------   EXISTING CONTACTS   ---------------------------*/
        existing_contact = findViewById(R.id.existing_contacts_button);

        existing_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ContactModel m=new ContactModel(1, "111111","khadija", "mother");
//                Toast.makeText(getApplicationContext(), m.getPhoneNo().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, REQUEST_SELECT_CONTACT);


            }
        });
        /*-------------------------------------------------------------------*/

        /*----------------------------   ADD CONTACTS   ---------------------------*/
        add_contact=findViewById(R.id.addContact);
        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberContact=number_contact.getText().toString();
                nameContact=name_contact.getText().toString();
                //add the data in DB
                ContactModel contact_user=new ContactModel( numberContact,nameContact, relationContact);
                d.addContact(contact_user);
            }
        });
        /*-------------------------------------------------------------------*/
        /*----------------------------   RESET CONTACTS   ---------------------------*/
        reset_form=findViewById(R.id.ResetContact);
        reset_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add the data in DB
//                relation_contact.setText("");
                name_contact.setText("");
                number_contact.setText("");
            }
        });
        /*-------------------------------------------------------------------*/
        /*----------------------------   MANAGE CONTACTS   ---------------------------*/
        manage_contact=findViewById(R.id.manage_contacts);
        manage_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(getApplicationContext(),ContactManager.class);
               startActivity(i);
            }
        });
        /*-------------------------------------------------------------------*/





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_CONTACT && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = getContentResolver().query(contactUri, projection,
                    null, null, null);
            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME);
                String number = cursor.getString(numberIndex);
                String name = cursor.getString(nameIndex);
                number_contact.setText(number);
                name_contact.setText(name);
                Toast.makeText(this, "" + number + "  " + name, Toast.LENGTH_SHORT).show();
            }
        }

    }

}
