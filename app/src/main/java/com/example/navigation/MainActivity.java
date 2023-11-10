package com.example.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.navigation.ui.events.EventDetailFragment;
import com.example.navigation.ui.events.EventsListFragment;
import com.example.navigation.ui.records.RecordsFragment;
import com.example.navigation.ui.tickets.TicketsListFragment;

public class MainActivity extends AppCompatActivity {
    TextView tv_events;
    TextView tv_tickets;
    TextView tv_records;
    FrameLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_events = findViewById(R.id.tv_events);
        tv_tickets = findViewById(R.id.tv_tickets);
        tv_records = findViewById(R.id.tv_records);


        tv_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_events.setTextColor(getColor(R.color.red));
                tv_tickets.setTextColor(getColor(R.color.black));
                tv_records.setTextColor(getColor(R.color.black));
                switchFragment(new EventsListFragment());
            }
        });

        tv_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_events.setTextColor(getColor(R.color.black));
                tv_tickets.setTextColor(getColor(R.color.red));
                tv_records.setTextColor(getColor(R.color.black));
                switchFragment(new TicketsListFragment());
            }
        });

        tv_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_events.setTextColor(getColor(R.color.black));
                tv_tickets.setTextColor(getColor(R.color.black));
                tv_records.setTextColor(getColor(R.color.red));
                switchFragment(new RecordsFragment());
            }
        });

        Intent intent = getIntent();
        String fragmentName = intent.getStringExtra("fragmentName");

        if(fragmentName != null){

            switch (fragmentName){
                case "eventDetail":
                    switchFragment(new EventDetailFragment());

                    tv_events.setTextColor(getColor(R.color.red));
                    break;
            }
        }
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}