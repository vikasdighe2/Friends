package com.alluresoft.friends.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alluresoft.friends.MessagesRecyclerAdapter;
import com.alluresoft.friends.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Context mContext;

    public MessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_messages, container, false);
        TextView tab_name=(TextView) rootView.findViewById(R.id.tab_name);
        tab_name.setText("Comments");
        ImageView backArrow=(ImageView) rootView.findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //super.onBackPressed();
                //getFragmentManager().popBackStack();
            }
        });
        mContext=container.getContext();
        recyclerView=(RecyclerView) rootView.findViewById(R.id.message_recycler_view);
        layoutManager=new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new MessagesRecyclerAdapter();
        recyclerView.setAdapter(adapter);

      //  final LinearLayout linearLayout=(LinearLayout) rootView.findViewById(R.id.messages_bottom_layout_icon);
      //  linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));

        return rootView;
    }

}
