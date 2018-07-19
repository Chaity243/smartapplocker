package drinsta.chaitanya.applocker.view.activity;

public interface LockScreenMVP {
    interface view{

        void blurLockViewData();
    }


    interface presenter{

        void setBlurLockViewData();
    }
}
