package com.huma.roomforasset;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.huma.room_for_asset.RoomAsset;
import com.jakewharton.fliptables.FlipTableConverters;

import java.util.List;

public class JavaActivity extends AppCompatActivity {
    private static final String TAG = JavaActivity.class.getSimpleName();

    private Button employeesButton;
    private Button customersButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        initView();

        final AppDatabase db = RoomAsset.databaseBuilder(
                getApplicationContext(), AppDatabase.class, "chinook.db").build();


        employeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final List<Employees> employees = db.chinookDao().getEmployees();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                resultTextView.setText(
                                        FlipTableConverters.fromIterable(employees, Employees.class));
                            }
                        });

                        Log.d(TAG, "employees = " + employees);
                    }
                }).start();
            }
        });

        customersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final List<Customers> customers = db.chinookDao().getCustomers();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                resultTextView.setText(
                                        FlipTableConverters.fromIterable(customers, Customers.class));
                            }
                        });
                        Log.d(TAG, "customers = " + customers);
                    }
                }).start();

            }
        });
    }

    private void initView() {
        employeesButton = findViewById(R.id.employeesButton);
        customersButton = findViewById(R.id.customersButton);
        resultTextView = findViewById(R.id.resultTextView);
    }
}
