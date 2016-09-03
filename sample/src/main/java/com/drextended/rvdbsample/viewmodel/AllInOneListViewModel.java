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

import com.drextended.actionhandler.ActionHandler;
import com.drextended.actionhandler.listener.ActionClickListener;
import com.drextended.rvdatabinding.ListConfig;
import com.drextended.rvdatabinding.adapter.BindableAdapter;
import com.drextended.rvdatabinding.delegate.ModelActionItemDelegate;
import com.drextended.rvdbsample.R;
import com.drextended.rvdbsample.model.ActionType;
import com.drextended.rvdbsample.model.Advertisement;
import com.drextended.rvdbsample.model.BaseModel;
import com.drextended.rvdbsample.model.Location;
import com.drextended.rvdbsample.model.User;
import com.drextended.rvdbsample.util.DummyDataProvider;
import com.drextended.rvdbsample.viewmodel.action.ShowToastAction;
import com.drextended.rvdbsample.BR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Viewmodel for page with All item types in one list
 */
public class AllInOneListViewModel implements ListViewModel {

    private ListConfig mListConfig;
    private BindableAdapter<List<BaseModel>> mAdapter;

    public AllInOneListViewModel(Context context) {

        final ActionClickListener actionHandler = new ActionHandler.Builder()
                .addAction(ActionType.OPEN, new ShowToastAction())
                .addAction(ActionType.MENU, new ShowToastAction())
                //.addAction(null, new TrackAction()) // fires for any actionType
                .build();

        //noinspection unchecked
        mAdapter = new BindableAdapter<>(
                // new UserDelegate(actionHandler), you do not need even create custom delegate
                new ModelActionItemDelegate<BaseModel>(actionHandler, User.class, R.layout.item_user, BR.user),
                new ModelActionItemDelegate<BaseModel>(actionHandler, Location.class, R.layout.item_location, BR.location),
                new ModelActionItemDelegate<BaseModel>(actionHandler, Advertisement.class, R.layout.item_advertisment, BR.advertisment)
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
    public void onDestroy() {}

    private void loadData() {
        mAdapter.setItems(getDummyData());
        mAdapter.notifyDataSetChanged();
    }

    private List<BaseModel> getDummyData() {
        ArrayList<BaseModel> list = new ArrayList<>();
        list.addAll(DummyDataProvider.getLocations());
        list.addAll(DummyDataProvider.getUsers());

        Collections.shuffle(list);

        list.add(0, DummyDataProvider.getAdvertisment(1));
        list.add(6, DummyDataProvider.getAdvertisment(2));
        list.add(12, DummyDataProvider.getAdvertisment(3));
        list.add(22, DummyDataProvider.getAdvertisment(4));
        list.add(30, DummyDataProvider.getAdvertisment(5));
        return list;
    }
}
