package com.alluresoft.friends;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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

public class DiscoverActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener{

    int[] mResources = {R.drawable.profile_pic, R.drawable.profile_pic, R.drawable.profile_pic};

    ViewPager mViewPager;
    private CustomPagerAdapter mAdapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;

    private ImageView friend1,friend2,friend3,friend4,backArrow;
    private ImageView nearby,followers,profile,discover,favourites;
    private Button attend,shareIt,friend5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        TextView activity_name=(TextView) findViewById(R.id.tab_name);
        activity_name.setText("Discover");
        backArrow=(ImageView) findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic1);
        Bitmap circularBitmap = RoundedImageView.getRoundedCroppedBitmap(bitmap, 100);

        friend1=(ImageView)findViewById(R.id.friend1);
        friend1.setImageBitmap(circularBitmap);

        friend2=(ImageView)findViewById(R.id.friend2);
        friend2.setImageBitmap(circularBitmap);

        friend3=(ImageView)findViewById(R.id.friend3);
        friend3.setImageBitmap(circularBitmap);

        friend4=(ImageView)findViewById(R.id.friend4);
        friend4.setImageBitmap(circularBitmap);

        friend5=(Button)findViewById(R.id.friend5);

        attend=(Button) findViewById(R.id.attend_btn_discover);
        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DiscoverActivity.this,"attend",Toast.LENGTH_SHORT).show();
            }
        });

        shareIt=(Button) findViewById(R.id.share_it_button);
        shareIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });

        //for image sliding
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        mAdapter = new CustomPagerAdapter(this, mResources);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);

        setPageViewIndicator();
        final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.discover_bottom_layout_icon);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));

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
    //sharing implementation here
    private void shareIt() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Share this event on over the web...");
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
  //for image changing dots and image change
    private void setPageViewIndicator() {

        Log.d("###setPageViewIndicator", " : called");
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
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
