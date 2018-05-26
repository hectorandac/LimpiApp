package com.limpiapp.hecto.limpiapp;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    String[] time = {"9-10", "11-13", "8-7", "7-8", "15-16", "23-01", "01-02"};
    public Sector[] sectores = {
            new Sector("Matahambre", new LatLng(18.471866, -69.928696), 0, time),
            new Sector("Enriquillo", new LatLng(18.469260, -69.920800), 1, time),
            new Sector("Buenos Aires", new LatLng(18.473249, -69.956591), 1, time),
            new Sector("Jose contreras", new LatLng(18.468691, -69.951098), 0, time),
            new Sector("16 de agosto", new LatLng(18.463317, -69.944145), 2, time),
            new Sector("Caciques", new LatLng(18.461445, -69.927323), 0, time),
            new Sector("Honduras", new LatLng(18.486518, -69.909899), 2, time),
            new Sector("Jardines del sur", new LatLng(18.464457, -69.900973), 0, time),
            new Sector("Tropical", new LatLng(18.477727, -69.894278), 1, time),
            new Sector("Miramar Este", new LatLng(18.472598, -69.885781), 2, time),
            new Sector("Costa Caribe", new LatLng(18.491370, -69.890557), 0, time),
            new Sector("30 de Mayo", new LatLng(18.495355, -69.926874), 1, time),
            new Sector("El Portal", new LatLng(18.494442, -69.833447), 2, time),
            new Sector("La Aurora", new LatLng(18.491919, -69.857394), 1, time),
            new Sector("Dominicanos Ausentes", new LatLng(18.500791, -69.857137), 1, time)
    };

    private GoogleMap mMap;
    private View view_map;
    private int small;
    private int big;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        view_map = findViewById(R.id.map_container);

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
// Get the string array
        String[] countries = getResources().getStringArray(R.array.barrios_array);
// Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);
    }

    private boolean isBig = true;

    public void show(View v) {

        if (!isBig) {
            ValueAnimator va = ValueAnimator.ofInt(small, big);
            va.setDuration(400);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    view_map.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                    view_map.requestLayout();
                }
            });
            va.start();
            isBig = true;
        } else {
            ValueAnimator va = ValueAnimator.ofInt(big, small);
            va.setDuration(400);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    view_map.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                    view_map.requestLayout();
                }
            });
            va.start();
            isBig = false;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_map));

            if (!success) {
                Log.e("MAP_R", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("MAP_R", "Can't find style. Error: ", e);
        }

        mMap = googleMap;
        LatLng coordinate = new LatLng(18.4861, -69.9312);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 11);
        mMap.animateCamera(yourLocation);

        for (Sector sec : sectores) {

            int a = R.drawable.garbage_truck_yellow;
            switch (sec.level) {
                case 0: {
                    a = R.drawable.garbage_truck_green;
                    break;
                }
                case 1: {
                    a = R.drawable.garbage_truck_yellow;
                    break;
                }
                case 2: {
                    a = R.drawable.garbage_truck_red;
                    break;
                }
            }
            int height = 45;
            int width = 70;
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(a);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            mMap.addMarker(new MarkerOptions()
                    .position(sec.location)
                    .title(sec.time[1])
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
        }

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) view_map.getLayoutParams();
        params.height = (int) (outMetrics.heightPixels - (80 * density));
        System.out.println(outMetrics.widthPixels / density);
        view_map.setLayoutParams(params);

        small = (int) (80 * density);
        big = (int) (outMetrics.heightPixels - (80 * density));

    }

    public void openGoogleMpas(View view) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", 18.4861, -69.9312);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}
