package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.vstechlab.easyfonts.EasyFonts;

import java.io.InputStream;
import java.util.List;

import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Database.DeviceSQLiteOpenHelper;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Others.CSVFile;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Others.Device;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.R;

public class SplashActivity extends Activity {

    TextView tvTitle, tvSubTitle;
    DeviceSQLiteOpenHelper helper;
    public static final String SP_FIRST_TIME_CHECK="sharedpreference_for_checking_first_time";
    public static final String SP_ON_BACKPRESSED_CHECK="sharedpreference_for_checking_back_pressed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        tvTitle=(TextView)findViewById(R.id.tvSplashTitle);
        tvSubTitle=(TextView)findViewById(R.id.tvSplashSubTitle);
        helper=new DeviceSQLiteOpenHelper(this);

        tvTitle.setTypeface(EasyFonts.caviarDreams(this));

        SharedPreferences.Editor editorr = getSharedPreferences(SP_ON_BACKPRESSED_CHECK, MODE_PRIVATE).edit();
        editorr.putInt("on_back_pressed_int", 0);
        editorr.commit();

        //tvSubTitle.setTypeface(EasyFonts.caviarDreamsBold(this));

        checkForFirstTime();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkForFirstTime();

                Intent i=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(i);
            }
        }, 1500);

    }

    @Override
    protected void onResume() {

        super.onResume();

        SharedPreferences prefss = getSharedPreferences(SP_ON_BACKPRESSED_CHECK, MODE_PRIVATE);
        int Value = prefss.getInt("on_back_pressed_int", 0);

        if(Value==5)
        {
            super.finish();
        }

    }

    private void checkForFirstTime()
    {
        SharedPreferences prefs = getSharedPreferences(SP_FIRST_TIME_CHECK, MODE_PRIVATE);
        int checkValue = prefs.getInt("first_time_check", 0);

        if (checkValue==0) {
            //means its the first time app is opened
            //so we will write in database from the csv file



//            SharedPreferences.Editor editor = getSharedPreferences(SP_FIRST_TIME_CHECK, MODE_PRIVATE).edit();
//            editor.putInt("first_time_check", 1);
//            editor.commit();


            ReadFromCSVAndSaveInDatabase();
        }
    }

    private void ReadFromCSVAndSaveInDatabase()
    {
        InputStream inputStream = getResources().openRawResource(R.raw.devicelist);
        CSVFile csvFile = new CSVFile(inputStream);
        List<Device> deviceList = csvFile.read();

        for(int i=0; i<deviceList.size(); i++)
        {
            Device device=deviceList.get(i);

            ContentValues cv = new ContentValues();
            SQLiteDatabase db=helper.getWritableDatabase();

            cv.put(DeviceSQLiteOpenHelper.BRAND, device.getBrandName());
            cv.put(DeviceSQLiteOpenHelper.DEVICENAME, device.getDeviceName());
            cv.put(DeviceSQLiteOpenHelper.DATAOFFER, device.getDataCheck());
            cv.put(DeviceSQLiteOpenHelper.CALLOFFER, device.getCallCheck());
            cv.put(DeviceSQLiteOpenHelper.JIOOFFFER, device.getOfferCheck());

            db.insert(DeviceSQLiteOpenHelper.TABLE_NAME, null, cv);

        }

    }


}
