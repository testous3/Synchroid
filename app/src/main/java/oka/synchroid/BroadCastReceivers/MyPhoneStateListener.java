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
    private boolean isStartRecord=false;
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
                    if(isStartRecord)
                    {
                    Toast.makeText(_context, "END CALL REGISTRING", Toast.LENGTH_SHORT).show();
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
    private void recordCall()
    {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "Synchroid");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }

        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setOutputFile(Environment.getExternalStorageDirectory() +
                File.separator + "Synchroid" + File.separator + "test.mpeg4");

        try {
            mRecorder.prepare();

        } catch (IllegalStateException e) {

            Log.d("ERROR ","IllegalStateException");
        } catch (Exception e) {
            Log.d("ERROR ","IOException");
            e.printStackTrace();
        }
        try {
            isStartRecord=true;
            mRecorder.start();
        } catch (Exception e) {

        }




    }

    private void stopRecordCall()
    {
        if(mRecorder!=null){


        try {
            mRecorder.stop();
            mRecorder.reset();   // You can reuse the object by going back to setAudioSource() step
            mRecorder.release();
            isStartRecord=false;
             }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    }
    }
