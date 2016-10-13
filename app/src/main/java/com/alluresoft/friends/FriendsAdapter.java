package com.alluresoft.friends;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;


public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Album> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView personName, location,followersCount,eventCount;
        public ImageView thumbnail,friendRequest;

        public MyViewHolder(View view) {
            super(view);
            personName = (TextView) view.findViewById(R.id.title);
            location = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            friendRequest=(ImageView) view.findViewById(R.id.send_friend_request);
            followersCount = (TextView) view.findViewById(R.id.followers_count);
            eventCount = (TextView) view.findViewById(R.id.events_count);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Toast.makeText(v.getContext(), "click detected in item" + position, Toast.LENGTH_LONG).show();
                }
            });

        }
    }


    public FriendsAdapter(Context mContext, List<Album> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friends_card, parent, false);
        mContext = itemView.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.personName.setText(album.getName());
        holder.location.setText(album.getName());
       // holder.followersCount.setText(album.getFollowers());
       // holder.eventCount.setText(album.getEvents());

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.friendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Friend request sent..", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
