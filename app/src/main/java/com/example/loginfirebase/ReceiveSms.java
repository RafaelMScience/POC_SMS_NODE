package com.example.loginfirebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

public class ReceiveSms extends BroadcastReceiver {

    TextView textsms;

    public static final String ACTION_FORCE_OFFLINE = "com.dev2qa.example.broadcast.receiver.ACTION_FORCE_OFFLINE";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String msg_from;


            if (bundle != null) {
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    assert pdus != null;
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();

                        Toast.makeText(context, ""+msgBody, Toast.LENGTH_SHORT).show();
                        if(msgBody.equals("Offline")){
                            Toast.makeText(context, ""+msgBody, Toast.LENGTH_SHORT).show();
                            // Finish all activity.
                            ActivityManagerUtil.finishAllActivity();

                            // Start the login form activity to let user login again.
                            Intent loginFormIntent = new Intent(context, LoginActivity.class);
                            context.startActivity(loginFormIntent);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
