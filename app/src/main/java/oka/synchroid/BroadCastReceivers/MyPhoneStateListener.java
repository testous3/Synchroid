package oka.synchroid.BroadCastReceivers;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.util.Log;
import android.widget.Toast;

public class MyPhoneStateListener extends PhoneStateListener {
    Context _context;
    public MyPhoneStateListener(Context context)
    {
        this._context=context;
    }
    public void onCallStateChanged(int state, String incomingNumber) {

        Log.d("MyPhoneListener", state + "   incoming no:" + incomingNumber);

        if (state == 1) {

            String msg = "New Phone Call Event. Incomming Number : "+incomingNumber;
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(_context, msg, duration);
            toast.show();

        }
    }
}
