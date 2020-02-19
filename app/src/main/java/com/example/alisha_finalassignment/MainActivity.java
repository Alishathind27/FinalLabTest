package com.example.alisha_finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    PersonDataBaseHelper mDatabase;
    EditText ET_FName, ET_LName, ET_PhoneNo, ET_Address;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ET_FName = findViewById(R.id.fName);
       ET_LName = findViewById(R.id.lName);
       ET_PhoneNo = findViewById(R.id.ph_Number);
       ET_Address = findViewById(R.id.address);

        findViewById(R.id.add_User).setOnClickListener(this);
        findViewById(R.id.view_person).setOnClickListener(this);

        mDatabase = new PersonDataBaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_User:
                addPerson();

                break;
            case R.id.view_person:
                // start activity to another activity to see the list of employees
                Intent intent = new Intent(MainActivity.this, Person_Activity.class);
                startActivity(intent);
                break;
        }
    }

        private void addPerson(){


            String firstname = ET_FName.getText().toString().trim();
            String lastname = ET_LName.getText().toString().trim();
            String contact = ET_PhoneNo.getText().toString().trim();
            String add = ET_Address.getText().toString().trim();


            if (firstname.isEmpty()) {
                ET_FName.setError("Name field is mandatory!");
                ET_FName.requestFocus();
                return;
            }

            if (lastname.isEmpty()) {
                ET_LName.setError("Salary field is mandatory!");
                ET_LName.requestFocus();
                return;
            }

            if (contact.isEmpty()) {
                ET_PhoneNo.setError("Salary field is mandatory!");
                ET_PhoneNo.requestFocus();
                return;
            }
            if (add.isEmpty()) {
                ET_Address.setError("Salary field is mandatory!");
                ET_Address.requestFocus();
                return;
            }


            if (mDatabase.addPerson(firstname, lastname, Integer.parseInt(contact),add)) {
                Toast.makeText(this,"Person added", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this,"Person not added", Toast.LENGTH_SHORT).show();





       }

        @Override
        protected void onRestart() {
            super.onRestart();

            ET_FName.setText("");
            ET_LName.setText("");
            ET_PhoneNo.setText("");
            ET_Address.setText("");
        }
    }

