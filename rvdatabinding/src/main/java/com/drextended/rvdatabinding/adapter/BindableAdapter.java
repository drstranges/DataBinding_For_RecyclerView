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

package com.drextended.rvdatabinding.adapter;

import android.support.annotation.NonNull;

import com.hannesdorfmann.adapterdelegates2.AdapterDelegate;
import com.hannesdorfmann.adapterdelegates2.AdapterDelegatesManager;

import java.util.List;

/**
 * RecyclerView Adapter for using with data binding. Uses List of items as dataset.
 * Based on AdapterDelegates Library by Hannes Dorfmann https://github.com/sockeqwe/AdapterDelegates
 *
 * @param <T> The type of the datasoure / items
 */

public class BindableAdapter<T extends List> extends BaseBindableAdapter<T> {

    public BindableAdapter() {
    }

    public BindableAdapter(@NonNull final AdapterDelegatesManager<T> delegatesManager) {
        super(delegatesManager);
    }

    public BindableAdapter(AdapterDelegate<T>... adapterDelegates) {
        super(adapterDelegates);
    }

    public BindableAdapter(final T items, final AdapterDelegatesManager<T> delegatesManager) {
        super(items, delegatesManager);
    }

    public BindableAdapter(final T items, AdapterDelegate<T>... adapterDelegates) {
        super(items, adapterDelegates);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
