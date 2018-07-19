package drinsta.chaitanya.applocker.ui.ConfirmSecurity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import drinsta.chaitanya.applocker.R;
import drinsta.chaitanya.applocker.ui.base.BaseActivity;
import drinsta.chaitanya.applocker.ui.main.MainActivity;

public class ConfirmSecurityActivity extends BaseActivity implements ConfirmSecurityMvpView {


    ConfirmSecurityMvpPresenter<ConfirmSecurityMvpView> mPresenter;

    EditText mEmailEditText;


    EditText mPasswordEditText;


    Button btn_server_login;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ConfirmSecurityActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_server_login=findViewById (R.id.btn_server_login);
        mPasswordEditText=findViewById (R.id.et_password);
        mEmailEditText=findViewById (R.id.et_email);



        mPresenter.onAttach(ConfirmSecurityActivity.this);
        btn_server_login.setText("Confirm");
    }



    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(ConfirmSecurityActivity.this);
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
}
