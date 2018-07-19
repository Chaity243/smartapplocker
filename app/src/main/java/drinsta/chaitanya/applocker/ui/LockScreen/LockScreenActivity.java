package drinsta.chaitanya.applocker.ui.LockScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import drinsta.chaitanya.applocker.R;
import drinsta.chaitanya.applocker.ui.ConfirmSecurity.ConfirmSecurityActivity;
import drinsta.chaitanya.applocker.ui.base.BaseActivity;

public class LockScreenActivity extends BaseActivity implements LockScreenMvpView {


    LockScreenMvpPresenter<LockScreenMvpView> mPresenter;


    EditText mEmailEditText;


    EditText mPasswordEditText;

    Button btn_server_login;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LockScreenActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock_screen_layout);

        mEmailEditText = findViewById(R.id.et_email);
        mPasswordEditText = findViewById(R.id.et_password);
        mPasswordEditText.setOnClickListener(this);

        btn_server_login = findViewById(R.id.btn_server_login);


        mPresenter.onAttach(LockScreenActivity.this);
    }


    @Override
    public void openConfirmSecurityActivity() {
        Intent intent = ConfirmSecurityActivity.getStartIntent(LockScreenActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
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
                onServerLoginClick(v);
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

