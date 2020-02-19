package com.example.alisha_finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class Person_Activity extends AppCompatActivity {

    PersonDataBaseHelper mDatabase;
    List<Person> personList;
    ListView listView;
    SearchView searchView;
    List<Person> userSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_);

        searchView = findViewById(R.id.search_bar);
        listView = findViewById(R.id.prsn_list);
        userSearch = new ArrayList<>();
        personList = new ArrayList<>();

        mDatabase = new PersonDataBaseHelper(this);
        loadPerson();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.isEmpty()){
                    userSearch.clear();
                    for(int i = 0; i< personList.size(); i++) {
                        Person contact = personList.get(i);
                                if (contact.fName.contains(newText)) {
                                    userSearch.add(contact);
                                }
                    }
                    personAdapter pAdapter = new personAdapter(Person_Activity.this, R.layout.layout_person, userSearch, mDatabase);
                    listView.setAdapter(pAdapter);
                }
                if(newText.isEmpty()) {
                    personAdapter pAdapter = new personAdapter(Person_Activity.this, R.layout.layout_person, userSearch, mDatabase);
                    listView.setAdapter(pAdapter);
                }
                return  false;
            }

        });

    }

    private void loadPerson(){

        Cursor cursor = mDatabase.getAllPerson();

        if(cursor.moveToFirst()){
            do {
                personList.add(new Person(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4)
                ));
            }while (cursor.moveToNext());
            cursor.close();

            personAdapter pAdapter = new personAdapter(this, R.layout.layout_person, personList, mDatabase);
            listView.setAdapter(pAdapter);


        }
    }
}
