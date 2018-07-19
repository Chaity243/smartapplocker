package drinsta.chaitanya.applocker.utility;

import android.app.ActivityManager;
import android.content.Context;

public class Common {
    public static final String ACTION_APPLICATION_PASSED = "com.drinsta.chaity.applockify";
    public static final String EXTRA_PACKAGE_NAME = "com.drinsta.chaity.applockify.extra.PACKAGE_NAME";

    public static boolean isMyServiceRunning(Class<?> serviceClass, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
