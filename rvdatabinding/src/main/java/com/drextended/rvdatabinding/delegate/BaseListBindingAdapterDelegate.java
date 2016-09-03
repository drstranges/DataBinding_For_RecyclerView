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

package com.drextended.rvdatabinding.delegate;

import android.databinding.ViewDataBinding;

import java.util.List;

import static android.support.v7.widget.RecyclerView.NO_ID;

/**
 * Base AdapterDelegate for items with ids
 * Based on AdapterDelegates Library by Hannes Dorfmann https://github.com/sockeqwe/AdapterDelegates
 *
 * @param <T> The type of the data source
 * @param <VB> The type of View Data Binding
 */
public abstract class BaseListBindingAdapterDelegate<T, VB extends ViewDataBinding> extends BaseBindingAdapterDelegate<List<T>, VB> implements IdHolder<List<T>> {

    @Override
    public long getItemId(final List<T> items, final int position) {
        return NO_ID;
    }
}
