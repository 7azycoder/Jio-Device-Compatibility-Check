package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Others;

/**
 * Created by Codev on 9/18/2016.
 */
public class Device {

    String brandName;
    String deviceName;
    String dataCheck;
    String callCheck;
    String offerCheck;

    public Device(String brandName, String deviceName, String dataCheck, String callCheck, String offerCheck)
    {
        this.brandName=brandName;
        this.deviceName=deviceName;
        this.dataCheck=dataCheck;
        this.callCheck=callCheck;
        this.offerCheck=offerCheck;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDataCheck() {
        return dataCheck;
    }

    public String getCallCheck() {
        return callCheck;
    }

    public String getOfferCheck() {
        return offerCheck;
    }
}
