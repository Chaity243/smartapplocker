/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package drinsta.chaitanya.applocker.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import drinsta.chaitanya.applocker.R;
import drinsta.chaitanya.applocker.data.prefs.PreferenceKeys;
import drinsta.chaitanya.applocker.ui.base.BaseActivity;
import drinsta.chaitanya.applocker.ui.login.LoginActivity;
import drinsta.chaitanya.applocker.ui.main.MainActivity;
import drinsta.chaitanya.applocker.utility.AppUtils;
import drinsta.chaitanya.applocker.view.activity.LockScreenActivity;


/**
* Created by Chaitanya Aggarwal on 15/07/2018.
 */

public class SplashActivity extends BaseActivity implements SplashMvpView {


    SplashMvpPresenter<SplashMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if( AppUtils.getPreferenceBoolean(this, PreferenceKeys.ISlOGGEDIN,false)==true){
            openLockScreenActivity();
        }
        else {
            openLoginActivity();
        }


    }

    /**
     * Making the screen wait so that the  branding can be shown
     */
    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openLockScreenActivity() {
        Intent intent = LockScreenActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void startSyncService() {
//        SyncService.start(this);
    }

    @Override
    public void decideNextScreen() {

    }

    @Override
    protected void onDestroy() {
//        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void openMainActivity() {

    }
}
