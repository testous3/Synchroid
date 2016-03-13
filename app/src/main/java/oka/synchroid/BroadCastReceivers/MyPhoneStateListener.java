package oka.synchroid.BroadCastReceivers;

import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import oka.synchroid.DataBase.DataBaseHelper;
import oka.synchroid.Models.Settings;


public class MyPhoneStateListener extends PhoneStateListener {

    private Context _context;
    private DataBaseHelper dataBaseHelper;
    private boolean isStartRecord = false;

    public MyPhoneStateListener(Context context) {
        super();
        this._context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    private MediaRecorder recorder = null;
    private static String mFileName = null;

    public void onCallStateChanged(int state, String incomingNumber) {

        try {
            switch (state) {

                case TelephonyManager.CALL_STATE_OFFHOOK:

                    recordCall();

                    break;
                case TelephonyManager.CALL_STATE_IDLE: {

                    stopRecordCall();
                }
                break;
                default:
                    Toast.makeText(_context, "default", Toast.LENGTH_SHORT).show();
                    Log.i("Default", "Unknown phone state=" + state);
            }
        } catch (Exception e) {
            Log.i("Exception", "PhoneStateListener() e = " + e);
        }
    }

    private void recordCall() throws IOException {
        if (recorder != null)
            return;
        File rootFolder = Settings.RootAppFolder;
        boolean success = true;
        if (!rootFolder.exists()) {
            success = rootFolder.mkdir();
        }
        File folder = Settings.RootRecordFolder;
        success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-MM-dd");
        File todayFolder = new File(Settings.RootRecordFolder.getAbsolutePath() + File.separator + dt1.format(new Date()));
        success = true;
        if (!todayFolder.exists()) {
            success = todayFolder.mkdir();
        }
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);

        File fileName = new File(todayFolder.getAbsolutePath() + File.separator + new Date().getTime() + ".3gp");
        recorder.setOutputFile(Uri.fromFile(fileName).getPath());


        try {
            recorder.prepare();
            Toast.makeText(_context, "START CALL REGISTRING", Toast.LENGTH_SHORT).show();

            recorder.start();
        } catch (IllegalStateException e) {

            Log.d("DEBUG", e.toString());
        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    private void stopRecordCall() {
        if (recorder != null) {
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;
            Toast.makeText(_context, "END CALL REGISTRING", Toast.LENGTH_SHORT).show();

        }
    }


}
