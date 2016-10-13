package com.alluresoft.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DiscoverSecondActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_second);

        TextView tab_name=(TextView) findViewById(R.id.tab_name);
        tab_name.setText("Discover");
        ImageView backArrow=(ImageView) findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.discover_second_recycler_view);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new DiscoverSecondRecyclerAdapter();
        recyclerView.setAdapter(adapter);

    }

    public void onIconClick(View v){
        if(v.getId() == R.id.nearby_bottom_layout_icon){
            final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.nearby_bottom_layout_icon);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
            finish();
            Intent intent = new Intent(this, NearbyActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.discover_bottom_layout_icon){
            final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.discover_bottom_layout_icon);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
            finish();
            Intent intent = new Intent(this, DiscoverActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.friends_bottom_layout_icon){
            final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.friends_bottom_layout_icon);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
            finish();
            Intent intent = new Intent(this, FriendsActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.messages_bottom_layout_icon){
            final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.messages_bottom_layout_icon);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
            finish();
            Intent intent = new Intent(this, MeetupCommentActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.profile_bottom_layout_icon){
            final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.profile_bottom_layout_icon);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
            finish();
            Intent intent = new Intent(this, OtherUserProfileActivity.class);
            startActivity(intent);
        }
    }
}
