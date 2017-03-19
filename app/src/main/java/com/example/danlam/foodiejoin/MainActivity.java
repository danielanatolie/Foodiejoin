package com.example.danlam.foodiejoin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static Button button_create;

    public static String userName="tomer";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickButtonListener();

        Button joinButton = (Button) findViewById(R.id.joinButton);
        joinButton.setOnClickListener(new View.OnClickListener() {
            //@todo: Manipulates the views should segue to a new screen
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
//
//        Button createButton = (Button) findViewById(R.id.createButton);
//        createButton.setOnClickListener(new View.OnClickListener() {
//            //@todo: Manipulates the views should segue to a new screen
//            @Override
//            public void onClick(View view) {
//
//            }
//        });




//        Event test = new Event("12 main", "a&w", 2017, 4,20,4,20, 20,20);
//        test.setCreator("tomer");
//        test.addPerson("victor");
//        Map<String, Event> events = new HashMap<String, Event>();
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference eventsRef = database.getReference("events");
//        events.put("test event", test);
//        eventsRef.setValue(events);
    }


    public void onClickButtonListener() {
        button_create = (Button) findViewById(R.id.createButton);
        button_create.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.danlam.foodiejoin.createActivity");
                        startActivity(intent);
                    }
                }
        );
    }
}
