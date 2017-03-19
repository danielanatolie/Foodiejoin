package com.example.danlam.foodiejoin;
import com.example.danlam.foodiejoin.Event;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
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
                        Event newEvent = new Event(address, title, 2017, 5, 20, 1, 30, Math.random()*40, Math.random()*40);
                        newEvent.setCreator(MainActivity.userName);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference eventsRef = database.getReference("events");
                        String key = eventsRef.push().getKey();
                        eventsRef.child(key).setValue(newEvent);
                        eventsRef.child(key).child("users").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                showNotification((String)dataSnapshot.getValue());
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
    }
    public void showNotification(String name){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                        .setContentTitle("foodiejoin")
                        .setContentText(name + " wants to join you for dinner!");
//        Intent resultIntent = new Intent(this, ResultActivity.class);
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(
//                        0,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );
        //mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        int mId=0;
        mNotificationManager.notify(mId, mBuilder.build());
    }
}