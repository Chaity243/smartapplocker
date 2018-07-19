package drinsta.chaitanya.applocker.view.activity;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;


import drinsta.chaitanya.applocker.services.MyService;

public class BaseActivity extends AppCompatActivity{

    public void start() {
        if (isMyServiceRunning(MyService.class))

        {
            Toast.makeText( BaseActivity.this, "Service is already enabled!!", Toast.LENGTH_SHORT ).show();
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(this, MyService.class).setAction("start"));
        } else {
            startService(new Intent(this, MyService.class).setAction("start"));
        }

        Toast.makeText( BaseActivity.this, "Service enabled!!", Toast.LENGTH_SHORT ).show();
    }

    public void stop() {
        if (!isMyServiceRunning(MyService.class))
        {
            Toast.makeText( BaseActivity.this, "Lockify Check has already been disabled!!", Toast.LENGTH_SHORT ).show();
            return;
        }
        Intent stopIntent = new Intent(this, MyService.class);
        stopIntent.setAction("stop");
        startService(stopIntent);
        Toast.makeText( BaseActivity.this, "Lockify Check Disabled!!", Toast.LENGTH_SHORT ).show();

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }



    void requestUsageStatsPermission() {
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && !hasUsageStatsPermission(this)) {
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        }
        start();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    boolean hasUsageStatsPermission(Context context) {
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow("android:get_usage_stats",
                android.os.Process.myUid(), context.getPackageName());
        boolean granted = mode == AppOpsManager.MODE_ALLOWED;
        return granted;
    }



    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
                finish();
                return true;

            case KeyEvent.KEYCODE_HOME:
                finish();
                return true;

            case KeyEvent.KEYCODE_BACK:
                finish();
                return true;
        }

        return super.onKeyDown(keycode, e);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();

    }


}
