package com.alluresoft.friends.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alluresoft.friends.R;
import com.alluresoft.friends.RoundedImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener{

    int[] mResources = {R.drawable.profile_pic, R.drawable.profile_pic, R.drawable.profile_pic};

    ViewPager mViewPager;
    private CustomPagerAdapter mAdapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;

    private ImageView friend1,friend2,friend3,friend4;
    private Button attend,shareIt,friend5;
    Context mContext;
    FragmentActivity context;
    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_discover, container, false);
        TextView tab_name=(TextView) rootView.findViewById(R.id.tab_name);
        tab_name.setText("Discover");
        mContext=container.getContext();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic);
        Bitmap circularBitmap = RoundedImageView.getRoundedCroppedBitmap(bitmap, 100);

        friend1=(ImageView)rootView.findViewById(R.id.friend1);
        friend1.setImageBitmap(circularBitmap);

        friend2=(ImageView)rootView.findViewById(R.id.friend2);
        friend2.setImageBitmap(circularBitmap);

        friend3=(ImageView)rootView.findViewById(R.id.friend3);
        friend3.setImageBitmap(circularBitmap);

        friend4=(ImageView)rootView.findViewById(R.id.friend4);
        friend4.setImageBitmap(circularBitmap);

        friend5=(Button)rootView.findViewById(R.id.friend5);

        attend=(Button) rootView.findViewById(R.id.attend_btn_discover);
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"attend",Toast.LENGTH_SHORT).show();
            }
        });

        shareIt=(Button) rootView.findViewById(R.id.share_it_button);
        shareIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });

        //for image sliding
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) rootView.findViewById(R.id.viewPagerCountDots);
        mAdapter = new CustomPagerAdapter(mContext, mResources);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);

        setPageViewIndicator();
        //final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.discover_bottom_layout_icon);
        //linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));

        return rootView;
    }

    //sharing implementation here
    private void shareIt() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Share this event on over the web...");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private void setPageViewIndicator() {

        Log.d("###setPageViewIndicator", " : called");
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(mContext);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mViewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });


            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Log.d("###onPageSelected, pos ", String.valueOf(position));
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.item_unselected));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.item_selected));

        if (position + 1 == dotsCount) {

        } else {

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //adapter class for dots
    public class CustomPagerAdapter extends PagerAdapter {
        private Context mContext;
        LayoutInflater mLayoutInflater;
        private int[] mResources;

        public CustomPagerAdapter(Context context, int[] resources) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mResources = resources;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View itemView = mLayoutInflater.inflate(R.layout.walk_through_swip_layout,container,false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(mResources[position]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
           /* LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(950, 950);
            imageView.setLayoutParams(layoutParams);*/
            mContext=container.getContext();
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
