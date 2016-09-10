package com.devlovepreet.navigationdrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutJioFragment extends Fragment {
    public AboutJioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_aboutjio, container, false);
        TextView aboutJio = (TextView) rootView.findViewById(R.id.tvAboutJio);
        String str = "Dhoni holds numerous captaincy records such as most wins by an Indian captain in Tests and ODIs, and most back-to-back wins by an Indian captain in ODIs. He took over the ODI captaincy from Rahul Dravid in 2007 and led the team to its first-ever bilateral ODI series wins in Sri Lanka and New Zealand. Under his captaincy, India won the 2007 ICC World Twenty20, the CB Series of 2007–08, the 2010 Asia Cup, the 2011 ICC Cricket World Cup and the 2013 ICC Champions Trophy. In the final of the 2011 World Cup, Dhoni scored 91 not out off 79 balls to take India to victory for which he was awarded the Man of the Match. In June 2013, when India defeated England in the final of the Champions Trophy in England, Dhoni became the first captain to win all three ICC limited-overs trophies (World Cup, Champions Trophy and the World Twenty20). After taking up the Test captaincy in 2008, he led the team to series wins in New Zealand and West Indies, and the Border-Gavaskar Trophy in 2008, 2010 and 2013. In 2009, Dhoni also led the Indian team to number one position for the first time in the ICC Test rankings. In 2013, under his captaincy, India became the first team in more than 40 years to whitewash Australia in a Test series. In the Indian Premier League, he captained the Chennai Super Kings to victory at the 2010 and 2011 seasons, along with wins in the 2010 and 2014 editions of Champions League Twenty20.";
        str += "Dhoni has been the recipient of many awards, including the ICC ODI Player of the Year award in 2008 and 2009 (the first player to win the award twice), the Rajiv Gandhi Khel Ratna award in 2007 and the Padma Shri, India's fourth highest civilian honour, in 2009.[9] He was named as the captain of ICC World Test XI and ICC World ODI XI teams for 2009. The Indian Territorial Army conferred the honorary rank of Lieutenant Colonel[10] to Dhoni on 1 November 2011. He is the second Indian cricketer after Kapil Dev to have received this honour. In 2011, Time magazine included Dhoni in its annual Time 100 list as one of the \"Most Influential People in the World.\"[11] In 2012, SportsPro rated Dhoni as the sixteenth most marketable athlete in the world.[12] In June 2015, Forbes ranked Dhoni at 23rd in the list of highest paid athletes in the world, estimating his earnings at US$31 million.[13]";
        aboutJio.setText(str);
        return rootView;
    }
}
