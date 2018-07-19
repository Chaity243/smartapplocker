package drinsta.chaitanya.applocker.view.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.nightonke.blurlockview.BlurLockView;
import com.nightonke.blurlockview.Password;

import drinsta.chaitanya.applocker.DeviceAdminSample;
import drinsta.chaitanya.applocker.R;
import drinsta.chaitanya.applocker.ui.splash.SplashActivity;

public class LockScreenActivity extends BaseActivity implements LockScreenMVP.view, View.OnClickListener,
        BlurLockView.OnPasswordInputListener,
        BlurLockView.OnLeftButtonClickListener,
        ColorChooserDialog.ColorCallback {
    private LockScrenPresenter lockScrenPresenter;
    private BlurLockView blurLockView;
    private Context mContext;
    private ImageView imageView1;
    private int radius;
    private  String intentAction;
    protected DeviceAdminSample mActivity;

    static int incorrAttempt =0;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LockScreenActivity.class);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestUsageStatsPermission();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lock_screen_layout);
        mContext = this;

        lockScrenPresenter = new LockScrenPresenter(this, this);
        Intent intent = getIntent();
        if(intent!=null)
        {
            if(intent.getAction()!=null&&!intent.getAction().isEmpty())
            {
                intentAction = intent.getAction().toString();
            }
        }
        initViews();


    }

    @Override
    protected void onStart() {
        super.onStart();
        requestUsageStatsPermission();
    }

    private void initViews() {
        // Set the view that need to be blurred
        imageView1=findViewById(R.id.imageView1);
        blurLockView = findViewById(R.id.blurlockview);
//        blurLockView.setBlurredView(imageView1);
        blurLockView.setType(Password.NUMBER,false);
        blurLockView.setSmallButtonViewsBackground(R.drawable.pass_back_btn);
        blurLockView.setLeftButton("Disable");
        blurLockView.setRightButton("Correct");
        blurLockView.setIncorrectInputTimes(3);
        blurLockView.setOverlayColor(Color.parseColor("#FF4081"));

// Set the password
        blurLockView.setCorrectPassword("1234");
        blurLockView.setOnLeftButtonClickListener(this);
        blurLockView.setOnPasswordInputListener(this);
        blurLockView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/San Francisco Regular.ttf"));
        blurLockViewData();

    }

    @Override
    public void correct(String inputPassword) {
        if(intentAction!=null&&intentAction.equals("fromService"))
        {
            finishAffinity();
        }
        else {
//            Intent i = new Intent(mContext, LockedAppsListActivity.class);
//            startActivity(i);
//            finish();
            finishAffinity();
        }

    }

    @Override
    public void incorrect(String inputPassword) {

        incorrAttempt++;
        if(incorrAttempt>=3)
        {
            Toast.makeText(getApplicationContext(), "Security Breach Wiping out all Data", Toast.LENGTH_SHORT).show();
            promptForRealDeviceWipe(true);

//            Toast.makeText(mContext,"On three failed attemp phone will reset  autoatically, but I am taking to the Admin phone Reset Screen where on enabling so phone will be reset!! ",Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(mContext, DeviceAdminSample.class);
//            startActivity(i);
        }
        else {
            Toast.makeText(getApplicationContext(), "Oops! Incorrect Password..Please Try again.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void input(String inputPassword) {
        // the password is being input
    }
    @Override
    public void onClick() {
        // The left button is being clicked
        stop();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onColorSelection(@NonNull ColorChooserDialog dialog, int selectedColor) {

    }

    @Override
    public void onColorChooserDismissed(@NonNull ColorChooserDialog dialog) {

    }

    @Override
    public void blurLockViewData() {

    }

    private void promptForRealDeviceWipe(final boolean wipeAllData) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.wipe_warning_first);
        builder.setPositiveButton(R.string.wipe_warning_first_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LockScreenActivity.this);
                        if (wipeAllData) {
                            builder.setMessage(R.string.wipe_warning_second_full);
                        } else {
                            builder.setMessage(R.string.wipe_warning_second);
                        }
                        builder.setPositiveButton(R.string.wipe_warning_second_ok,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i = new Intent(mContext, DeviceAdminSample.class);
                                        startActivity(i);
                                        finish();

                                    }
                                });
                        builder.setNegativeButton(R.string.wipe_warning_second_no, null);
                        builder.show();
                    }
                });
        builder.setNegativeButton(R.string.wipe_warning_first_no, null);
        builder.show();

    }

}
