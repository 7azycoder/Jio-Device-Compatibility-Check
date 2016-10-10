package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.R;
import mehdi.sakout.fancybuttons.FancyButton;


public class FragmentSupportUs extends Fragment {
    private String TAG = jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Fragments.FragmentSupportUs.class.getSimpleName();
    InterstitialAd mInterstitialAd;
    FancyButton showAdBtn;

    public FragmentSupportUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_support_us, container, false);
        final TextView supportUs = (TextView) rootView.findViewById(R.id.tvSupportUs);

        showAdBtn=(FancyButton) rootView.findViewById(R.id.btn_show_ad);

        showAdBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             mInterstitialAd = new InterstitialAd(getActivity());

                                             // set the ad unit ID
                                             mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

                                             AdRequest adRequest = new AdRequest.Builder().addTestDevice("D6E4AA1CFCB962501E8060728AB60A88")
                                                     .build();

                                             // Load ads into Interstitial Ads
                                             mInterstitialAd.loadAd(adRequest);

                                             mInterstitialAd.setAdListener(new AdListener() {
                                                 public void onAdLoaded() {
                                                     showInterstitial();
                                                 }

                                                 @Override
                                                 public void onAdClosed() {
                                                     supportUs.setText("Thanks for Supporting us. You can click few more ads to make a difference. We will be very pleased.");
                                                 }

                                                 @Override
                                                 public void onAdFailedToLoad(int errorCode) {
                                                     Toast.makeText(getActivity(), "Ad Failed to load. Please Check your internet connection." , Toast.LENGTH_SHORT).show();
                                                 }

                                             });
                                         }

                                     });

        return rootView;
    }
        private void showInterstitial() {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
        }
}
