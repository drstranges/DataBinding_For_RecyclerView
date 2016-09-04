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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by roman.donchenko on 26.04.2016.
 */
public class TwoWayLoadingScrollListener extends RecyclerView.OnScrollListener {

    private static final int DEFAULT_VISIBLE_THRESOLD = 2;
    private final OnLoadMoreListener mMoreListener;

    private OnPositionChangeListener mPositionChangeListener;
    private int mVisibleThreshold; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, lastVisibleItem, oldFirstVisibleItem, oldLastVisibleItem, visibleItemCount, totalItemCount;

    private RecyclerView.LayoutManager mLayoutManager;

    public interface OnLoadMoreListener {
        void onLoadMoreForward();
        void onLoadMoreBackward();
        boolean isLoadingForward();
        boolean isLoadingBackward();
    }

    public interface OnPositionChangeListener {
        void onVisiblePositionChanged(int firstVisibleItem, int lastVisibleItem);
    }


    public TwoWayLoadingScrollListener(@NonNull OnLoadMoreListener listener) {
        this(listener, null, DEFAULT_VISIBLE_THRESOLD);
    }

    public TwoWayLoadingScrollListener(@NonNull OnLoadMoreListener listener, int visibleThreshold) {
        this(listener, null, visibleThreshold);
    }

    public TwoWayLoadingScrollListener(@NonNull OnLoadMoreListener listener, OnPositionChangeListener positionChangeListener, int visibleThreshold) {
        mMoreListener = listener;
        mPositionChangeListener = positionChangeListener;
        mVisibleThreshold = visibleThreshold;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

//        if (dy < 0) return;

        getLayoutManager(recyclerView);
        if (mLayoutManager == null) return;

        visibleItemCount = mLayoutManager.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        firstVisibleItem = getFirstVisibleItemPosition();
        lastVisibleItem = firstVisibleItem + visibleItemCount - 1;
        if (mPositionChangeListener != null && (firstVisibleItem != oldFirstVisibleItem || lastVisibleItem != oldLastVisibleItem)) {
            mPositionChangeListener.onVisiblePositionChanged(firstVisibleItem, lastVisibleItem);
            oldFirstVisibleItem = firstVisibleItem;
            oldLastVisibleItem = lastVisibleItem;
        }

        if (!mMoreListener.isLoadingBackward()
                && firstVisibleItem <= mVisibleThreshold){
            // Start has been reached
            mMoreListener.onLoadMoreBackward();

        }
        if (!mMoreListener.isLoadingForward()
                && lastVisibleItem >= (totalItemCount - mVisibleThreshold)) {

            // End has been reached
            mMoreListener.onLoadMoreForward();
        }
    }

    private int getFirstVisibleItemPosition() {
        int pos = 0;
        if (mLayoutManager instanceof LinearLayoutManager) {
            pos = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        } else if (mLayoutManager instanceof StaggeredGridLayoutManager) {
            pos = ((StaggeredGridLayoutManager) mLayoutManager).findFirstVisibleItemPositions(null)[0];
        }
        return pos;
    }

    private void getLayoutManager(RecyclerView _recyclerView) {
        if (_recyclerView != null) {
            mLayoutManager = _recyclerView.getLayoutManager();
        }
    }
}
