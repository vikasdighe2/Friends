package com.alluresoft.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){

        if(v.getId() == R.id.create_meetup_popup){
            Intent intent = new Intent(this, CreateMeetUpActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.discover){
            Intent intent = new Intent(this, DiscoverActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.meetup_details){
            Intent intent = new Intent(this, MeetUpDetailsActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.nearby){
            Intent intent = new Intent(this, NearbyActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.other_user_profile){
            Intent intent = new Intent(this, OtherUserProfileActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.walk_through){
            Intent intent = new Intent(this, WalkThroughActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.meetup_comment){
            Intent intent = new Intent(this, MeetupCommentActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.friends){
            Intent intent = new Intent(this, FriendsActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.discover_second){
            Intent intent = new Intent(this, DiscoverSecondActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.comments){
            Intent intent = new Intent(this, CommentsActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.fragments){
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
    }

}
