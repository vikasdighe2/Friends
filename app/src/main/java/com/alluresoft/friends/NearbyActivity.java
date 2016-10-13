package com.alluresoft.friends;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class NearbyActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    JSONArray latlogResult;
    InputStream is=null;
    String result=null;
    String line=null;
    private String user_name="Person Name";
    private String current_location="Nasik",current_state="Maharashtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
        }

        TextView activity_name=(TextView) findViewById(R.id.tab_name);
        activity_name.setText("Nearby");
        ImageView backArrow=(ImageView) findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.nearby_bottom_layout_icon);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        GPSTracker mGPS = new GPSTracker(this);
        // check if GPS enabled
        GPSTracker gpsTracker = new GPSTracker(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic1);
        Bitmap circularBitmap = RoundedImageView.getRoundedCroppedBitmap(bitmap, 100);

        if (gpsTracker.getIsGPSTrackingEnabled())
        {

            LatLng currentLocation = new LatLng( gpsTracker.latitude, gpsTracker.longitude);
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
            mMap.addMarker(new MarkerOptions().position(currentLocation).icon(bitmapDescriptor));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
            mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
        }

        JSONArray getLatLongResult = getLatitudeAndLongitude();
        try {
            for (int i = 0; i < getLatLongResult.length(); i++) {
                JSONObject object3 = getLatLongResult.getJSONObject(i);
                String latitude = object3.getString("latitude");
                String longitude = object3.getString("longitude");
                LatLng loc1 = new LatLng( Double.parseDouble(latitude),  Double.parseDouble(longitude));
                mMap.addMarker(new MarkerOptions().position(loc1).icon(BitmapDescriptorFactory.fromBitmap(circularBitmap)));
            }
        }  catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    //bottom icon listener
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
    public JSONArray getLatitudeAndLongitude()
    {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("id","1"));

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://192.168.1.104:8080/select.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.d("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Server Exception",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            JSONObject json_data = new JSONObject(result);
            latlogResult = (json_data.getJSONArray("result"));
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
        return latlogResult;
    }
    // Added custom window class for map

    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter
    {
        public MarkerInfoWindowAdapter()
        {
        }

        @Override
        public View getInfoWindow(Marker marker)
        {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker)
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic1);
            Bitmap circularBitmap = RoundedImageView.getRoundedCroppedBitmap(bitmap, 50);
            View v  = getLayoutInflater().inflate(R.layout.infowindow_layout, null);

            ImageView markerIcon = (ImageView) v.findViewById(R.id.marker_icon);
            TextView markerLabel = (TextView)v.findViewById(R.id.marker_label);
            TextView user_location_name=(TextView) v.findViewById(R.id.user_location_name);

            markerIcon.setImageBitmap(circularBitmap);

            markerLabel.setText(""+ user_name);
            user_location_name.setText("" + current_location + "" + current_state);



            return v;
        }
    }
}
