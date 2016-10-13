package com.alluresoft.friends.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alluresoft.friends.GPSTracker;
import com.alluresoft.friends.R;
import com.alluresoft.friends.RoundedImageView;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class NearbyFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    JSONArray latlogResult;
    InputStream is=null;
    String result=null;
    String line=null;
    private String user_name="Person Name";
    private String current_location="Nasik",current_state="Maharashtra";
    Context mContext;

    public NearbyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView= inflater.inflate(R.layout.fragment_nearby, container, false);
        mContext=container.getContext();
        TextView tabName=(TextView) rootView.findViewById(R.id.tab_name);
        tabName.setText("Nearby");
        if( Build.VERSION.SDK_INT >= 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

      //  final LinearLayout linearLayout=(LinearLayout) findViewById(R.id.nearby_bottom_layout_icon);
      //  linearLayout.setBackgroundColor(getResources().getColor(R.color.bottom_layout_select_tab));
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
       // GPSTracker mGPS = new GPSTracker(this);
        // check if GPS enabled
        GPSTracker gpsTracker = new GPSTracker(mContext);
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
            Toast.makeText(mContext, "Server Exception",
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
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            View v = inflater.inflate( R.layout.infowindow_layout, null );
            //View v  = getLayoutInflater().inflate(R.layout.infowindow_layout, null);

            ImageView markerIcon = (ImageView) v.findViewById(R.id.marker_icon);
            TextView markerLabel = (TextView)v.findViewById(R.id.marker_label);
            TextView user_location_name=(TextView) v.findViewById(R.id.user_location_name);

            markerIcon.setImageResource(R.drawable.profile_pic1);

            markerLabel.setText(""+ user_name);
            user_location_name.setText("" + current_location + "" + current_state);
            return v;
        }
    }

}
