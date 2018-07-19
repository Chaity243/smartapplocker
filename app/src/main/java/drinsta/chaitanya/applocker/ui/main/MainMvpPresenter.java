

package drinsta.chaitanya.applocker.ui.main;


import drinsta.chaitanya.applocker.ui.base.MvpPresenter;

/**
 * Created by Chaitanya Aggarwal on 15/07/2018.
 */


public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onDrawerOptionAboutClick();

    void onDrawerOptionLogoutClick();

    void onDrawerOptionLockedAppsListClick();

    void  onDrawerOptionSettingsClick();

    void onDrawerOptionAdminClick();

    void onDrawerRateUsClick();

    void onDrawerMyFeedClick();

    void onViewInitialized();

    void onCardExhausted();

    void onNavMenuCreated();
}
