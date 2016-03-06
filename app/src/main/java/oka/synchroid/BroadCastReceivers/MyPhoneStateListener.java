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
        this._context=context;
    }
    MediaRecorder recorder ;
    public void onCallStateChanged(int state, String incomingNumber) {

        try {
            switch (state) {

                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Toast.makeText(_context, "ST CALL REGISTRING", Toast.LENGTH_SHORT).show();
                    recordCall();

                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    Toast.makeText(_context, "END CALL REGISTRING", Toast.LENGTH_SHORT).show();
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
        recorder= new MediaRecorder();
        try {
            File folder = new File(Environment.getExternalStorageDirectory() + "/Synchroid2342");
            Log.d("DEBUG","**********"+folder.getAbsoluteFile().toString());
            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdir();
            }
            if (success) {
                Log.d("DEBUG","********** Folder Created");

            } else {
                // Do something else on failure
            }
            recorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

            recorder.setOutputFile("/data/text.mpeg4");
            recorder.prepare();
            recorder.start();
        }
        catch (IllegalStateException | IOException e)
        {
            e.printStackTrace();
        }


    }

    private void stopRecordCall()
    {
        Toast.makeText(_context, "Appel enregistré",
                Toast.LENGTH_LONG).show();

        try {
            recorder.stop();
            recorder.reset();   // You can reuse the object by going back to setAudioSource() step
            recorder.release();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
