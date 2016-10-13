package com.alluresoft.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alluresoft.friends.fragments.OneFragment;
import com.alluresoft.friends.fragments.ThreeFragment;
import com.alluresoft.friends.fragments.TwoFragment;

public class MeetUpDetailsActivity extends AppCompatActivity {


    private ImageView friend1,friend2,friend3,friend4;
    private ImageView nearby,followers,profile,discover,favourites;
    private Button attend,shareIt,friend5;
    private int attending=30,comments=20,likes=50;
    LinearLayout attending_layout,comments_layout,likes_layout;
    ImageView attending_icon,comments_icon,likes_icon;
    TextView attending_text,comments_text,likes_text;
    TextView attending_count,comments_count,likes_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_up_details);

        TextView tab_name=(TextView) findViewById(R.id.tab_name);
        tab_name.setText("Football Match");
        ImageView backArrow=(ImageView) findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        attending_icon=(ImageView) findViewById(R.id.attending_icon);
        attending_count=(TextView)findViewById(R.id.attending_count);
        attending_text=(TextView)findViewById(R.id.attending_text);
        comments_icon=(ImageView) findViewById(R.id.comments_icon);
        comments_count=(TextView)findViewById(R.id.comments_count);
        comments_text=(TextView)findViewById(R.id.comments_text);
        likes_icon=(ImageView) findViewById(R.id.likes_icon);
        likes_count=(TextView)findViewById(R.id.likes_count);
        likes_text=(TextView)findViewById(R.id.likes_text);
      //  getSupportFragmentManager().beginTransaction().replace(R.id.one_two_three_fragment_container, new OneFragment()).commit();
      //  setInActiveTab("attending_layout");

        shareIt=(Button) findViewById(R.id.share_it_button);
        shareIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });


    }

    public void onIconClick(View v){
        if(v.getId() == R.id.nearby_map_icon){
            Intent intent = new Intent(this, NearbyActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.discover_map_icon){
            Intent intent = new Intent(this, DiscoverActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.friends_map_icon){
            Intent intent = new Intent(this, FriendsActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.messages_map_icon){
            Intent intent = new Intent(this, MeetupCommentActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.profile_map_icon){
            Intent intent = new Intent(this, OtherUserProfileActivity.class);
            startActivity(intent);
        }
    }

    //sharing implementation here
    private void shareIt() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Share this event on over the web...");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
    public void onLayoutClick(View view){
        if(view.getId() == R.id.attending_layout){
            getSupportFragmentManager().beginTransaction().replace(R.id.one_two_three_fragment_container, new OneFragment()).commit();
            setInActiveTab("attending_layout");
        }else if(view.getId() == R.id.comments_layout){
            getSupportFragmentManager().beginTransaction().replace(R.id.one_two_three_fragment_container, new TwoFragment()).commit();
            setInActiveTab("comments_layout");
        }else if(view.getId() == R.id.likes_layout){
            getSupportFragmentManager().beginTransaction().replace(R.id.one_two_three_fragment_container, new ThreeFragment()).commit();
            setInActiveTab("likes_layout");
        }
    }
    public void setInActiveTab(String inActiveTabName) {
        if (inActiveTabName == "attending_layout") {
            attending_icon.setColorFilter(getResources().getColor(R.color.tab_in_active));
            attending_text.setTextColor(getResources().getColor(R.color.tab_in_active));
            attending_count.setTextColor(getResources().getColor(R.color.tab_in_active));
            comments_icon.setColorFilter(getResources().getColor(R.color.blackNormal));
            comments_text.setTextColor(getResources().getColor(R.color.blackNormal));
            comments_count.setTextColor(getResources().getColor(R.color.blackNormal));
            likes_icon.setColorFilter(getResources().getColor(R.color.blackNormal));
            likes_text.setTextColor(getResources().getColor(R.color.blackNormal));
            likes_count.setTextColor(getResources().getColor(R.color.blackNormal));

        } else if (inActiveTabName == "comments_layout") {
            attending_icon.setColorFilter(getResources().getColor(R.color.blackNormal));
            attending_text.setTextColor(getResources().getColor(R.color.blackNormal));
            attending_count.setTextColor(getResources().getColor(R.color.blackNormal));
            comments_icon.setColorFilter(getResources().getColor(R.color.tab_in_active));
            comments_text.setTextColor(getResources().getColor(R.color.tab_in_active));
            comments_count.setTextColor(getResources().getColor(R.color.tab_in_active));
            likes_icon.setColorFilter(getResources().getColor(R.color.blackNormal));
            likes_text.setTextColor(getResources().getColor(R.color.blackNormal));
            likes_count.setTextColor(getResources().getColor(R.color.blackNormal));

        }else if (inActiveTabName == "likes_layout") {
            attending_icon.setColorFilter(getResources().getColor(R.color.blackNormal));
            attending_text.setTextColor(getResources().getColor(R.color.blackNormal));
            attending_count.setTextColor(getResources().getColor(R.color.blackNormal));
            comments_icon.setColorFilter(getResources().getColor(R.color.blackNormal));
            comments_text.setTextColor(getResources().getColor(R.color.blackNormal));
            comments_count.setTextColor(getResources().getColor(R.color.blackNormal));
            likes_icon.setColorFilter(getResources().getColor(R.color.tab_in_active));
            likes_text.setTextColor(getResources().getColor(R.color.tab_in_active));
            likes_count.setTextColor(getResources().getColor(R.color.tab_in_active));

        }
    }
}
