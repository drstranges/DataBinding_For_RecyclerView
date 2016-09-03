/*
 *  Copyright Roman Donchenko. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.drextended.rvdbsample.viewmodel;

import android.content.Context;
import android.view.View;

import com.drextended.actionhandler.listener.ActionClickListener;
import com.drextended.rvdatabinding.ListConfig;
import com.drextended.rvdatabinding.adapter.BindableAdapter;
import com.drextended.rvdbsample.model.ActionType;
import com.drextended.rvdbsample.model.BaseModel;
import com.drextended.rvdbsample.util.DummyDataProvider;
import com.drextended.rvdbsample.util.SimpleCallback;
import com.drextended.rvdbsample.viewmodel.delegate.AdvertisementDelegate;
import com.drextended.rvdbsample.viewmodel.delegate.UserDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Viewmodel for page with User and Advertisement item types in one list
 */
public class UserListViewModel implements ListViewModel, ActionClickListener {

    private ListConfig mListConfig;
    private BindableAdapter<List<BaseModel>> mAdapter;
    private SimpleCallback mCallback;

    public UserListViewModel(Context context, final SimpleCallback callback) {
        mCallback = callback;
        mAdapter = new BindableAdapter<>(
                new UserDelegate(this),
                new AdvertisementDelegate(this)
        );
        mListConfig = new ListConfig.Builder(mAdapter)
                .setDefaultDividerEnabled(true)
                .build(context);
        loadData();
    }

    @Override
    public ListConfig getListConfig() {
        return mListConfig;
    }

    @Override
    public void onDestroy() {
        mCallback = null;
    }

    private void loadData() {
        mAdapter.setItems(getDummyData());
        mAdapter.notifyDataSetChanged();
    }

    private List<BaseModel> getDummyData() {
        ArrayList<BaseModel> list = new ArrayList<>();
        list.addAll(DummyDataProvider.getUsers());
        list.add(0, DummyDataProvider.getAdvertisment(1));
        list.add(6, DummyDataProvider.getAdvertisment(2));
        list.add(12, DummyDataProvider.getAdvertisment(3));
        return list;
    }

    @Override
    public void onActionClick(final View view, final String actionType, final Object model) {
        switch (actionType) {
            case ActionType.OPEN:
                mCallback.showMessage("Short click by " + model.toString());
                break;
            case ActionType.MENU:
                mCallback.showMessage("Long click by " + model.toString());
                break;
        }
    }
}
