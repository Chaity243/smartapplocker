package drinsta.chaitanya.applocker.ui.LockScreen;

import drinsta.chaitanya.applocker.ui.base.MvpPresenter;

public interface LockScreenMvpPresenter<V extends LockScreenMvpView> extends MvpPresenter<V> {
    void onServerLoginClick(String email, String password);
}
