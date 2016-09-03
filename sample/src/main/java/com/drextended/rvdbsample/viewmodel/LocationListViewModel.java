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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.drextended.actionhandler.ActionHandler;
import com.drextended.actionhandler.listener.ActionClickListener;
import com.drextended.rvdatabinding.ListConfig;
import com.drextended.rvdatabinding.adapter.BindableAdapter;
import com.drextended.rvdatabinding.adapter.DividerItemDecoration;
import com.drextended.rvdbsample.model.ActionType;
import com.drextended.rvdbsample.model.Advertisement;
import com.drextended.rvdbsample.model.BaseModel;
import com.drextended.rvdbsample.util.DummyDataProvider;
import com.drextended.rvdbsample.viewmodel.action.OpenLocationAction;
import com.drextended.rvdbsample.viewmodel.action.ShowToastAction;
import com.drextended.rvdbsample.viewmodel.delegate.AdvertisementDelegate;
import com.drextended.rvdbsample.viewmodel.delegate.LocationDelegate;

import java.util.ArrayList;
import java.util.List;

import static com.drextended.rvdatabinding.adapter.DividerItemDecoration.SPACE_BOTTOM;
import static com.drextended.rvdatabinding.adapter.DividerItemDecoration.SPACE_LEFT;
import static com.drextended.rvdatabinding.adapter.DividerItemDecoration.SPACE_RIGHT;
import static com.drextended.rvdatabinding.adapter.DividerItemDecoration.SPACE_TOP;

/**
 * Viewmodel for page with Location and Advertisement item types in one list
 */
public class LocationListViewModel implements ListViewModel {

    private ListConfig mListConfig;
    private BindableAdapter<List<BaseModel>> mAdapter;

    public LocationListViewModel(Context context) {
        final ActionClickListener actionHandler = new ActionHandler.Builder()
                .addAction(ActionType.OPEN, new OpenLocationAction())
                .addAction(ActionType.MENU, new ShowToastAction())
                .build();
        mAdapter = new BindableAdapter<>(
                new LocationDelegate(actionHandler),
                new AdvertisementDelegate(actionHandler)
        );
        mListConfig = createListConfig(context, mAdapter);

        loadData();
    }

    private ListConfig createListConfig(final Context context, final RecyclerView.Adapter adapter) {
        final int divider = context.getResources().getDimensionPixelSize(com.drextended.rvdatabinding.R.dimen.rvdb_list_divider_size_default);
        return new ListConfig.Builder(adapter)
                .setLayoutManagerProvider(new ListConfig.SimpleGridLayoutManagerProvider(2, new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(final int position) {
                        return mAdapter.getItems().get(position) instanceof Advertisement ? 2 : 1;
                    }
                }))
                .addItemDecoration(new DividerItemDecoration(divider, SPACE_LEFT|SPACE_TOP|SPACE_RIGHT|SPACE_BOTTOM))
                .build(context);
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
        list.add(0, DummyDataProvider.getAdvertisment(4));
        list.add(9, DummyDataProvider.getAdvertisment(5));
        return list;
    }
}
