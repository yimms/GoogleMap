package com.example.googlemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng latLng = new LatLng(37.1785603, 128.1976598);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("세명대 한방병원");
        googleMap.addMarker(markerOptions);

        LatLng a1 = new LatLng(37.14023473, 128.1984099);
        googleMap.addMarker(new MarkerOptions().position(a1).title("혜민서한의원"));

        LatLng a2 = new LatLng(37.1402039, 128.1990649);
        googleMap.addMarker(new MarkerOptions().position(a2).title("강남한의원"));

        LatLng a3 = new LatLng(37.1399501, 128.1999366);
        googleMap.addMarker(new MarkerOptions().position(a3).title("구침한의원"));

        LatLng a4 = new LatLng(37.1393295, 128.2126948);
        googleMap.addMarker(new MarkerOptions().position(a4).title("우리한의원"));

        LatLng a5 = new LatLng(37.1384692, 128.2115555);
        googleMap.addMarker(new MarkerOptions().position(a5).title("자연한의원"));

        LatLng a6 = new LatLng(37.1388398, 128.2114035);
        googleMap.addMarker(new MarkerOptions().position(a6).title("의림한의원"));

        LatLng a7 = new LatLng(37.136624, 128.2113501);
        googleMap.addMarker(new MarkerOptions().position(a7).title("바른한의원"));

        LatLng a8 = new LatLng(37.1370542, 128.2125567);
        googleMap.addMarker(new MarkerOptions().position(a8).title("세명한의원"));

        LatLng a9 = new LatLng(37.1388471, 128.211883);
        googleMap.addMarker(new MarkerOptions().position(a9).title("동의보감한의원"));

        LatLng a10 = new LatLng(37.13544, 128.2113333);
        googleMap.addMarker(new MarkerOptions().position(a10).title("명진한의원"));

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            checkLocationPermissionWithRationale();
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermissionWithRationale() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle("위치정보")
                        .setMessage("이 앱을 사용하기 위해서는 위치정보에 접근이 필요합니다. 위치정보 접근을 허용하여 주세요.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        }).create().show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        googleMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}

