package drinsta.chaitanya.applocker.services;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;


import com.rvalerio.fgchecker.AppChecker;
import com.rvalerio.fgchecker.Utils;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import drinsta.chaitanya.applocker.utility.Config;
import drinsta.chaitanya.applocker.view.activity.LockScreenActivity;

import drinsta.chaitanya.applocker.R;

public class MyService  extends Service  {

    private Handler h;
    private Runnable r;
    private Thread t;
    private static final String CHANNEL_ID = "channel_01";
    private static final String PACKAGE_NAME =
            "drinsta.chaitanya.applocker";
    private String text="Service is running......";
    private Config conf;
    private static final String TAG = MyService.class.getName() ;
    private static final String EXTRA_STARTED_FROM_NOTIFICATION = PACKAGE_NAME +
            ".started_from_notification";
    int counter = 0;
    Map<String, java.sql.Timestamp> lastLaunchEvent7 = new LinkedHashMap<>();;
    int unLockPeriod = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        conf = Config.getInstance(getApplicationContext());
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "App Service", NotificationManager.IMPORTANCE_DEFAULT));

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification updateNotification() {
        counter++;

        Intent intent = new Intent(this, MyService.class);



        // Extra to help us figure out if we arrived in onStartCommand via the notification or not.
        intent.putExtra(EXTRA_STARTED_FROM_NOTIFICATION, true);

        // The PendingIntent that leads to a call to onStartCommand() in this service.
        PendingIntent servicePendingIntent = PendingIntent.getService(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // The PendingIntent to launch activity.
        PendingIntent activityPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, LockScreenActivity.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .addAction(R.drawable.ic_launch, getString(R.string.launch_activity),
                        activityPendingIntent)
//                .addAction(R.drawable.ic_cancel, getString(R.string.remove_location_updates),
//                        servicePendingIntent)
                .setContentText(counter+" "+text)
                .setContentTitle("App Lockify Security Runnning")
                .setOngoing(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Service is running......")
                .setWhen(System.currentTimeMillis());

        // Set the Channel ID for Android O.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(CHANNEL_ID); // Channel ID
        }

        return builder.build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().contains("start")) {
            h = new Handler(Looper.getMainLooper());
            r = new Runnable() {
                @Override
                public void run()
                {
                    lockLaunchLogic( unLockPeriod);
                    startForeground(101, updateNotification());
                    h.postDelayed(this, 1000);
                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                /*{
                    AppChecker appChecker = new AppChecker();
                    String packageName = appChecker.getForegroundApp(getApplicationContext());

                    text= packageName +"  launched!!";
                    if(packageName.equals("com.vipulmedcare.h3u.colt")){
                        Intent  i = new Intent(getApplicationContext(),LockScreenActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                    appChecker.when("com.vipulmedcare.h3u.colt",new AppChecker.Listener() {
                        @Override
                        public void onForeground(String process) {
                            text= process +"  launched!!";

                        }
                    });

                    startForeground(101, updateNotification());
                    h.postDelayed(this, 1000);
                    try {
                        Thread.currentThread().sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }*/


            };

            h.post(r);
        } else {
            h.removeCallbacks(r);
            stopForeground(true);
            stopSelf();
        }



        t = new Thread(r);
        t.start();
        return Service.START_STICKY;
    }




    public void lockLaunchLogic(int unLockPeriodMin) {
        String recentlyLauchedAppName="";

        if(getLaunchedAppPackageName()!=null)
        {
            recentlyLauchedAppName =getLaunchedAppPackageName();


        if(recentlyLauchedAppName!=null&&!recentlyLauchedAppName.isEmpty()){
            String launchTime =String.valueOf(System.currentTimeMillis());
            text=recentlyLauchedAppName +" Launched at: " +launchTime;
            if(!recentlyLauchedAppName.equals("drinsta.chaitanya.applocker"))
            {
                if(recentlyLauchedAppName.equals("com.vipulmedcare.h3u.colt"))
                {
                    onLaunchOFOtherApp ( recentlyLauchedAppName,unLockPeriodMin);
                }
            }

        }
        else {
            return;
        }

        }
    }



    public void onLaunchOFOtherApp (String lauchAppName,long unLockPeriodMin)
    {
        Timestamp timestampCurrent = new Timestamp(System.currentTimeMillis());

        Timestamp  AppLauchTime = lastLaunchEvent7.get("L"+lauchAppName);
        long diffInMinutes;
        if(AppLauchTime==null)
        {

            lastLaunchEvent7.put("L"+lauchAppName, new Timestamp(System.currentTimeMillis()));

            //show locker then other app
            showLockScreen();

        }
        else
        {
            diffInMinutes=  compareTwoTimeStamps(timestampCurrent, AppLauchTime);
            text="diff:"+String.valueOf(diffInMinutes)+","+text;

            if(diffInMinutes<=unLockPeriodMin)
            {
                // show other app
            }
            else
            {

                lastLaunchEvent7.put("L"+lauchAppName, new Timestamp(System.currentTimeMillis()));
                // show locker app then other

                showLockScreen();
            }
        }

    }

    public String getLaunchedAppPackageName()
    {
        ActivityManager am = (ActivityManager) getBaseContext().getSystemService(ACTIVITY_SERVICE);
        PackageManager pm = getBaseContext().getPackageManager();
        String mPackageName = null;

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            @SuppressLint("WrongConstant") UsageStatsManager mUsageStatsManager = (UsageStatsManager)getSystemService("usagestats");
            long time = System.currentTimeMillis();
            // We get usage stats for the last 10 seconds
            List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000*60*unLockPeriod, time);
            // Sort the stats by the last time used
            if(stats != null) {
                SortedMap<Long,UsageStats> mySortedMap = new TreeMap<Long,UsageStats>();
                for (UsageStats usageStats : stats) {
                    mySortedMap.put(usageStats.getLastTimeUsed(),usageStats);
                }
                if(!mySortedMap.isEmpty()) {
                    mPackageName =  mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                    return mPackageName;
                }
            }
        }else {
            ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> runningTasks = activityManager
                    .getRunningTasks(1);
            ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
            ComponentName topActivity = runningTaskInfo.topActivity;
            mPackageName =  topActivity.getPackageName();
            return mPackageName;
        }
        return mPackageName;
    }

    public void showLockScreen()
    {
        Intent  i = new Intent(getApplicationContext(),LockScreenActivity.class).setAction("fromService");
        i.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    public static long compareTwoTimeStamps(java.sql.Timestamp currentTime, java.sql.Timestamp oldTime)
    {
        long milliseconds1 = oldTime.getTime();
        long milliseconds2 = currentTime.getTime();

        long diff = milliseconds2 - milliseconds1;
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffMinutes;
    }

}