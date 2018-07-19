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

package drinsta.chaitanya.applocker.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rvalerio.fgchecker.Utils;

import drinsta.chaitanya.applocker.R;
import drinsta.chaitanya.applocker.data.prefs.PreferenceKeys;
import drinsta.chaitanya.applocker.ui.ConfirmSecurity.ConfirmSecurityActivity;
import drinsta.chaitanya.applocker.ui.base.BaseActivity;
import drinsta.chaitanya.applocker.utility.AppUtils;


/**
 * Created by Chaitanya Aggarwal on 15/07/2018.
 */

    public class LoginActivity extends BaseActivity implements LoginMvpView {


        LoginMvpPresenter<LoginMvpView> mPresenter;


        EditText mEmailEditText;


        EditText mPasswordEditText;

        Button btn_server_login;

        public static Intent getStartIntent(Context context) {
            Intent intent = new Intent(context, LoginActivity.class);
            return intent;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            mEmailEditText = findViewById(R.id.et_email);
            mPasswordEditText = findViewById(R.id.et_password);
            mPasswordEditText.setOnClickListener(this);

            btn_server_login = findViewById(R.id.btn_server_login);
            btn_server_login.setOnClickListener(this);


        }


        @Override
        public void openConfirmSecurityActivity() {
            Intent intent = ConfirmSecurityActivity.getStartIntent(LoginActivity.this);
            startActivity(intent);
            finish();
        }

        @Override
        protected void onDestroy() {
//            mPresenter.onDetach();
            super.onDestroy();
        }

        @Override
        protected void setUp() {

        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            finishAffinity();
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {


                case R.id.btn_server_login: /** AlerDialog when click on Exit */
//                    onServerLoginClick(v);
                    AppUtils.addPreferenceString(this, PreferenceKeys.lOGINEMAIL,(mEmailEditText.getText().toString()));
                    AppUtils.addPreferenceString(this, PreferenceKeys.LOGINPASSWORD,mPasswordEditText.getText().toString());

                    ConfirmSecurityActivity.getStartIntent(this);
                    break;
                }
            }


       void onServerLoginClick (View v) {
                mPresenter.onServerLoginClick(mEmailEditText.getText().toString(),
                        mPasswordEditText.getText().toString());
        }

    @Override
    public void openMainActivity() {

    }
}

