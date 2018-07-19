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

package drinsta.chaitanya.applocker.ui.main.rating;

import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import drinsta.chaitanya.applocker.R;
import drinsta.chaitanya.applocker.ui.base.BaseDialog;
import drinsta.chaitanya.applocker.utility.AppUtils;


/**
 * Created by  Chaitanya Aggarwal  on 21/03/17.
 */

public class RateUsDialog extends BaseDialog implements RatingDialogMvpView {

    private static final String TAG = "RateUsDialog";


    RatingDialogMvpPresenter<RatingDialogMvpView> mPresenter;


    RatingBar mRatingBar;


    EditText mMessage;


    View mRatingMessageView;


    View mPlayStoreRatingView;

    Button mSubmitButton;


    public static RateUsDialog newInstance() {
        RateUsDialog fragment = new RateUsDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_rate_us, container, false);



                 mRatingBar=view.findViewById(R.id.rating_bar_feedback);
 mMessage=view.findViewById(R.id.et_message);

       mRatingMessageView=view.findViewById(R.id.view_rating_message);

       mPlayStoreRatingView=view.findViewById(R.id.view_play_store_rating);

        mSubmitButton=view.findViewById(R.id.btn_submit);

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }


    @Override
    public void openPlayStoreForRating() {
        AppUtils.openPlayStoreForApp(getContext());
    }

    @Override
    public void showPlayStoreRatingView() {
        mPlayStoreRatingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRatingMessageView() {
        mRatingMessageView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setUp(View view) {

        mRatingMessageView.setVisibility(View.GONE);
        mPlayStoreRatingView.setVisibility(View.GONE);

        LayerDrawable stars = (LayerDrawable) mRatingBar.getProgressDrawable();
        stars.getDrawable(2)
                .setColorFilter(ContextCompat.getColor(getContext(), R.color.yellow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(0)
                .setColorFilter(ContextCompat.getColor(getContext(), R.color.shadow), PorterDuff.Mode.SRC_ATOP);
        stars.getDrawable(1)
                .setColorFilter(ContextCompat.getColor(getContext(), R.color.shadow), PorterDuff.Mode.SRC_ATOP);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onRatingSubmitted(mRatingBar.getRating(), mMessage.getText().toString());
            }
        });

    }



    @Override
    public void disableRatingStars() {
        mRatingBar.setIsIndicator(true);
    }

    @Override
    public void hideSubmitButton() {
        mSubmitButton.setVisibility(View.GONE);
    }

    @Override
    public void dismissDialog() {
        super.dismissDialog(TAG);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.btn_later: /** AlerDialog when click on Exit */
                onLaterClick();
                break;
            case R.id.btn_rate_on_play_store: /** AlerDialog when click on Exit */
                onPlayStoreRateClick();
                break;
        }
    }

    void onLaterClick() {
        mPresenter.onLaterClicked();
    }

    void onPlayStoreRateClick() {
        mPresenter.onPlayStoreRatingClicked();
    }

    @Override
    public void openMainActivity() {

    }
}