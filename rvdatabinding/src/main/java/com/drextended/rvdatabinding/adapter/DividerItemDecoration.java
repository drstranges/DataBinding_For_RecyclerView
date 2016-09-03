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

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ItemDecoration for spacing (divider) between items in RecyclerView
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    public static final int SPACE_LEFT = 1;
    public static final int SPACE_RIGHT = 2;
    public static final int SPACE_TOP = 4;
    public static final int SPACE_BOTTOM = 8;
//    public static final int SPACE_GRID = 16;

    protected final int mSpacingConfig;
    protected final boolean mDrawLeft;
    protected final boolean mDrawTop;
    protected final boolean mDrawRight;
    protected final boolean mDrawBottom;
    private final boolean mDisableFirst;
    private final boolean mDisableLast;

    protected int mSpacing;

    /**
     * Creates ItemDecoration for colored spacing (divider) between items in RecyclerView
     * Make spacing on top of each item
     *
     * @param spacing the spacing size in pixels
     */
    public DividerItemDecoration(final int spacing) {
        this(spacing, SPACE_TOP);
    }

    /**
     * Creates ItemDecoration for colored spacing (divider) between items in RecyclerView
     *
     * @param spacing       the spacing size in pixels
     * @param spacingConfig the spacing config. Can be set as bit mask like {@code SPACE_LEFT|SPACE_TOP}.
     *                      Available values:
     *                      {@link DividerItemDecoration#SPACE_LEFT}, {@link DividerItemDecoration#SPACE_TOP},
     *                      {@link DividerItemDecoration#SPACE_RIGHT}, {@link DividerItemDecoration#SPACE_BOTTOM}.
     */
    public DividerItemDecoration(final int spacing, int spacingConfig) {
        this(spacing, spacingConfig, false, false);
    }

    /**
     * Creates ItemDecoration for colored spacing (divider) between items in RecyclerView
     *
     * @param spacing       the spacing size in pixels
     * @param spacingConfig the spacing config. Can be set as bit mask like {@code SPACE_LEFT|SPACE_TOP}.
     *                      Available values:
     *                      {@link DividerItemDecoration#SPACE_LEFT}, {@link DividerItemDecoration#SPACE_TOP},
     *                      {@link DividerItemDecoration#SPACE_RIGHT}, {@link DividerItemDecoration#SPACE_BOTTOM}.
     * @param disableFirst  true for not drawing divider for first item.
     * @param disableLast   true for not drawing divider for last item.
     */
    public DividerItemDecoration(final int spacing, int spacingConfig, boolean disableFirst, boolean disableLast) {
        mSpacing = spacing;
        mSpacingConfig = spacingConfig;

        mDrawLeft = check(SPACE_LEFT);
        mDrawTop = check(SPACE_TOP);
        mDrawRight = check(SPACE_RIGHT);
        mDrawBottom = check(SPACE_BOTTOM);

        mDisableFirst = disableFirst;
        mDisableLast = disableLast;
    }

    @Override
    public void getItemOffsets(Rect outRect, View child, RecyclerView parent, RecyclerView.State state) {
        if (mDrawLeft && needDrawIfFirst(child, parent, SPACE_LEFT)) outRect.left = mSpacing;
        if (mDrawTop && needDrawIfFirst(child, parent, SPACE_TOP)) outRect.top = mSpacing;
        if (mDrawRight && needDrawIfLast(child, parent, SPACE_RIGHT)) outRect.right = mSpacing;
        if (mDrawBottom && needDrawIfLast(child, parent, SPACE_BOTTOM)) outRect.bottom = mSpacing;
    }

    protected boolean check(int value) {
        return (mSpacingConfig & value) == value;
    }

    protected boolean isFirstItem(View child, RecyclerView parent, final int spacingConfig) {
        return parent.getChildAdapterPosition(child) == 0;
    }

    protected boolean isLastItem(View child, RecyclerView parent) {
        final RecyclerView.Adapter adapter = parent.getAdapter();
        return adapter != null && parent.getChildAdapterPosition(child) == adapter.getItemCount() - 1;
    }

    protected boolean needDrawIfFirst(View child, RecyclerView parent, final int spacingConfig) {
        return !(mDisableFirst && isFirstItem(child, parent, spacingConfig));
    }

    protected boolean needDrawIfLast(View child, RecyclerView parent, final int spacingConfig) {
        return !(mDisableLast && isLastItem(child, parent));
    }

}
