package com.devlovepreet.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FAQsFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<FAQs> list = new ArrayList<FAQs>();

    String[] ques,ans;


    public FAQsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_faqs, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        ques = getResources().getStringArray(R.array.ques);
        ans = getResources().getStringArray(R.array.ans);


        int count=0;
        for(String Ques : ques)
        {
            FAQs faq = new FAQs(Ques,ans[count]);
            count++;
            list.add(faq);
        }

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new FAQsAdapter(list);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

}
