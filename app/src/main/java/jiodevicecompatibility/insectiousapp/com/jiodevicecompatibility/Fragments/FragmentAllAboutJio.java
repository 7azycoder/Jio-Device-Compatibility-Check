package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.R;

/**
 * Created by Codev on 9/18/2016.
 */
public class FragmentAllAboutJio extends Fragment {
    public FragmentAllAboutJio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_aboutjio, container, false);
        TextView aboutJio = (TextView) rootView.findViewById(R.id.tvAboutJio);
        String str = "Reliance Jio Infocomm Limited (“Jio”), a subsidiary of Reliance Industries Limited (“RIL”), has built a" +
                " world-class all-IP data strong future proof network with latest 4G LTE technology. It is the only network" +
                " conceived and born as a Mobile Video Network from the ground up and supporting Voice over LTE" +
                " technology. It is future ready and can be easily upgraded to support even more data, as technologies" +
                " advance on to 5G, 6G and beyond.\n\n";
        str=str+" Jio will bring transformational changes in the Indian digital services space to enable the vision of Digital" +
                " India for 1.2 billion Indians and propel India into global leadership in digital economy. It has created an" +
                " eco-system comprising network, devices, applications and content, service experience and affordable" +
                " tariffs for everyone to live the Jio Digital Life. As part of its customer offers, Jio has revolutionised the" +
                " Indian telecom landscape by making voice calls for Jio customers absolutely free, across India, to any" +
                " network, and always. Jio makes India the highest quality, most affordable data market in the world so" +
                " that every Indian can do Datagiri\n";
        aboutJio.setText(str);
        return rootView;
    }
}
