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

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ItemDecoration for colored spacing (divider) between items in RecyclerView
 */
public class ColorDividerItemDecoration extends DividerItemDecoration {

    private final Paint mPaint;

    /**
     * Creates ItemDecoration for colored spacing (divider) between items in RecyclerView
     *
     * @param color         the color of spacing area
     * @param spacing       the spacing size in pixels
     * @param spacingConfig the spacing config. Can be set as bit mask like {@code SPACE_LEFT|SPACE_TOP}.
     *                      Available values:
     *                      {@link DividerItemDecoration#SPACE_LEFT}, {@link DividerItemDecoration#SPACE_TOP},
     *                      {@link DividerItemDecoration#SPACE_RIGHT}, {@link DividerItemDecoration#SPACE_BOTTOM}.
     * @param disableFirst  true for not drawing divider for first item.
     * @param disableLast   true for not drawing divider for last item.
     */
    public ColorDividerItemDecoration(int color, int spacing, int spacingConfig, boolean disableFirst, boolean disableLast) {
        super(spacing, spacingConfig, disableFirst, disableLast);
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            drawDivider(c, child, parent);
        }
    }

    private void drawDivider(Canvas c, View child, RecyclerView parent) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        int left, top, right, bottom;

        if (mDrawLeft && needDrawIfFirst(child, parent, SPACE_TOP)) {
            top = child.getTop() - params.topMargin;
            bottom = child.getBottom() + params.bottomMargin;
            right = child.getLeft() - params.leftMargin;
            left = right - mSpacing;
            drawDivider(c, left, top, right, bottom);
        }
        if (mDrawTop && needDrawIfFirst(child, parent, SPACE_TOP)) {
            bottom = child.getTop() - params.topMargin;
            top = bottom - mSpacing;
            left = child.getLeft() - params.leftMargin - (mDrawLeft ? mSpacing : 0);
            right = child.getRight() + params.rightMargin + (mDrawRight ? mSpacing : 0);
            drawDivider(c, left, top, right, bottom);
        }
        if (mDrawRight && needDrawIfLast(child, parent, SPACE_BOTTOM)) {
            top = child.getTop() - params.topMargin;
            bottom = child.getBottom() + params.bottomMargin;
            left = child.getRight() + params.rightMargin;
            right = left + mSpacing;
            drawDivider(c, left, top, right, bottom);
        }
        if (mDrawBottom && needDrawIfLast(child, parent, SPACE_BOTTOM)) {
            top = child.getBottom() + params.bottomMargin;
            bottom = top + mSpacing;
            left = child.getLeft() - params.leftMargin - (mDrawLeft ? mSpacing : 0);
            right = child.getRight() + params.rightMargin + (mDrawRight ? mSpacing : 0);
            drawDivider(c, left, top, right, bottom);
        }

    }

    private void drawDivider(Canvas c, int left, int top, int right, int bottom) {
        c.drawRect(left, top, right, bottom, mPaint);
    }
}
