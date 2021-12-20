package com.example.reto5;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.osmdroid.config.Configuration;
import org.osmdroid.library.BuildConfig;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class Mapa extends AppCompatActivity {

    ArrayList<OverlayItem> puntos = new ArrayList<OverlayItem>();
    private MapView myOpenMapView;
    private MapController myMapController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        /* ---- necesitamos establecer el valor del agente de usuario en la configuración ------- */
        //pre 5.6
        //OpenStreetMapTileProviderConstants.setUserAgentValue(BuildConfig.APPLICATION_ID);
        //5.6 and newer
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
        /* -------------------------------------------------------------------------------------- */

        /*   punto de geolocalizacion de ejemplo */
        GeoPoint Bogota = new GeoPoint(4.6351, -74.0703);
        GeoPoint Bogota1 = new GeoPoint(4.6351, -74.1703);
        GeoPoint Bogota2 = new GeoPoint(4.6584796, -74.0956466);
        GeoPoint Bogota3 = new GeoPoint(4.6584000, -74.1956466);
        //GeoPoint Madrid = new GeoPoint(40.416775, -3.70379);
        //GeoPoint Sydney = new GeoPoint(5.71666667, -72.92083333);
        //----------------------------------------------------------------------------------

        myOpenMapView = (MapView) findViewById(R.id.openmapview);
        myOpenMapView.setBuiltInZoomControls(true);

        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setCenter(Bogota);
        myMapController.setZoom(12);

        myOpenMapView.setMultiTouchControls(true);
        puntos.add(new OverlayItem("Bogota", "Sucursal Principal", Bogota));
        puntos.add(new OverlayItem("Bogota", "Sucursal Av68", Bogota1));
        puntos.add(new OverlayItem("Bogota", "Parque Simon Bolivar", Bogota2));
        puntos.add(new OverlayItem("Bogota", "Toxy Tour", Bogota3));
        refrescaPuntos();
    }




    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
        @Override
        public boolean onItemLongPress(int arg0, OverlayItem arg1) {
            return false;
        }
        @Override
        public boolean onItemSingleTapUp(int index, OverlayItem item) {
            return true;
        }
    };
    private void refrescaPuntos() {
        myOpenMapView.getOverlays().clear();
        ItemizedIconOverlay.OnItemGestureListener<OverlayItem> tap = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                return false;
            }

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }
        };

        ItemizedOverlayWithFocus<OverlayItem> capa = new ItemizedOverlayWithFocus<>(this, puntos, tap);
        capa.setFocusItemsOnTap(true);
        myOpenMapView.getOverlays().add(capa);
    }
}