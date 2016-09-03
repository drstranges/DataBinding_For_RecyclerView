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
package com.drextended.rvdbsample.viewmodel.action;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.drextended.actionhandler.action.BaseAction;

/**
 * Simple action
 */
public class ShowToastAction extends BaseAction {
    @Override
    public boolean isModelAccepted(final Object model) {
        return model != null;
    }

    @Override
    public void onFireAction(final Context context, @Nullable final View view, @Nullable final String actionType, @Nullable final Object model) {
        //noinspection ConstantConditions
        Toast.makeText(context, "Action fired: actionType = " + actionType + ", model = " + model.toString(), Toast.LENGTH_SHORT).show();
    }
}
