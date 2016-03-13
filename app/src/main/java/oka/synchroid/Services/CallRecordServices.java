package oka.synchroid.Services;

import android.media.MediaPlayer;

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

    public static void AudioPlayer(File file){
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
}
