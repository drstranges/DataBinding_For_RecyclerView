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
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.drextended.rvdatabinding.adapter.BindingHolder;
import com.hannesdorfmann.adapterdelegates2.AdapterDelegate;

/**
 * Base AdapterDelegate to use with data binding
 * Based on AdapterDelegates Library by Hannes Dorfmann https://github.com/sockeqwe/AdapterDelegates
 *
 * @param <T>  The type of the data source
 * @param <VB> The type of View Data Binding
 */
public abstract class BaseBindingAdapterDelegate<T, VB extends ViewDataBinding> implements AdapterDelegate<T> {


    @NonNull
    @Override
    public abstract BindingHolder<VB> onCreateViewHolder(ViewGroup parent);

    @Override
    public void onBindViewHolder(@NonNull T items, int position, @NonNull RecyclerView.ViewHolder holder) {
        //noinspection unchecked
        final BindingHolder<VB> bindingHolder = (BindingHolder<VB>) holder;
        onBindViewHolder(items, position, bindingHolder);
        bindingHolder.getBinding().executePendingBindings();
    }

    public abstract void onBindViewHolder(@NonNull T items, int position, @NonNull BindingHolder<VB> holder);
}
