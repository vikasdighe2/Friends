package com.alluresoft.friends;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vikas on 9/19/2016.
 */
public class DiscoverSecondRecyclerAdapter extends RecyclerView.Adapter<DiscoverSecondRecyclerAdapter.ViewHolder> {

    private String[] eventName={"New Year Party","Car Race","Temple","Hill Race","Cam Soa"};

    private String[] location={"us, noida",
            "bihar, it", "ran, ham",
            "canada,US","Mumbai, Harbur" };
    private String[] dateNTime={"16 aug 2016","16 aug 2016","16 aug 2016","16 aug 2016","16 aug 2016"};


    private int[] eventPic={R.drawable.blank_avatar,
            R.drawable.blank_avatar,
            R.drawable.blank_avatar,
            R.drawable.blank_avatar,
            R.drawable.blank_avatar
             };
    private int[] profilePicture={R.drawable.blank_avatar,
            R.drawable.blank_avatar,
            R.drawable.blank_avatar,
            R.drawable.blank_avatar, R.drawable.blank_avatar
    };

    class ViewHolder extends RecyclerView.ViewHolder{
        public int currentItem;
        public ImageView eventPic;
        public ImageView profilePic;
        public TextView eventName;
        public TextView lacation;
        public TextView dateNTime;
        public Button shareItBtn,favoritesBtn,commentBtn;

        public ViewHolder(View itemView){
            super(itemView);

            eventPic =(ImageView) itemView.findViewById(R.id.event_pic);
            profilePic =(ImageView) itemView.findViewById(R.id.profile_pic_discover_second);
            eventName= (TextView) itemView.findViewById(R.id.event_name_on_discover);
            lacation=(TextView) itemView.findViewById(R.id.place_location);
            dateNTime=(TextView) itemView.findViewById(R.id.time_and_date);

            shareItBtn=(Button) itemView.findViewById(R.id.share_it_button);
            favoritesBtn=(Button) itemView.findViewById(R.id.favorite_button);
            commentBtn=(Button) itemView.findViewById(R.id.comment_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    //Snackbar.make(v, "click detected in item" + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
         View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_second_card_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.eventName.setText(eventName[i]);
        viewHolder.lacation.setText(location[i]);
        viewHolder.dateNTime.setText(dateNTime[i]);
        viewHolder.eventPic.setImageResource(eventPic[i]);
        viewHolder.profilePic.setImageResource(profilePicture[i]);

        viewHolder.shareItBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "AndroidSolved");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "Share this event on over the web...");
                view.getContext().startActivity(sharingIntent);
            }
        });

        viewHolder.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),MeetupCommentActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        viewHolder.favoritesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Favorites",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventName.length;
    }
}


