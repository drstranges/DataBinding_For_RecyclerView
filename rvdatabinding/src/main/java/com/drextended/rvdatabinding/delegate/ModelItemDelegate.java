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
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.drextended.rvdatabinding.BR;
import com.drextended.rvdatabinding.R;
import com.drextended.rvdatabinding.adapter.BindingHolder;

import java.util.List;

/**
 * Generic ActionDelegate. You can use this ActionDelegate if you do not want to implement custom one
 * Based on AdapterDelegates Library by Hannes Dorfmann https://github.com/sockeqwe/AdapterDelegates
 *
 * @param <T> The type of the data source
 */
public class ModelItemDelegate<T> extends BaseListBindingAdapterDelegate<T, ViewDataBinding> {

    private final int mModelId;
    private final int mItemLayoutResId;
    private final ViewTypeClause mViewTypeClause;

    public ModelItemDelegate(@NonNull Class<? extends T> modelClass, @LayoutRes int itemLayoutResId) {
        this(itemLayoutResId, BR.model, new SimpleViewTypeClause(modelClass));
    }

    public ModelItemDelegate(@NonNull Class<? extends T> modelClass, @LayoutRes int itemLayoutResId, int modelId) {
        this(itemLayoutResId, modelId, new SimpleViewTypeClause(modelClass));
    }

    public ModelItemDelegate(@LayoutRes int itemLayoutResId, int modelId, ViewTypeClause viewTypeClause) {
        mItemLayoutResId = itemLayoutResId != 0 ? itemLayoutResId : R.layout.item_fallback;
        mViewTypeClause = viewTypeClause;
        mModelId = modelId != 0 ? modelId : BR.model;
    }

    @Override
    public boolean isForViewType(@NonNull List<T> items, int position) {
        return mViewTypeClause.isForViewType(items, position);
    }

    @NonNull
    @Override
    public BindingHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent) {
        return BindingHolder.newInstance(mItemLayoutResId,
                LayoutInflater.from(parent.getContext()), parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull List<T> items, int position, @NonNull BindingHolder<ViewDataBinding> holder) {
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(mModelId, items.get(position));
        binding.executePendingBindings();
    }

    public interface ViewTypeClause {
        boolean isForViewType(List<?> items, int position);
    }

    public static class SimpleViewTypeClause implements ViewTypeClause {

        private final Class<?> mClass;

        public SimpleViewTypeClause(@NonNull Class<?> aClass) {
            mClass = aClass;
        }


        @Override
        public boolean isForViewType(List<?> items, int position) {
            return mClass.isAssignableFrom(items.get(position).getClass());
        }
    }
}
