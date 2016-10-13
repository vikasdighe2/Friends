package com.alluresoft.friends.fragments;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alluresoft.friends.Album;
import com.alluresoft.friends.FriendsAdapter;
import com.alluresoft.friends.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {

    private RecyclerView recyclerView;
    private FriendsAdapter adapter;
    private List<Album> albumList;

    FragmentActivity myContext;

    public FriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_friends, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.friends_recycler_view);
        TextView tab_name=(TextView) rootView.findViewById(R.id.tab_name);
        tab_name.setText("My Friends");
        albumList = new ArrayList<>();
        adapter = new FriendsAdapter(myContext, albumList);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(myContext, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
  //      final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.friends_bottom_layout_icon);
    //    linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));

        return rootView;
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


}
