package com.example.navigation.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.navigation.R;

public class EventDetailFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_events_detail, container, false);



        Intent intent = getActivity().getIntent();
        EventItem eventItem = (EventItem) intent.getSerializableExtra("event");

        Toast.makeText(getActivity(), "even: "+eventItem.getTitle(), Toast.LENGTH_SHORT).show();
        return view;
    }
}