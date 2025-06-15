package com.example.testmap;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;
    private NaverMap naverMap;
    InfoWindow infoWindow = new InfoWindow();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.5757745627, 127.0858494168)); // 서울 시청
        naverMap.moveCamera(cameraUpdate);
        naverMap.setMapType(NaverMap.MapType.Basic);
        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRAFFIC, true);
        //naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_TRANSIT, true);



        double[] arr = {37.5757745627,127.0858494168,
                37.5786342777,127.0863248288,
                37.579857,127.088067,
                37.5820548439,127.0887178358,
                37.5842573049,127.0884543619,
                37.586909,127.088244,
                37.5891662321,127.0875225751,
                37.5913436688,127.0869725161,
                37.5943971396,127.0861765659,
                37.6004378473,127.0876490313,
                37.601538208,127.0874691143,
                37.6031155767,127.0880531041,
                37.603949,127.090003,
                37.6047917401,127.0914211649,
                37.6062056111,127.0936349035,
                37.605617,127.095212,
                37.6039568351,127.0954657423,
                37.5994734943,127.0971021652,
                37.598848,127.09539,
                37.5962266864,127.092859113,
                37.594427,127.09505,
                37.595025,127.097837

        };

        String[] str = {"방약국앞","면남초등학교","지하철7호선사가정역","면목우체국.녹색병원","면동초등학교","면목본동주민센터.면목정보도서관","지하철7호선면목역","면목초등학교","상봉역3번출구","상봉1동주민센터",
        "신내쌍용아파트","쌍용아파트.신내테크노타운앞","엘지아파트앞","신현중학교","중랑구청","중랑구청사거리","성원아파트.경남아너스빌앞","신내지하차도","망우역.망우지구대","상봉터미널","혜원여중고입구","봉우재고개","우림시장"};
        for(int i = 0; i < arr.length; i += 2){
            Marker marker = new Marker();
            marker.setPosition(new LatLng(arr[i],arr[i+1]));
            marker.setMap(naverMap);
            marker.setIcon(OverlayImage.fromResource(R.drawable.busicon));

            int finalI = i / 2;  // 람다식 안에서 사용하려면 변수 final 로 복사
            marker.setOnClickListener(overlay -> {
                infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(getApplicationContext()) {
                    @NonNull
                    @Override
                    public CharSequence getText(@NonNull InfoWindow infoWindow) {
                        return "정류장 위치: " + str[finalI];

                    }
                });
                infoWindow.open(marker);
                return true;
            });
        }


    }
}

