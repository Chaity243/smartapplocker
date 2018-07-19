package drinsta.chaitanya.applocker.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import drinsta.chaitanya.applocker.utility.Common;
import drinsta.chaitanya.applocker.utility.Config;
import drinsta.chaitanya.applocker.services.MyService;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Config conf = Config.getInstance(context);
        if(conf.isEnableOnStartUp()){
            if(!Common.isMyServiceRunning(MyService.class, context))
                startMonitor(context);
        }


    }

    private void startMonitor(final Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(new Intent(context, MyService.class).setAction("start"));
                } else {
                    context.startService(new Intent(context, MyService.class).setAction("start"));
                }
            }
        }).start();
    }
}