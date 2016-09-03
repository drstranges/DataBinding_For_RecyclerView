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

import static android.support.v7.widget.RecyclerView.LayoutManager;
import static android.support.v7.widget.RecyclerView.OnScrollListener;

/**
 * RecyclerView ScrollListener for implementing lazy loading list (endless list)
 * <pre>
 * {@code
 *      ListConfig listConfig = new ListConfig.Builder(mAdapter)
 *          .addOnScrollListener(new OnLoadMoreScrollListener(
 *              new OnLoadMoreListener {
 *                  public void onLoadMore() {
 *                      //Load new items
 *                  }
 *
 *                  public boolean isLoading() {
 *                      return isLoading; // true if loading in progress
 *                  }
 *              }
 *          ))
 *          .build(context);
 * }
 * </pre>
 */
public class LoadMoreScrollListener extends OnScrollListener {

    private static final int DEFAULT_VISIBLE_THRESOLD = 5;
    private final OnLoadMoreListener mMoreListener;
    private int mVisibleThreshold; // The minimum amount of items to have below your current scroll position before loading more.
    private int firstVisibleItem, lastVisibleItem, visibleItemCount, totalItemCount;

    /**
     * Listener for implementing lazy loading list (endless list)
     */
    public interface OnLoadMoreListener {
        /**
         * Callback for starting loading new portion of data
         */
        void onLoadMore();

        /**
         * Return loading status
         *
         * @return true if loading in progress
         */
        boolean isLoading();
    }

    /**
     * Creates ScrollListener for implementing lazy loading list (endless list)
     * If there are less then 5 items after last visible item in recycler view
     * and {@link OnLoadMoreListener#isLoading()} returns false,
     * then {@link OnLoadMoreListener#onLoadMore()} will be called.
     *
     * @param listener the callback {@link OnLoadMoreListener}
     */
    public LoadMoreScrollListener(@NonNull OnLoadMoreListener listener) {
        this(listener, DEFAULT_VISIBLE_THRESOLD);
    }

    /**
     * Creates ScrollListener for implementing lazy loading list (endless list)
     *
     * @param listener         the callback {@link OnLoadMoreListener}
     * @param visibleThreshold the amount of items. If there are less then visibleThreshold items
     *                         after last visible item in recycler view
     *                         and {@link OnLoadMoreListener#isLoading()} returns false,
     *                         then {@link OnLoadMoreListener#onLoadMore()} will be called.
     */
    public LoadMoreScrollListener(@NonNull OnLoadMoreListener listener, int visibleThreshold) {
        mMoreListener = listener;
        mVisibleThreshold = visibleThreshold;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy < 0) return;

        final LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager == null) return;

        visibleItemCount = layoutManager.getChildCount();
        totalItemCount = layoutManager.getItemCount();
        firstVisibleItem = getFirstVisibleItemPosition(layoutManager);
        lastVisibleItem = firstVisibleItem + visibleItemCount - 1;

        if (!mMoreListener.isLoading()
                && lastVisibleItem >= (totalItemCount - mVisibleThreshold)) {
            // End has been reached
            mMoreListener.onLoadMore();
        }
    }

    private int getFirstVisibleItemPosition(LayoutManager layoutManager) {
        int pos = 0;
        if (layoutManager instanceof LinearLayoutManager) {
            pos = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            pos = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null)[0];
        }
        return pos;
    }

    private LayoutManager getLayoutManager(RecyclerView recyclerView) {
        if (recyclerView != null) {
            return recyclerView.getLayoutManager();
        }
        return null;
    }
}
