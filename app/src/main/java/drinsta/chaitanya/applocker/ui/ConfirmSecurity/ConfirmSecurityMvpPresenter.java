package drinsta.chaitanya.applocker.ui.ConfirmSecurity;


import drinsta.chaitanya.applocker.ui.base.MvpPresenter;

public interface ConfirmSecurityMvpPresenter <V extends ConfirmSecurityMvpView> extends MvpPresenter<V> {

    void onServerLoginClick(String email, String password);

    void onGoogleLoginClick();

    void onFacebookLoginClick();

}
