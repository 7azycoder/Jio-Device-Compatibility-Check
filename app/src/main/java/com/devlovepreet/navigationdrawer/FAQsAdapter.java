package com.devlovepreet.navigationdrawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by devlovepreet on 9/9/16.
 */
public class FAQsAdapter extends RecyclerView.Adapter<FAQsAdapter.FAQsViewHolder>{
    ArrayList<FAQs> faqs = new ArrayList<FAQs>();

    public FAQsAdapter(ArrayList<FAQs> faqs)
    {
        this.faqs = faqs;
    }
    @Override
    public FAQsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);
        FAQsViewHolder faQsViewHolder = new FAQsViewHolder(view);
        return faQsViewHolder;
    }

    @Override
    public void onBindViewHolder(FAQsViewHolder holder, int position) {
        FAQs f = faqs.get(position);
        holder.ques.setText(f.getQues());
        holder.ans.setText(f.getAns());
    }

    @Override
    public int getItemCount() {
        return faqs.size();
    }
    public  static class FAQsViewHolder extends RecyclerView.ViewHolder
    {
        TextView ques,ans;
        public  FAQsViewHolder(View view)
        {
            super(view);
            ques = (TextView) view.findViewById(R.id.ques);
            ans = (TextView) view.findViewById(R.id.ans);
        }
    }
}
