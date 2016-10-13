package com.alluresoft.friends.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alluresoft.friends.R;

public class OneFragment extends Fragment {
    private ImageView friend1,friend2,friend3,friend4;
    private Button attend,shareIt,friend5;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_one, container, false);
        friend1=(ImageView)root.findViewById(R.id.friend1);
        //friend1.setImageResource(R.id.profile_pic1);

        friend2=(ImageView)root.findViewById(R.id.friend2);
        //friend2.setImageBitmap(circularBitmap);

        friend3=(ImageView)root.findViewById(R.id.friend3);
        //friend3.setImageBitmap(circularBitmap);

        friend4=(ImageView)root.findViewById(R.id.friend4);
        //friend4.setImageBitmap(circularBitmap);

        attend=(Button) root.findViewById(R.id.attend_btn_discover);
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"attend",Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }

}
