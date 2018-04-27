package com.lfp.lfp_base_recycleview_library.itemdecora;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by linfp on 2016/5/11.
 * 通用分割线
 */
public class ItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    private int mSpanCount;
    private int mRadixX;
    private int mCountInFirstLine = 1;

    public ItemDecoration(int spanCount, int space) {
        this.mSpace = space;
        this.mSpanCount = spanCount;
        this.mRadixX = space / spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        final int position = parent.getChildAdapterPosition(view);
        final int column = position % mSpanCount; // item column
        final int spanSize;
        final int index;

        if (params instanceof GridLayoutManager.LayoutParams) {
            GridLayoutManager.LayoutParams gridParams = (GridLayoutManager.LayoutParams) params;
            spanSize = gridParams.getSpanSize();
            index = gridParams.getSpanIndex();
            //calculate real num in line
            if (index == 0 && mSpanCount > 1 && position == 0) {
                int tempPosition = position;
                int countInLine = 0;
                int spanIndex;
                do {
                    countInLine++;
                    if (tempPosition < state.getItemCount() - 1) {
                        spanIndex = ((GridLayoutManager) parent.getLayoutManager()).getSpanSizeLookup().getSpanIndex(++tempPosition, mSpanCount);
                    } else {
                        spanIndex = 0;
                    }
                } while (spanIndex != 0);
                mCountInFirstLine = countInLine;
            }
        } else {
            spanSize = 1;
            index = 0;
        }

        // invalid value
        if (spanSize < 1 || index < 0 || spanSize > mSpanCount)
            return;

        outRect.left = mSpace - mRadixX * index;
        outRect.right = mRadixX + mRadixX * (index + spanSize - 1);

        // set top to all in first lane
        if (column == 0) {
            outRect.top = mSpace - 30;
        }
        outRect.bottom = mSpace - 30;
    }
}