package oka.synchroid.Services;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by okahoul on 13/03/2016.
 */
public class CallRecordServices {

    public static ArrayList<File> GetAllFileFromDirectory(File directory, ArrayList<File> listFile) {


        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {

                GetAllFileFromDirectory(file, listFile);
            } else {
                listFile.add(file);
            }
        }


        return listFile;
    }

    public static void AudioPlayer(File file) {
        //set up MediaPlayer
        MediaPlayer mp = new MediaPlayer();

        try {
            mp.setDataSource(file.getAbsolutePath());
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getContactName(Context context, String phoneNumber) {
        if(phoneNumber==null || phoneNumber.isEmpty())
            return "Inconnu";
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = phoneNumber;
        if(cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }

        if(cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return contactName;
    }
}
