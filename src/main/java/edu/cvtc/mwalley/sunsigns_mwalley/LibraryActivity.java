package edu.cvtc.mwalley.sunsigns_mwalley;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import edu.cvtc.mwalley.sunsigns_mwalley.sqlite.Helper;

import static edu.cvtc.mwalley.sunsigns_mwalley.R.id.buttonAdd;
import static edu.cvtc.mwalley.sunsigns_mwalley.R.id.library;

public class LibraryActivity extends AppCompatActivity {

    // nav bar
    BottomNavigationView bottomNavigationView;

    Helper db;

    Button add;
    EditText nameText;
    ListView personList;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        // intitialize and assign
        db = new Helper(this);

        listItem = new ArrayList<>();

        add = findViewById(buttonAdd);
        nameText = findViewById(R.id.nameText);
        personList = findViewById(R.id.personList);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        viewData();

        // set library selected to LibraryActivity
        bottomNavigationView.setSelectedItemId(library);

        // itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case library:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(),
                                InfoActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        // onClickListener to add entered in data
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString();

                if (!name.equals("") && db.insertData(name)) {
                    Toast.makeText(LibraryActivity.this, "Person added", Toast.LENGTH_SHORT).show();
                    nameText.setText("");
                    listItem.clear();
                    viewData();
                } else {
                    Toast.makeText(LibraryActivity.this, "Person NOT added", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // toast with info
        personList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = personList.getItemAtPosition(i).toString();
                Toast.makeText(LibraryActivity.this, "" + text, Toast.LENGTH_SHORT).show();
            }
        });

        // onItemLongClickListener to delete from list
        personList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                listItem.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(LibraryActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                return true;
            }

        });

    }

    // cursor to add data
    private void viewData() {
        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem);
            personList.setAdapter(adapter);

        }
    }

    // search menu to find saved info in SQLite
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> peopleList = new ArrayList<>();

                for (String person : listItem) {
                    if (person.toLowerCase().contains(newText.toLowerCase())) {
                        peopleList.add(person);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(LibraryActivity.this, android.R.layout.simple_list_item_1, peopleList);

                personList.setAdapter(adapter);

                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

}