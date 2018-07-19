package drinsta.chaitanya.applocker.ui.InstalledAppsList;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import drinsta.chaitanya.applocker.ui.base.BaseActivity;
import drinsta.chaitanya.applocker.ui.login.LoginActivity;

public class InstalledAppsListActivity extends BaseActivity {
    @Override
    protected void setUp() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void openMainActivity() {

    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, InstalledAppsListActivity.class);
        return intent;
    }
}
