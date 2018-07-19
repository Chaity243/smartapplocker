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

package drinsta.chaitanya.applocker.ui.login;


import drinsta.chaitanya.applocker.R;
import drinsta.chaitanya.applocker.data.DataManager;
import drinsta.chaitanya.applocker.ui.base.BasePresenter;
import drinsta.chaitanya.applocker.utility.CommonUtils;

/**
* Created by Chaitanya Aggarwal on 15/07/2018.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    private static final String TAG = "InstalledAppList";


    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void onServerLoginClick(String email, String password) {
        //validate email and password
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
        getMvpView().showLoading();

      /* Save Code to Preferences*/


    }


    public void onGoogleLoginClick() {
//        // instruct LoginActivity to initiate google login
//        getMvpView().showLoading();
//
//        getCompositeDisposable().add(getDataManager()
//                .doGoogleLoginApiCall(new LoginRequest.GoogleLoginRequest("test1", "test1"))
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(new Consumer<LoginResponse>() {
//                    @Override
//                    public void accept(LoginResponse response) throws Exception {
//                        getDataManager().updateUserInfo(
//                                response.getAccessToken(),
//                                response.getUserId(),
//                                DataManager.LoggedInMode.LOGGED_IN_MODE_GOOGLE,
//                                response.getUserName(),
//                                response.getUserEmail(),
//                                response.getGoogleProfilePicUrl());
//
//                        if (!isViewAttached()) {
//                            return;
//                        }
//
//                        getMvpView().hideLoading();
//                        getMvpView().openMainActivity();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                        if (!isViewAttached()) {
//                            return;
//                        }
//
//                        getMvpView().hideLoading();
//
//                        // handle the login error here
//                        if (throwable instanceof ANError) {
//                            ANError anError = (ANError) throwable;
//                            handleApiError(anError);
//                        }
//                    }
//                }));
    }


    public void onFacebookLoginClick() {
//        // instruct LoginActivity to initiate facebook login
//        getMvpView().showLoading();
//
//        getCompositeDisposable().add(getDataManager()
//                .doFacebookLoginApiCall(new LoginRequest.FacebookLoginRequest("test3", "test4"))
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(new Consumer<LoginResponse>() {
//                    @Override
//                    public void accept(LoginResponse response) throws Exception {
//                        getDataManager().updateUserInfo(
//                                response.getAccessToken(),
//                                response.getUserId(),
//                                DataManager.LoggedInMode.LOGGED_IN_MODE_FB,
//                                response.getUserName(),
//                                response.getUserEmail(),
//                                response.getGoogleProfilePicUrl());
//
//                        if (!isViewAttached()) {
//                            return;
//                        }
//
//                        getMvpView().hideLoading();
//                        getMvpView().openMainActivity();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                        if (!isViewAttached()) {
//                            return;
//                        }
//
//                        getMvpView().hideLoading();
//
//                        // handle the login error here
//                        if (throwable instanceof ANError) {
//                            ANError anError = (ANError) throwable;
//                            handleApiError(anError);
//                        }
//                    }
//                }));
    }
}
