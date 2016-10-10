package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Others.FAQs;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Others.FAQsAdapter;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.R;

/**
 * Created by Codev on 9/18/2016.
 */
public class FragmentFAQ extends Fragment{

    View v;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<FAQs> list = new ArrayList<FAQs>();

    String[] ques,ans;

    public FragmentFAQ()
    {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

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
