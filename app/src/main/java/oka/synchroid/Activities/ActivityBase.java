package oka.synchroid.Activities;

import android.app.Activity;

import oka.synchroid.DataBase.DataBaseHelper;
import oka.synchroid.Models.Settings;

/**
 * Created by okahoul on 08/03/2016.
 */
public class ActivityBase extends Activity {
    protected Settings settings=null;
    protected DataBaseHelper dataBaseHelper = null;

    public ActivityBase() {
        super();
        settings = new Settings();
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
    }
}
