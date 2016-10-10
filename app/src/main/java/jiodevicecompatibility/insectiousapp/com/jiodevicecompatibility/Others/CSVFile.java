package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Codev on 9/18/2016.
 */

public class CSVFile {
    InputStream inputStream;

    String brandName;
    String deviceName;
    String dataCheck;
    String callCheck;
    String offerCheck;


    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List read(){
        List<Device> resultList = new ArrayList<Device>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");

                brandName=row[0];
                deviceName=row[1];
                dataCheck=row[2];
                callCheck=row[3];
                offerCheck=row[4];
                Device device=new Device(brandName, deviceName, dataCheck, callCheck, offerCheck);
                resultList.add(device);

            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        return resultList;
    }
}
