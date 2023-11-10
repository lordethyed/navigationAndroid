package com.example.navigation.ui.events;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navigation.MainActivity;
import com.example.navigation.R;
import com.example.navigation.ui.dbUtils.DbUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class EventsListFragment extends Fragment implements ItemClick{

    EventAdapter eventAdapter;

    View view;

    RecyclerView recyclerView;
    DbUtils dbUtils;

    TextView btnAll;
    TextView btnUnRead;
    TextView btnRead;

    List<EventItem> eventItems = new ArrayList<>();
    List<EventItem> eventItemsAll = new ArrayList<>();
    List<EventItem> eventItemsUnread = new ArrayList<>();
    List<EventItem> eventItemsRead = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_events_list, container, false);
        recyclerView = view.findViewById(R.id.list);
        btnAll = view.findViewById(R.id.menu1);
        btnUnRead = view.findViewById(R.id.menu2);
        btnRead = view.findViewById(R.id.menu3);

        dbUtils = new DbUtils(getContext());

        try {
            eventItems = dbUtils.getEvents();
            eventItems.forEach((e) -> {
                eventItemsAll.add(e);
                if(e.isRead()){
                    eventItemsRead.add(e);
                } else{
                    eventItemsUnread.add(e);
                }
            });
            changeAdapter(eventItems);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAdapter(eventItemsAll);
            }
        });

        btnUnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAdapter(eventItemsUnread);
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAdapter(eventItemsRead);
            }
        });

        return view;
    }

    private void changeAdapter(List<EventItem> eventItemList){
        eventAdapter = new EventAdapter(getContext(),this::onItemClick , eventItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(eventAdapter);
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("fragmentName", "eventDetail");
        intent.putExtra("event", eventItemsAll.get(pos));
        startActivity(intent);
    }

}