package com.alluresoft.friends;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.alluresoft.friends.fragments.DiscoverFragment;
import com.alluresoft.friends.fragments.FriendsFragment;
import com.alluresoft.friends.fragments.MessagesFragment;
import com.alluresoft.friends.fragments.NearbyFragment;
import com.alluresoft.friends.fragments.ProfileFragment;

public class Main2Activity extends AppCompatActivity {

    LinearLayout nearby_bottom_icon;
    LinearLayout friends_bottom_icon;
    LinearLayout discover_bottom_icon;
    LinearLayout messages_bottom_icon;
    LinearLayout profile_bottom_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nearby_bottom_icon=(LinearLayout) findViewById(R.id.nearby_bottom_layout_icon);
        friends_bottom_icon=(LinearLayout) findViewById(R.id.friends_bottom_layout_icon);
        discover_bottom_icon=(LinearLayout) findViewById(R.id.discover_bottom_layout_icon);
        messages_bottom_icon=(LinearLayout) findViewById(R.id.messages_bottom_layout_icon);
        profile_bottom_icon=(LinearLayout) findViewById(R.id.profile_bottom_layout_icon);

    }

    //bottom icon listener
    public void onIconClick(View v){

        if(v.getId() == R.id.nearby_bottom_layout_icon){
            nearby_bottom_icon.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NearbyFragment()).commit();
            setInActiveTab("nearby_bottom_layout_icon");

        }else if(v.getId() == R.id.discover_bottom_layout_icon){
            discover_bottom_icon.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DiscoverFragment()).commit();
            setInActiveTab("discover_bottom_layout_icon");

        }else if(v.getId() == R.id.friends_bottom_layout_icon){
            friends_bottom_icon.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FriendsFragment()).commit();
            setInActiveTab("friends_bottom_layout_icon");

        }else if(v.getId() == R.id.messages_bottom_layout_icon){
            messages_bottom_icon.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MessagesFragment()).commit();
            setInActiveTab("messages_bottom_layout_icon");

        }else if(v.getId() == R.id.profile_bottom_layout_icon){
            profile_bottom_icon.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            setInActiveTab("profile_bottom_layout_icon");
        }
    }

    public void setInActiveTab(String inActiveTabName) {
        if (inActiveTabName == "nearby_bottom_layout_icon") {
            friends_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            discover_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            messages_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            profile_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
        }else if (inActiveTabName == "friends_bottom_layout_icon") {
            nearby_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            discover_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            messages_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            profile_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
        }else if (inActiveTabName == "discover_bottom_layout_icon") {
            nearby_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            friends_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            messages_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            profile_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
        }else if (inActiveTabName == "messages_bottom_layout_icon") {
            nearby_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            discover_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            friends_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            profile_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
        }else if (inActiveTabName == "profile_bottom_layout_icon") {
            nearby_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            discover_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            messages_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
            friends_bottom_icon.setBackgroundColor(getResources().getColor(R.color.tab_in_active));
        }
    }
}
