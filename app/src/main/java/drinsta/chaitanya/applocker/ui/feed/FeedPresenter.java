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

package drinsta.chaitanya.applocker.ui.feed;


;import drinsta.chaitanya.applocker.data.DataManager;
import drinsta.chaitanya.applocker.ui.base.BasePresenter;
import drinsta.chaitanya.applocker.ui.base.MvpView;


/**
 * Created by  Chaitanya Aggarwal  on 25/05/17.
 */

public class FeedPresenter<V extends MvpView> extends BasePresenter<V> implements
        FeedMvpPresenter<V> {

    private static final String TAG = "FeedPresenter";


    public FeedPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
