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
import android.view.ViewGroup;

import com.drextended.actionhandler.listener.ActionClickListener;
import com.drextended.rvdatabinding.BR;
import com.drextended.rvdatabinding.adapter.BindingHolder;

/**
 * Generic ActionDelegate. You can use this ActionDelegate if you do not want to implement custom one.
 * With ability to set actionHandler.
 * Based on AdapterDelegates Library by Hannes Dorfmann https://github.com/sockeqwe/AdapterDelegates
 * and on Action-Handler Library by Roman Donchenko https://github.com/drstranges/ActionHandler
 *
 * @param <T> The type of the data source
 */
public class ModelActionItemDelegate<T> extends ModelItemDelegate<T> {

    protected ActionClickListener mActionHandler;
    protected int mActionHandlerId = BR.actionHandler;

    public ModelActionItemDelegate(ActionClickListener actionHandler, @NonNull Class<? extends T> modelClass, @LayoutRes int itemLayoutResId) {
        super(modelClass, itemLayoutResId);
        mActionHandler = actionHandler;
    }

    public ModelActionItemDelegate(ActionClickListener actionHandler, @NonNull Class<? extends T> modelClass, @LayoutRes int itemLayoutResId, int modelId) {
        super(modelClass, itemLayoutResId, modelId);
        mActionHandler = actionHandler;
    }

    public ModelActionItemDelegate(ActionClickListener actionHandler, @NonNull Class<? extends T> modelClass, @LayoutRes int itemLayoutResId, int modelId, int actionHandlerId) {
        super(modelClass, itemLayoutResId, modelId);
        mActionHandler = actionHandler;
        if (actionHandlerId != 0) mActionHandlerId = actionHandlerId;
    }

    public ModelActionItemDelegate(ActionClickListener actionHandler, @LayoutRes int itemLayoutResId, int modelId, ViewTypeClause viewTypeClause) {
        super(itemLayoutResId, modelId, viewTypeClause);
        mActionHandler = actionHandler;
    }

    public ModelActionItemDelegate(ActionClickListener actionHandler, @LayoutRes int itemLayoutResId, int modelId, int actionHandlerId, ViewTypeClause viewTypeClause) {
        super(itemLayoutResId, modelId, viewTypeClause);
        mActionHandler = actionHandler;
        if (actionHandlerId != 0) mActionHandlerId = actionHandlerId;
    }

    @NonNull
    @Override
    public BindingHolder<ViewDataBinding> onCreateViewHolder(ViewGroup parent) {
        BindingHolder<ViewDataBinding> holder = super.onCreateViewHolder(parent);
        final ActionClickListener actionHandler = getActionHandler();
        if (actionHandler != null) {
            holder.getBinding().setVariable(mActionHandlerId, actionHandler);
        }
        return holder;
    }

    public ActionClickListener getActionHandler() {
        return mActionHandler;
    }
}
