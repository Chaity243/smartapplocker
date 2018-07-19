package drinsta.chaitanya.applocker;

import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {
    private static MyApplication myApp = null;

    @Override
    public void onCreate() {

        super.onCreate();
        myApp=this;


    }
    public static Context context()
    {
        return myApp.getApplicationContext();
    }
}
