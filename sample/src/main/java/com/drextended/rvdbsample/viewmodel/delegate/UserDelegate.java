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
package com.drextended.rvdbsample.viewmodel.delegate;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.drextended.actionhandler.listener.ActionClickListener;
import com.drextended.rvdatabinding.adapter.BindingHolder;
import com.drextended.rvdatabinding.delegate.ActionAdapterDelegate;
import com.drextended.rvdbsample.R;
import com.drextended.rvdbsample.databinding.ItemUserBinding;
import com.drextended.rvdbsample.model.BaseModel;
import com.drextended.rvdbsample.model.User;

import java.util.List;

/**
 * Item Delegate to display User item
 */
public class UserDelegate extends ActionAdapterDelegate<BaseModel, ItemUserBinding> {

    public UserDelegate(final ActionClickListener actionHandler) {
        super(actionHandler);
    }

    @Override
    public boolean isForViewType(@NonNull final List<BaseModel> items, final int position) {
        return items.get(position) instanceof User;
    }

    @NonNull
    @Override
    public BindingHolder<ItemUserBinding> onCreateViewHolder(final ViewGroup parent) {
        return BindingHolder.newInstance(R.layout.item_user, LayoutInflater.from(parent.getContext()), parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull final List<BaseModel> items, final int position, @NonNull final BindingHolder<ItemUserBinding> holder) {
        final User user = (User) items.get(position);
        holder.getBinding().setUser(user);
        holder.getBinding().setActionHandler(getActionHandler());
    }

    @Override
    public long getItemId(final List<BaseModel> items, final int position) {
        return items.get(position).getId();
    }
}
