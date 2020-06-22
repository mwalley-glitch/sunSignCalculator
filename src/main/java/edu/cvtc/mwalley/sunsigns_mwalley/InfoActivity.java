package edu.cvtc.mwalley.sunsigns_mwalley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InfoActivity extends AppCompatActivity  {

    BottomNavigationView bottomNavigationView;
    Spinner sunSpinner;
    TextView textViewTraits;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // intitialize and assign
        sunSpinner = findViewById(R.id.sunSpinner);
        textViewTraits = findViewById(R.id.textViewTraits);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set info selected to InfoActivity
        bottomNavigationView.setSelectedItemId(R.id.info);

        // itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.info:
                        return true;
                    case R.id.library:
                        startActivity(new Intent(getApplicationContext(),
                                LibraryActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        // get array from strings.xml and set up adapter
        ArrayAdapter<String> signAdapter = new ArrayAdapter<String>(InfoActivity.this,
                android.R.layout.simple_expandable_list_item_1,
                getResources().getStringArray(R.array.signs));
        signAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sunSpinner.setAdapter(signAdapter);


        // set up onItemSelectedListener and display the correct string
        sunSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                switch (i) {
                    case 0:
                        textViewTraits.setText(R.string.Sign1);
                        break;
                    case 1:
                        textViewTraits.setText(R.string.Sign2);
                        break;
                    case 2:
                        textViewTraits.setText(R.string.Sign3);
                        break;
                    case 3:
                        textViewTraits.setText(R.string.Sign4);
                        break;
                    case 4:
                        textViewTraits.setText(R.string.Sign5);
                        break;
                    case 5:
                        textViewTraits.setText(R.string.Sign6);
                        break;
                    case 6:
                        textViewTraits.setText(R.string.Sign7);
                        break;
                    case 7:
                        textViewTraits.setText(R.string.Sign8);
                        break;
                    case 8:
                        textViewTraits.setText(R.string.Sign9);
                        break;
                    case 9:
                        textViewTraits.setText(R.string.Sign10);
                        break;
                    case 10:
                        textViewTraits.setText(R.string.Sign11);
                        break;
                    case 11:
                        textViewTraits.setText(R.string.Sign12);
                        break;
                        default:
                        textViewTraits.setText("Select a Sign");


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}

