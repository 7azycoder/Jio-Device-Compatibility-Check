package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.R;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Codev on 9/18/2016.
 */
public class FragmentRateAndShareUs extends Fragment implements View.OnClickListener{

    View v;
    FancyButton btnRateUs, btnShareUs;

    public FragmentRateAndShareUs()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_rate_and_shareus, container, false);

        btnRateUs=(FancyButton)v.findViewById(R.id.fragment_rate_and_shareus_btn_rateus);
        btnShareUs=(FancyButton)v.findViewById(R.id.fragment_rate_and_shareus_btn_shareus);
        btnRateUs.setOnClickListener(this);
        btnShareUs.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.fragment_rate_and_shareus_btn_rateus:

                Uri uri = Uri.parse("market://details?id="+"jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    getActivity().startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    //getActivity().showAlertDialog(getActivity(), ERROR, "Couldn't launch the market", null, 0);
                    Toast.makeText(getActivity(), "cannot open", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.fragment_rate_and_shareus_btn_shareus:

                try
                { Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Jio Device Compatibility Check");
                    String sAux = "\n Jio Device Check \n\n An app, which lets you check whether your phone is compatible for Jio sim or not (data services, call services, etc)\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                }
                catch(Exception e)
                { //e.toString();
                }

                break;
        }

    }
}
