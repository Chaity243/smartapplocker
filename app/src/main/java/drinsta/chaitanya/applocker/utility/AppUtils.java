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

package drinsta.chaitanya.applocker.utility;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import drinsta.chaitanya.applocker.R;


/**
 * Created by  Chaitanya Aggarwal  on 24/05/17.
 */

public final class AppUtils {

    private static SharedPreferences mAppPreferences;
    private static SharedPreferences.Editor mEditor;
    public static void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showSoftKeyboard(final Context cntx, final EditText et) {
        et.requestFocus();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                final InputMethodManager imm = (InputMethodManager) cntx.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 0);
    }

    public static void hideSoftKeyboard(Context cntx, View v) {
        final InputMethodManager imm = (InputMethodManager) cntx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static boolean addPreferenceBoolean(Context context, String pref_field, Boolean pref_value) {
        mAppPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mAppPreferences.edit();
        mEditor.putBoolean(pref_field, pref_value);
        mEditor.commit();
        return mAppPreferences.getBoolean(pref_field, false);
    }
    public static String addPreferenceString(Context context, String pref_field, String pref_value) {
        mAppPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mAppPreferences.edit();
        mEditor.putString(pref_field, pref_value);
        mEditor.apply();
        return mAppPreferences.getString(pref_field, "null");
    }

    public static String getPreferenceString(Context context, String pref_field, String def_value) {
        mAppPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mAppPreferences.getString(pref_field, def_value);
    }
    public static String getPreference(Context context, String pref_field, String def_value) {
        mAppPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mAppPreferences.getString(pref_field, def_value);
    }

    public static boolean getPreferenceBoolean(Context context, String pref_field, Boolean def_value) {
        mAppPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return mAppPreferences.getBoolean(pref_field, def_value);
    }




    public static void saveStringFieldSpinner(Context mContext, String fieldName, String fieldValue) {
        if (fieldValue == null) {
            AppUtils.addPreferenceString(mContext, fieldName, "");
        } else {
            AppUtils.addPreferenceString(mContext, fieldName, fieldValue);
        }
    }
    public static void showErrorAlert(final Context context,String Message) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Mandatory Fields");
        alertDialog.setMessage(Message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setBackgroundColor(Color.GRAY);

        pbutton.setTextColor(Color.CYAN);
    }




    public static void showAlert(final Context mContext,String Message, String Title) {
        final Dialog myDialog = new Dialog(mContext);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.setContentView(R.layout.dialog_myalert);
        final TextView tv_title = (TextView) myDialog.findViewById(R.id.tv_title);
        tv_title.setText(Title);
        final TextView tv_message = (TextView) myDialog.findViewById(R.id.tv_message);
        tv_message.setText(Message);


        final Button btn_security_submit = (Button) myDialog.findViewById(R.id.btn_ok);
        btn_security_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }});
        myDialog.show();
    }




    public static String checkDigit(int number)
    {
        return number<=9?"0"+number:String.valueOf(number);
    }

    public static void setFont(Context mContext, EditText editText)
    {
        Typeface type = Typeface.createFromAsset(mContext.getAssets(),"fonts/Frutiger LT 55 Roman.ttf");
        editText.setTypeface(type);
    }







    private AppUtils() {
        // This class is not publicly instantiable
    }

    public static void openPlayStoreForApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_market_link) + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_google_play_store_link) + appPackageName)));
        }
    }





}
