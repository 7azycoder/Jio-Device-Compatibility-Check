package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.paolorotolo.appintro.AppIntro2;

import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Others.SampleSlide;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.R;

public class HelpActivity extends AppIntro2 {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_help);
//    }

    SharedPreferences sharedPreferencesForFirstTime;

    @Override
    public void init(Bundle bundle) {

        addSlide(SampleSlide.newInstance(R.layout.screen1));
        addSlide(SampleSlide.newInstance(R.layout.screen2));
        addSlide(SampleSlide.newInstance(R.layout.screen3));
        addSlide(SampleSlide.newInstance(R.layout.screen4));
        addSlide(SampleSlide.newInstance(R.layout.screen5));


        // setBarColor(Color.parseColor("#FF5722"));
        //setSeparatorColor(Color.parseColor("#FF5722"));

        //showSkipButton(true);
        setProgressButtonEnabled(true);




    }

//    @Override
//    public void onSkipPressed() {
//
//        //firsttimeopened();
//        finish();
//
//    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {


        //firsttimeopened();
        finish();

    }


    @Override
    public void onSlideChanged() {

        // setBarColor(Color.parseColor("#8A56D2"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

