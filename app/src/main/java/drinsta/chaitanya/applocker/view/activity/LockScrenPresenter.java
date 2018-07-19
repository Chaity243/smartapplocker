package drinsta.chaitanya.applocker.view.activity;

import android.content.Context;

public class LockScrenPresenter implements LockScreenMVP.presenter {

    private  LockScreenMVP.view view = null;
    private Context mContext;

    public LockScrenPresenter(LockScreenMVP.view view, Context mContext)
    {
        this.view=view;
        this.mContext= mContext;


    }
    @Override
    public void setBlurLockViewData() {


    }
}
