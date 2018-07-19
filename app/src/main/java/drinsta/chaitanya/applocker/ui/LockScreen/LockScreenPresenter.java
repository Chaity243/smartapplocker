package drinsta.chaitanya.applocker.ui.LockScreen;

import drinsta.chaitanya.applocker.data.DataManager;
import drinsta.chaitanya.applocker.ui.base.BasePresenter;
import drinsta.chaitanya.applocker.utility.CommonUtils;

public class LockScreenPresenter<V extends LockScreenMvpView> extends BasePresenter<V>
        implements LockScreenMvpPresenter<V> {

    private static final String TAG = "InstalledAppList";


    public LockScreenPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onServerLoginClick(String email, String password) {
        //validate email and password
        if (email == null || email.isEmpty()) {

            return;
        }
        if (!CommonUtils.isEmailValid(email)) {

            return;
        }
        if (password == null || password.isEmpty()||password.length()!=4) {

            return;
        }


        /* Save Code to Preferences*/


    }



}
