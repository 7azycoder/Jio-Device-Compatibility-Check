package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Fragments.FragmentAllAboutJio;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Fragments.FragmentCompatibilityCheck;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Fragments.FragmentFAQ;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Fragments.FragmentRateAndShareUs;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Fragments.FragmentSupportUs;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.R;

public class MenuActivity extends ActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int fragmentCheckCounter=0;
    public static final String SP_ON_BACKPRESSED_CHECK="sharedpreference_for_checking_back_pressed";
    public static final String SP_FIRST_TIME_CHECK="sharedpreference_for_checking_first_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        replaceWithCompaitiblityCheckFragment();


        //shared preferences for back pressed finish
        SharedPreferences.Editor editorr = getSharedPreferences(SP_ON_BACKPRESSED_CHECK, MODE_PRIVATE).edit();
        editorr.putInt("on_back_pressed_int", 5);
        editorr.commit();

        //appIntro
        SharedPreferences prefs = getSharedPreferences(SP_FIRST_TIME_CHECK, MODE_PRIVATE);
        int checkValue = prefs.getInt("first_time_check", 0);

        if (checkValue==0) {
            //means its the first time app is opened
            //so we will write in database from the csv file

            SharedPreferences.Editor editor = getSharedPreferences(SP_FIRST_TIME_CHECK, MODE_PRIVATE).edit();
            editor.putInt("first_time_check", 1);
            editor.commit();

            Intent i=new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(i);

        }




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if(fragmentCheckCounter==1)
            {
                super.onBackPressed();
            }
            else
            {
                replaceWithCompaitiblityCheckFragment();
            }
        }
    }

    public void replaceWithCompaitiblityCheckFragment()
    {
        fragmentCheckCounter=1;
        FragmentCompatibilityCheck fragmentCompatibilityCheck=new FragmentCompatibilityCheck();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content_menu, fragmentCompatibilityCheck).commit();
        setTitle("Compatibility Check");
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_comaptibility_check) {
            // Handle the camera action
                fragmentCheckCounter=1;
                replaceWithCompaitiblityCheckFragment();
        }
        else if(id==R.id.nav_faq)
        {
            fragmentCheckCounter=2;
            FragmentFAQ fragmentFAQ=new FragmentFAQ();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content_menu, fragmentFAQ).commit();
            setTitle("FAQ's");
        }
        else if(id==R.id.nav_all_about_jio)
        {
            fragmentCheckCounter=3;
            FragmentAllAboutJio fragmentAllAboutJio=new FragmentAllAboutJio();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content_menu, fragmentAllAboutJio).commit();
            setTitle("All About JIO");
        }
        else if(id==R.id.nav_rate_and_share)
        {
            fragmentCheckCounter=4;
            FragmentRateAndShareUs fragmentRateAndShareUs=new FragmentRateAndShareUs();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content_menu, fragmentRateAndShareUs).commit();
            setTitle("Rate and Share");
        }
        else if(id==R.id.nav_support_us){
            fragmentCheckCounter=5;
            FragmentSupportUs fragmentSupportUs=new FragmentSupportUs();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content_menu, fragmentSupportUs).commit();
            setTitle("Support Us");
        }
        else
        {
            setTitle("Menu");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
