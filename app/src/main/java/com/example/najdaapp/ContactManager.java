package com.example.najdaapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.najdaapp.contact.ContactModel;
import com.example.najdaapp.contact.DbHelper;
import com.example.najdaapp.contactAdapter.CustomAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ContactManager extends AppCompatActivity {
EditText text;
DbHelper d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manager);
        d=new DbHelper(getApplicationContext());

        ArrayList<ContactModel>l;
        l= (ArrayList<ContactModel>) d.getAllContacts();

//        for (ContactModel object : l) {
//            text.setText(text.getText()+object.getName());
//        }
        ListView lv=findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), l);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext()," vous avez cliqué sur la ville de :"+i,Toast.LENGTH_SHORT).show();
            }
        });}



}
