package oka.synchroid.BroadCastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;





public class MyPhoneStateListener extends PhoneStateListener {
    private Context _context;
    public MyPhoneStateListener(Context context)
    {
        super();
        this._context=context;
    }
   private  MediaRecorder mRecorder=new MediaRecorder() ;
    private static String mFileName = null;
    public void onCallStateChanged(int state, String incomingNumber) {

        try {
            switch (state) {

                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Toast.makeText(_context, "ST CALL REGISTRING", Toast.LENGTH_SHORT).show();
                    recordCall();

                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    Toast.makeText(_context, "END CALL REGISTRING", Toast.LENGTH_SHORT).show();
                    stopRecordCall();
                    break;
                default:
                    Toast.makeText(_context, "default", Toast.LENGTH_SHORT).show();
                    Log.i("Default", "Unknown phone state=" + state);
            }
        } catch (Exception e) {
            Log.i("Exception", "PhoneStateListener() e = " + e);
        }
    }
    private void recordCall()
    {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "TollCulator");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }

        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);

        mRecorder.setOutputFile("/data/test.txt");
        try {
            mRecorder.prepare();
            mRecorder.start();
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        mRecorder.setOutputFile(mFileName);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.d("DEBUG", "prepare() failed");
        }

        mRecorder.start();
        Toast.makeText(_context, "START ENREGISTREMENT", Toast.LENGTH_SHORT).show();


    }

    private void stopRecordCall()
    {

        Log.d("DEBUG", "SAVEGARDE DE L'ENREGISTREMENT");

        try {
            mRecorder.stop();
            mRecorder.reset();   // You can reuse the object by going back to setAudioSource() step
            mRecorder.release();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
