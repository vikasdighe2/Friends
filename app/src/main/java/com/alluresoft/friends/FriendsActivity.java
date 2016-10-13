package com.alluresoft.friends;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FriendsAdapter adapter;
    private List<Album> albumList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        TextView tab_name=(TextView) findViewById(R.id.tab_name);
        tab_name.setText("My Friends");
        ImageView backArrow=(ImageView) findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.friends_recycler_view);

        albumList = new ArrayList<>();
        adapter = new FriendsAdapter(this, albumList);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
        final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.friends_bottom_layout_icon);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));

    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.profile_pic1,
                R.drawable.profile_pic1,
                R.drawable.profile_pic1,
                R.drawable.profile_pic1,
                R.drawable.profile_pic1,
                R.drawable.profile_pic1,
                R.drawable.profile_pic1,
                R.drawable.profile_pic1,
                R.drawable.profile_pic1,
                R.drawable.profile_pic1,
                R.drawable.profile_pic1};

        Album a = new Album("Ayup Teckbas", 13, covers[0]);
        albumList.add(a);

        a = new Album("Vikas Dighe", 8, covers[1]);
        albumList.add(a);

        a = new Album("Sagar Nawale", 11, covers[2]);
        albumList.add(a);

        a = new Album("Roam Dany", 12, covers[3]);
        albumList.add(a);

        a = new Album("Hony Singh", 14, covers[4]);
        albumList.add(a);

        a = new Album("Badshah", 1, covers[5]);
        albumList.add(a);

        a = new Album("Shreya Legend", 11, covers[6]);
        albumList.add(a);

        a = new Album("Compute Goal", 14, covers[7]);
        albumList.add(a);

        a = new Album("Pamp Sam", 11, covers[8]);
        albumList.add(a);

        a = new Album("Ram Chem", 17, covers[9]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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
