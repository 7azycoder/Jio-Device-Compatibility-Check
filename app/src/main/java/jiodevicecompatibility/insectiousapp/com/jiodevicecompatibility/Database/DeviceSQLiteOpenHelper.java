package jiodevicecompatibility.insectiousapp.com.jiodevicecompatibility.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Codev on 9/18/2016.
 */
public class DeviceSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String _ID="id";
    public static final String DATABASE="devicedatabase";

    public static final String TABLE_NAME="device_table";
    public static final String BRAND="brand";
    public static final String DEVICENAME="devicename";
    public static final String DATAOFFER="dataoffer";
    public static final String CALLOFFER="calloffer";
    public static final String JIOOFFFER="jiooffer";

    public DeviceSQLiteOpenHelper(Context context)
    {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME+" ( "+ _ID + " INTEGER PRIMARY KEY, "+ BRAND + " TEXT, " + DEVICENAME + " TEXT, "
                + DATAOFFER + " TEXT, " + CALLOFFER + " TEXT, "  + JIOOFFFER + " TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
