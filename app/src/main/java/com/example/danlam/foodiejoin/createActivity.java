package com.example.danlam.foodiejoin;
import com.example.danlam.foodiejoin.Event;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class createActivity extends AppCompatActivity {
    Button mButton;
    EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mButton = (Button)findViewById(R.id.createEventButton);
        mEdit   = (EditText)findViewById(R.id.titleText);
        mEdit   = (EditText)findViewById(R.id.addressText);


        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Log.d("titleText", mEdit.getText().toString());
                        Log.d("addressText", mEdit.getText().toString());

                        EditText titleText = (EditText)findViewById(R.id.titleText);
                        String title = titleText.getText().toString();

                        EditText addressText = (EditText)findViewById(R.id.addressText);
                        String address = addressText.getText().toString();
                        Event newEvent = new Event(address, title, 2017, 5, 20, 1, 30);
                    }
                });
    }
}
