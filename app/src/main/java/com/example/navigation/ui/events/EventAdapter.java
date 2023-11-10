package com.example.navigation.ui.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    Context context;

    ItemClick itemClick;

    List<EventItem> eventItemList;

    public EventAdapter(Context context, ItemClick itemClick, List<EventItem> eventItemList) {
        this.context = context;
        this.itemClick = itemClick;
        this.eventItemList = eventItemList;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_tile, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {
        holder.title.setText(eventItemList.get(position).getTitle());
        holder.description.setText(eventItemList.get(position).getDescription());
        holder.status.setText(eventItemList.get(position).isRead() ? "Read" : "Unread");
        String resName = eventItemList.get(position).getImages().get(0).split("\\.")[2];
        holder.image.setImageResource(context.getResources().getIdentifier(resName, "drawable", context.getPackageName()));
    }

    @Override
    public int getItemCount() {
        return eventItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,description,status;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            status = itemView.findViewById(R.id.status);
            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        itemClick.onItemClick(pos);
                    }
                }
            });
        }
    }
}
