package com.example.rajeshkumar.eventbusexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new UserFragment()).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getBus().register(this);
    }
    public void sendMessageToFragment(View view) {
        EditText etMessage = (EditText) findViewById(R.id.activityData);
        Event.ActivityFragmentMessage activityFragmentMessageEvent =
                new Event.ActivityFragmentMessage(String.valueOf(etMessage.getText()));
        EventBus.getBus().post(activityFragmentMessageEvent);
    }


    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getBus().unregister(this);
    }
    @Subscribe
    public void getMessage(Event.FragmentActivityMessage fragmentActivityMessage) {
        TextView messageView = (TextView) findViewById(R.id.message);
        messageView.setText(getString(R.string.message_received) + " " + fragmentActivityMessage.getMessage());

        Toast.makeText(getApplicationContext(),
                getString(R.string.message_main_activity) + " " + fragmentActivityMessage.getMessage(),
                Toast.LENGTH_SHORT).show();
    }
}
