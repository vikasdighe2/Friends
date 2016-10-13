package com.alluresoft.friends;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vikas on 9/16/2016.
 */
public class CommentsRecyclerAdapter extends RecyclerView.Adapter<CommentsRecyclerAdapter.ViewHolder> {

    private String[] profileName={"vikas","sagar","Dessert","Drinks","Coffee" };

    private String[] comments={"message1","To finish setting up your Microsoft account, we just need to make sure this email address is yours.If you didn't request this code, you can safely ignore this email. Someone else might have typed your email address by mistake. ",
            "as nbbas","asdxvhas","bbjbs"};

    private String[] commentedTime={"12:10","12:56","11:10","09:14","10:34"};


    private int[] profilePicture={R.drawable.profile_pic,
            R.drawable.profile_pic,
            R.drawable.profile_pic,
            R.drawable.profile_pic,
            R.drawable.profile_pic
             };

    class ViewHolder extends RecyclerView.ViewHolder{
        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;


        public ViewHolder(View itemView){
            super(itemView);

            itemImage =(ImageView) itemView.findViewById(R.id.item_image);
            itemTitle= (TextView) itemView.findViewById(R.id.item_title);
            itemDetail=(TextView) itemView.findViewById(R.id.item_detail);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Snackbar.make(v, "click detected in item" + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
         View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_card_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(profileName[i] + ":" + comments[i]);
        viewHolder.itemDetail.setText(commentedTime[i]);
        viewHolder.itemImage.setImageResource(profilePicture[i]);

    }

    @Override
    public int getItemCount() {
        return profileName.length;
    }
}


