package com.alluresoft.friends;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vikas on 9/17/2016.
 */
public class MessagesRecyclerAdapter extends RecyclerView.Adapter<MessagesRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private String[] user_name={"Person Name",
            "Person Name",
            "Person Name",
            "Person Name",
            "Person Name"
            };

    private String[] messages={"Served with perfect ingredient by our best users in the world.",
            "Our soup & ramen made from traditional ingredients.",
            "We have various desserts choice for you to accompany enjoying the love.",
            "Test our fresh drinks delightfully.","Enjoy our best coffee made from selected coffee seeds."
            };


    private int[] profile_pic={R.drawable.profile_pic,
            R.drawable.profile_pic,
            R.drawable.profile_pic,
            R.drawable.profile_pic,
            R.drawable.profile_pic
             };
    private String[] commented_time={"Yesterday at 23:30","Yesterday at 23:30","Yesterday at 23:30",
            "Yesterday at 23:30","Yesterday at 23:30"};

    class ViewHolder extends RecyclerView.ViewHolder{
        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
        public TextView commentedTime;

        public ViewHolder(View itemView){
            super(itemView);

            itemImage =(ImageView) itemView.findViewById(R.id.item_image);
            itemTitle= (TextView) itemView.findViewById(R.id.item_title);
            itemDetail=(TextView) itemView.findViewById(R.id.item_detail);
            commentedTime=(TextView) itemView.findViewById(R.id.commented_time);

/**
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Toast.makeText(mContext, "click detected in item" + position, Toast.LENGTH_LONG).show();
                    //Snackbar.make(v, "click detected in item" + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });*/
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
         View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_card_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(user_name[i]);
        viewHolder.itemDetail.setText(messages[i]);
        viewHolder.commentedTime.setText(commented_time[i]);
        viewHolder.itemImage.setImageResource(profile_pic[i]);

    }

    @Override
    public int getItemCount() {
        return user_name.length;
    }
}


