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

package com.drextended.rvdatabinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * Helper class for collect all data binding adapters in one place
 */
public class Converters {

    /**
     * Binding adapter to apply RecyclerView config.
     * Sample:
     * <pre>
     * {@code
     *     &lt;android.support.v7.widget.RecyclerView
     *     android:layout_width="match_parent"
     *     android:layout_height="match_parent"
     *     app:listConfig="@{viewModel.listConfig}"
     *     /&gt;
     * }
     * </pre>
     *
     * @param recyclerView The RecyclerView to apply config
     * @param config       The config for RecyclerView
     */
    @BindingAdapter({"listConfig"})
    public static void configRecyclerView(RecyclerView recyclerView, ListConfig config) {
        if (config == null) return;
        config.applyConfig(recyclerView.getContext(), recyclerView);
    }
}
