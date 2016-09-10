package com.devlovepreet.navigationdrawer;

/**
 * Created by devlovepreet on 9/9/16.
 */
public class FAQs {
    public FAQs(String ques, String ans)
    {
        this.setQues(ques);
        this.setAns(ans);
    }

    private String ques,ans;

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
