package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.R;
import mehdi.sakout.fancybuttons.FancyButton;

public class MakeCompatibleActivity extends AppCompatActivity {

    TextView tvMakeCompatible;
    FancyButton btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_compatible);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Make Device Compatible");

        tvMakeCompatible=(TextView)findViewById(R.id.activity_make_compatible_tv_message);
        btnDownload=(FancyButton)findViewById(R.id.activity_make_compatible_btn_download);

        String msg="Your device might not have one of the following feature(s) : \n\n";
        msg=msg+"1. Long Term Evolution (LTE)\n";
        msg=msg+"2. Voice over LTE (VoLTE) \n\n";
        msg=msg+"but you can make your device fully compatible by installing this official application from Reliance JIO";

        tvMakeCompatible.setText(msg);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("market://details?id="+"com.jio.join");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    //getActivity().showAlertDialog(getActivity(), ERROR, "Couldn't launch the market", null, 0);
                    Toast.makeText(getApplicationContext(), "cannot open", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
