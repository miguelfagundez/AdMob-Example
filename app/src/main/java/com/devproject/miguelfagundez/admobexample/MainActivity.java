package com.devproject.miguelfagundez.admobexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

/********************************************
 * Activity - MainActivity
 * This activity shows a Full Banner Ad
 * and a Interstitial Ad
 * @author: Miguel Fagundez
 * @date: May 05th, 2020
 * @version: 1.0
 * *******************************************/
public class MainActivity extends AppCompatActivity {

    // View Members
    private AdView adView;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Views
        setupViews();
        // The App initializes the Mobile Ads SDK
        initMobileAds();
        // Display Ads into this AdView
        displayAds();
    }

    private void setupViews() {
        // Ad View
        adView = findViewById(R.id.adViewMainActivity);
        // Each Ad View MUST have an UnitId
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

         // Interstitial Ads
        interstitialAd = new InterstitialAd(this);
        // Every Interstitial Ad can have the same UnitId
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        //************************************************
        // I prepare the interstitialAd for next launch
        // after it is closed.
        //************************************************
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    private void initMobileAds() {
        MobileAds.initialize(this);
    }

    private void displayAds() {
        // Full Banner Ads
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        // Interstitial Ads
        AdRequest adRequestInterstitial = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequestInterstitial);
    }

    // Button click event
    public void LaunchIntertitial(View view) {
        if (interstitialAd.isLoaded()){
            interstitialAd.show();
        }else{
            Toast.makeText(this, "Ad is not loaded yet!", Toast.LENGTH_SHORT).show();
        }
    }
}
