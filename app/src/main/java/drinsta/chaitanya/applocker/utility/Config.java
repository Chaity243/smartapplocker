package drinsta.chaitanya.applocker.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class Config {

    private static Config instance;
    private SharedPreferences PREFS;
    private static final String PREFS_NAME = "com.drinsta.chaity.applockify.Preferences";
    private final String enableOnStartUpName = "enableOnStartUp";
    private final String securityMethodName = "securityMethod";
    private Config(Context context) {
        assert context != null;
        PREFS = context.getSharedPreferences(PREFS_NAME, 0);
    }

    public static synchronized Config getInstance(Context context) {
        if (instance == null) instance = new Config(context);
        return instance;
    }
    public String getAllowedPackage() {
        return PREFS.getString("allowedPackage", null);
    }

    public boolean isEnableOnStartUp() {
        return PREFS.getBoolean(enableOnStartUpName, true);
    }
    public void setEnableOnStartUp(boolean val) {
        synchronized (PREFS) {
            SharedPreferences.Editor editor = PREFS.edit();
            editor.putBoolean(enableOnStartUpName, val);
            editor.apply();
        }
    }

}
