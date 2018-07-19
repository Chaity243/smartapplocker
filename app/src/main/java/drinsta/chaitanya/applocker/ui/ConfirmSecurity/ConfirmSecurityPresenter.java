package drinsta.chaitanya.applocker.ui.ConfirmSecurity;


import drinsta.chaitanya.applocker.R;
import drinsta.chaitanya.applocker.data.DataManager;
import drinsta.chaitanya.applocker.ui.base.BasePresenter;
import drinsta.chaitanya.applocker.ui.login.LoginMvpPresenter;
import drinsta.chaitanya.applocker.ui.login.LoginMvpView;
import drinsta.chaitanya.applocker.utility.CommonUtils;

public class ConfirmSecurityPresenter <V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    private static final String TAG = "ConfirmSecurityPresenter";


    public ConfirmSecurityPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onServerLoginClick(String email, String password) {
        //validate email and password
        getMvpView().showLoading();
        if (email == null || email.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.invalid_email);
            return;
        }
        if (password == null || password.isEmpty()||password.length()!=4) {
            getMvpView().onError(R.string.empty_password);
            return;
        }


        getMvpView().hideLoading();
        getMvpView().openMainActivity();
    }


    }
