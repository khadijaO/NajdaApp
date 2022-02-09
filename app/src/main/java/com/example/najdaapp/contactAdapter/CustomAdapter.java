package com.example.najdaapp.contactAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.najdaapp.R;
import com.example.najdaapp.contact.ContactModel;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;//i don't know what does it mean
    ArrayList<ContactModel> arrayList;

    public CustomAdapter(Context context, ArrayList<ContactModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return  this.arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_slave, viewGroup, false);
        }

        TextView number = (TextView) convertView.findViewById(R.id.phone);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView relation = (TextView) convertView.findViewById(R.id.relation);

        name.setText(arrayList.get(i).getName());
        number.setText(arrayList.get(i).getPhoneNo());
        relation.setText(arrayList.get(i).getRelation());
        return convertView;

    }
}
