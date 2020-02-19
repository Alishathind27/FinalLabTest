package com.example.alisha_finalassignment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class personAdapter extends ArrayAdapter {

    Context mContext;
    int layRes;
    List<Person>  persons;
    PersonDataBaseHelper mDatabase;

    public personAdapter(Context mContext, int layRes, List<Person> persons, PersonDataBaseHelper mDatabase) {
        super(mContext, layRes, persons);
        this.mContext = mContext;
        this.layRes = layRes;
        this.persons = persons;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(layRes, null);

        TextView t_FName = view.findViewById(R.id.t_fname);
        TextView t_LName = view.findViewById(R.id.t_lname);
        TextView t_PhoneN = view.findViewById(R.id.t_phone);
        TextView t_Address = view.findViewById(R.id.t_address);

        final Person person = persons.get(position);
        t_FName.setText(person.getfName());
        t_LName.setText(person.getlName());
        t_PhoneN.setText(String.valueOf(person.getPhone_number()));
        t_Address.setText(person.getAddress());

        view.findViewById(R.id.edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePerson(person);
            }
        });

        view.findViewById(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteperson(person);
            }
        });

        return view;

    }


    private void deleteperson(final Person person){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Sure?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(mDatabase.deletePerson(person.getId()))
                    loadPerson();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void updatePerson(final Person person){
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.edit_layout, null);
        alert.setView(v);
        final AlertDialog alertDialog = alert.create();
        alertDialog.show();

        final EditText E_FName = v.findViewById(R.id.fName);
        final EditText E_LName = v.findViewById(R.id.lName);
        final EditText E_phone = v.findViewById(R.id.ph_Number);
        final EditText E_address = v.findViewById(R.id.address);


        E_FName.setText(person.getfName());
        E_LName.setText(person.getlName());
        E_phone.setText(String.valueOf(person.getPhone_number()));
        E_address.setText(person.getAddress());

        v.findViewById(R.id.update_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = E_FName.getText().toString().trim();
                String lastname = E_LName.getText().toString().trim();
                String contact = E_phone.getText().toString().trim();
                String add = E_address.getText().toString().trim();

                if (firstname.isEmpty()) {
                    E_FName.setError("Name field is mandatory!");
                    E_FName.requestFocus();
                    return;
                }

                if (lastname.isEmpty()) {
                    E_LName.setError("Salary field is mandatory!");
                    E_LName.requestFocus();
                    return;
                }

                if (contact.isEmpty()) {
                    E_phone.setError("Salary field is mandatory!");
                    E_phone.requestFocus();
                    return;
                }
                if (add.isEmpty()) {
                    E_address.setError("Salary field is mandatory!");
                    E_address.requestFocus();
                    return;
                }


                if (mDatabase.updatePerson(person.getId(), firstname, lastname, Integer.parseInt(contact),add)) {
                    Toast.makeText(mContext,"Person updated", Toast.LENGTH_SHORT).show();
                    loadPerson();
                } else
                    Toast.makeText(mContext,"Person not updated", Toast.LENGTH_SHORT).show();

//
                alertDialog.dismiss();
            }
        });

    }

    private void loadPerson(){
        Cursor cursor = mDatabase.getAllPerson();

        persons.clear();
        if (cursor.moveToFirst()) {

            do {
                persons.add(new Person(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4)
                ));
            } while (cursor.moveToNext());
            cursor.close();
        }

        notifyDataSetChanged();
    }
}
