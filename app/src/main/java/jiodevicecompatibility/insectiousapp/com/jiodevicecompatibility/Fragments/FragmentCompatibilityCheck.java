package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Activities.MakeCompatibleActivity;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Database.DeviceSQLiteOpenHelper;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Others.Device;
import jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.R;

/**
 * Created by Codev on 9/14/2016.
 */
public class FragmentCompatibilityCheck extends Fragment implements View.OnClickListener{

    View v;
    private String[] brandList;
    TextView tvDeviceName, tvBrandName, tvDataCheck, tvCallCheck, tvOfferCheck, tvFinalMessage, tvMakeCompatible;
    String itemDevice, itemBrand;
    SearchableSpinner sBrand, sDevice;
    String sDataCheck, sCallCheck, sOfferCheck;
    Boolean bDataCheck, bCallCheck, bOfferCheck;
    ImageView ivData, ivCall, ivOffer;

    InterstitialAd mInterstitialAd;

    List<String> deviceNamesList;
    List<String> spinnerList;
    ArrayAdapter<String> adapterForBrandList, adapterForDeviceList;

    DeviceSQLiteOpenHelper helper;

    public FragmentCompatibilityCheck()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.fragment_compatibilitycheck, container, false);

        tvBrandName=(TextView)v.findViewById(R.id.fragment_compatibility_check_tv_brandname);
        tvDeviceName=(TextView)v.findViewById(R.id.fragment_compatibility_check_tv_devicename);
        tvDataCheck=(TextView)v.findViewById(R.id.fragment_compatibility_check_tv_dataCheck);
        tvCallCheck=(TextView)v.findViewById(R.id.fragment_compatibility_check_tv_callCheck);
        tvOfferCheck=(TextView)v.findViewById(R.id.fragment_compatibility_check_tv_offerCheck);
        tvFinalMessage=(TextView)v.findViewById(R.id.fragment_compatibility_check_tv_finalMessage);
        tvMakeCompatible=(TextView)v.findViewById(R.id.fragment_compatibility_check_tv_makeCompatibleMessage);
        ivData=(ImageView)v.findViewById(R.id.fragment_compatibility_check_iv_data);
        ivCall=(ImageView)v.findViewById(R.id.fragment_compatibility_check_iv_call);
        ivOffer=(ImageView)v.findViewById(R.id.fragment_compatibility_check_iv_offer);

        tvMakeCompatible.setOnClickListener(this);
        ivData.setOnClickListener(this);
        ivCall.setOnClickListener(this);
        ivOffer.setOnClickListener(this);

        helper=new DeviceSQLiteOpenHelper(getActivity());

        deviceNamesList=new ArrayList<String>();
        spinnerList=new ArrayList<String>();

        this.brandList = new String[] {
                "No Brand Selected", "Acer", "Alcatel", "Asus", "Benq", "BlackBerry" , "BLU","Celkon","Coolpad","Elephone","Gionee","HTC","Huawei","iBall","Iberry","Infocus","Intex","Itel","Karbonn","Kult","Lava","LeEco","Lenovo","LG","LYF","Meizu","Micromax","Microsoft","Motorola","Oneplus","Onida","Oppo","Panasonic","Pepsi","Phicomm","Philips","Qiku","Samsung","Smartron","Sony","Swipe","TCL","TP-LINK","Videocon","Vivo","Wibridge","Wickedleak","Wor(l)d","Xiaomi","Xolo","Zopo","ZTE"
        };

        //for first spinner, which contains the list of all the available brands
        sBrand = (SearchableSpinner)v.findViewById(R.id.sp_brand);
        adapterForBrandList = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_layout, brandList);
        sBrand.setAdapter(adapterForBrandList);
        sBrand.setTitle("Select Item");
        sBrand.setPositiveButton("OK");
        sBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemBrand=sBrand.getSelectedItem().toString();
                tvBrandName.setText(itemBrand);
                changeDeviceListAccordingToSelectedBrand(itemBrand);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ////for second spinner, it contains list of devices for the selected brand
        sDevice = (SearchableSpinner)v.findViewById(R.id.sp_device);
        adapterForDeviceList = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_layout, deviceNamesList);
        sDevice.setAdapter(adapterForDeviceList);
        sDevice.setTitle("Select Item");
        sDevice.setPositiveButton("OK");

        sDevice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemDevice=sDevice.getSelectedItem().toString();
                itemBrand=sBrand.getSelectedItem().toString();
                tvDeviceName.setText(itemDevice);

                Device device=getDeviceDetailFromDatbase(itemBrand, itemDevice);
                setTextViews(device);

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return v;
    }

    private void setTextViews(Device device)
    {

        bDataCheck=false;
        bCallCheck=false;
        bOfferCheck=false;
        sDataCheck="Unavailable";
        sCallCheck="Unavailable";
        sOfferCheck="Unavailable";

        if(device.getDataCheck().contentEquals("Yes"))
        {
            sDataCheck="Available";
            bDataCheck=true;
        }

        if(device.getCallCheck().contentEquals("Yes"))
        {
            sCallCheck="Available";
            bCallCheck=true;
        }

        if(device.getOfferCheck().contentEquals("Yes"))
        {
            sOfferCheck="Available";
            bOfferCheck=true;
        }
        tvDataCheck.setText(sDataCheck);
        tvCallCheck.setText(sCallCheck);
        tvOfferCheck.setText(sOfferCheck);

        String msg;

        if((!bDataCheck)&&(!bCallCheck)&&(!bOfferCheck))
        {
            msg="None of the services are available";
        }
        else
        {
            msg=" ";
            if(bDataCheck)
                msg=msg+"You can enjoy full data services ";

            if(bCallCheck)
                msg=msg+"& You can enjoy all call services ";

            if(bOfferCheck)
                msg=msg+" for FREE and unlimited !! till 3 months";
        }

        msg=msg+" on selected device";
        tvFinalMessage.setText(msg);

    }

    private void changeDeviceListAccordingToSelectedBrand(String brandSelected)
    {
        //now we have got the brand
        //we will fetch all the corresponding devices for that brand and put them in a spinner
        deviceNamesList.clear();
        deviceNamesList=getDeviceListFromDatabase(brandSelected);
        adapterForDeviceList = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_layout, deviceNamesList);
        sDevice.setAdapter(adapterForDeviceList);
    }

    public List<String> getDeviceListFromDatabase(String brandName)
    {
        List<String> selectedBrand_DeviceList=new ArrayList<String>();
        SQLiteDatabase db=helper.getReadableDatabase();

        String[] col=new String[1];
        col[0]=DeviceSQLiteOpenHelper.DEVICENAME;

        Cursor c=db.query(DeviceSQLiteOpenHelper.TABLE_NAME, col, DeviceSQLiteOpenHelper.BRAND + "='"+brandName+"'", null, null, null, null);

        if((c!=null)&&c.getCount()>0)
        {
            // Toast.makeText(this, "Cursor Not Null", Toast.LENGTH_SHORT).show();
            //fetch the data from database
            while(c.moveToNext())
            {
                String s1=c.getString(c.getColumnIndex(DeviceSQLiteOpenHelper.DEVICENAME));
                selectedBrand_DeviceList.add(s1);
            }

        }

        return selectedBrand_DeviceList;
    }

    private Device getDeviceDetailFromDatbase(String brand, String device)
    {
        Device d=null;
        SQLiteDatabase db=helper.getReadableDatabase();

        String[] col=new String[5];
        col[0]=DeviceSQLiteOpenHelper.BRAND;
        col[1]=DeviceSQLiteOpenHelper.DEVICENAME;
        col[2]=DeviceSQLiteOpenHelper.DATAOFFER;
        col[3]=DeviceSQLiteOpenHelper.CALLOFFER;
        col[4]=DeviceSQLiteOpenHelper.JIOOFFFER;

        Cursor c=db.query(DeviceSQLiteOpenHelper.TABLE_NAME, col, DeviceSQLiteOpenHelper.BRAND + "='"+brand+"' AND " + DeviceSQLiteOpenHelper.DEVICENAME+ "='"+device+"'", null, null, null, null);

        if((c!=null)&&c.getCount()>0)
        {
            //fetch the device details from database according to brand and device name
            while(c.moveToNext())
            {
                String s1=c.getString(c.getColumnIndex(DeviceSQLiteOpenHelper.BRAND));
                String s2=c.getString(c.getColumnIndex(DeviceSQLiteOpenHelper.DEVICENAME));
                String s3=c.getString(c.getColumnIndex(DeviceSQLiteOpenHelper.DATAOFFER));
                String s4=c.getString(c.getColumnIndex(DeviceSQLiteOpenHelper.CALLOFFER));
                String s5=c.getString(c.getColumnIndex(DeviceSQLiteOpenHelper.JIOOFFFER));
                d=new Device(s1, s2, s3, s4, s5);
            }

        }
        return d;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.fragment_compatibility_check_iv_data:

                new AlertDialog.Builder(getActivity())
                        .setTitle("Data(Internet) Services")
                        .setMessage("Tells whether your device is compatible for jio sim's internet(data) services !")
                        .setIcon(R.mipmap.screen1_data_icon)
                        .show();
                break;
            case R.id.fragment_compatibility_check_iv_call:
                new AlertDialog.Builder(getActivity())
                        .setTitle("Call Services")
                        .setMessage("Tells whether your device is compatible for jio sim's call services !")
                        .setIcon(R.mipmap.screen1_call_icon)
                        .show();
                break;
            case R.id.fragment_compatibility_check_iv_offer:
                new AlertDialog.Builder(getActivity())
                        .setTitle("Jio Welcome Offer")
                        .setMessage("Tells whether your device will get unlimited call and data till 31st Dec for FREE !")
                        .setIcon(R.mipmap.screen1_gift_icon)
                        .show();
                break;
            case R.id.fragment_compatibility_check_tv_makeCompatibleMessage:

                Intent i=new Intent(getActivity(), MakeCompatibleActivity.class);
                startActivity(i);

                break;

        }

    }



}
