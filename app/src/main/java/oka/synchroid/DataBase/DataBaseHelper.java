package oka.synchroid.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import oka.synchroid.Models.Enums.RecordOutputFormat;
import oka.synchroid.Models.Settings;

/**
 * Created by okahoul on 08/03/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Synchroid.db";
    public static final String SETTINGS_TABLE_NAME = "Settings";
    public static final String SETTINGS_COLUMN_ID = "Id";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table Settings " +
                        "(Id integer primary key,RecordOutputFormat integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean InsertSettings(Settings settings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //    contentValues.put("name", name);
        db.insert("Settings", null, contentValues);
        return true;
    }

    public Settings GetSettings() {
        Settings _settings = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from Settings", null);

        while (res.moveToFirst()) {
            _settings = new Settings();
            _settings.RecordOutputFormat = RecordOutputFormat.values()[res.getShort(1)];
        }
        return _settings;

    }

    public boolean UpdateSettings(Settings settings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RecordOutputFormat", settings.RecordOutputFormat.ordinal());
        db.update("Settings", contentValues, "Id = ? ", new String[]{Integer.toString(settings.Id)});
        return true;
    }


}
