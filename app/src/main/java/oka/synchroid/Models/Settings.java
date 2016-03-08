package oka.synchroid.Models;

import android.os.Environment;

import java.io.File;

import oka.synchroid.Models.Enums.RecordOutputFormat;

/**
 * Created by okahoul on 07/03/2016.
 */
public class Settings {


    public static File RootAppFolder = new File(Environment.getExternalStorageDirectory() +
            File.separator + "Synchroid");
    public static File RootRecordFolder = new File(Environment.getExternalStorageDirectory() +
            File.separator + "Synchroid");
    public int Id;
    public RecordOutputFormat RecordOutputFormat;

}


