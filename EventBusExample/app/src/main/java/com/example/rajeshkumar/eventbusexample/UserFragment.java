package com.example.rajeshkumar.eventbusexample;

import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

/**
 * Created by Rajesh kumar on 20-10-2017.
 */

public class UserFragment extends Fragment{
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragmentuser,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etMessage = (EditText) getView().findViewById(R.id.editText);
                Event.FragmentActivityMessage fragmentActivityMessageEvent =
                        new Event.FragmentActivityMessage(String.valueOf(etMessage.getText()));
                EventBus.getBus().post(fragmentActivityMessageEvent);
            }
        });
    }

    @Subscribe
    public void getMessage(Event.ActivityFragmentMessage activityFragmentMessage) {
        TextView messageView = (TextView) getView().findViewById(R.id.message);
        messageView.setText(getString(R.string.message_received) + " " + activityFragmentMessage.getMessage());
        Toast.makeText(getActivity(),
                getString(R.string.message_fragment) + " " + activityFragmentMessage.getMessage(),
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getBus().unregister(this);
    }
}
