package edu.cvtc.mwalley.sunsigns_mwalley;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button button;
    TextView mainText;
    TextView labelText;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // intitialize and assign
        button = findViewById(R.id.buttonBirth);
        mainText = findViewById(R.id.textViewMain);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // set home selected to MainActivity
        bottomNavigationView.setSelectedItemId(R.id.home);

        // itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.library:
                        startActivity(new Intent(getApplicationContext(),
                                LibraryActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(),
                                InfoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        // button onClickListener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // disappear the top TextView
                labelText = findViewById(R.id.textViewLabel);
                labelText.setVisibility(TextView.INVISIBLE);
                if(button.getVisibility()!=View.INVISIBLE)
                    button.setVisibility(View.INVISIBLE);
                else
                    button.setVisibility(View.VISIBLE);

                // show the datepicker/calendar
                Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog  datePickerDialog = new DatePickerDialog(MainActivity.this,
                                                                            MainActivity.this,
                                                                                year, month, day) ;

                datePickerDialog.show();

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        // return the sunSign
        mainText.setText("Your sun sign is " + getSunSign(dayOfMonth, month));
    }

    // getSunSign for chosen birthdate
    private String getSunSign(int day, int month) {
        String sunSign = "";

        // January = 0 , February = 1, March = 2, and so on...
        if (month == 0) {
            if(day <= 19) {
                sunSign = "Capricorn";
            } else {
                sunSign = " Aquarius";
            }
        } else if (month == 1) {
            if(day <= 18) {
                sunSign = "Aquarius";
            } else {
                sunSign = "Pisces";
            }
        } else if (month == 2) {
            if(day <= 20) {
                sunSign = "Pisces";
            } else {
                sunSign = "Aries";
            }
        } else if (month == 3) {
            if(day <= 19) {
                sunSign = "Aries";
            } else {
                sunSign = "Taurus";
            }
        } else if (month == 4) {
            if(day <= 20) {
                sunSign = "Taurus";
            } else {
                sunSign = "Gemini";
            }
        } else if (month == 5) {
            if(day <= 20) {
                sunSign = "Gemini";
            } else {
                sunSign = "Cancer";
            }
        } else if (month == 6) {
            if(day <= 22) {
                sunSign = "Cancer";
            } else {
                sunSign = "Leo";
            }
        } else if (month == 7) {
            if(day <= 22) {
                sunSign = "Leo";
            } else {
                sunSign = "Virgo";
            }
        } else if (month == 8) {
            if(day <= 22) {
                sunSign = "Virgo";
            } else {
                sunSign = "Libra";
            }
        } else if (month == 9) {
            if(day <= 22) {
                sunSign = "Libra";
            } else {
                sunSign = "Scorpio";
            }
        } else if (month == 10) {
            if(day <= 21) {
                sunSign = "Scorpio";
            } else {
                sunSign = "Sagittarius";
            }
        } else if (month == 11) {
            if (day <= 21) {
                sunSign = "Sagittarius";
            } else {
                sunSign = "Capricorn";
            }
        }
        return sunSign;

    }


}
