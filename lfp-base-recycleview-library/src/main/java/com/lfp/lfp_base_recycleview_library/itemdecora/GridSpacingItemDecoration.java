package com.lfp.lfp_base_recycleview_library.itemdecora;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by linfp on 2016/5/11.
 * GridLayoutManager的分界线
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private int sub;
    private boolean includeEdge;

    private Paint mPaint;

    /**
     * 设置每个Item的边距
     *
     * @author: linfp
     * Date: 2016/5/11
     * Time: 9:13
     * spanCount(每列的个数),spacing(每个item的间距),includeEdge(是否有边距),sub表示减去的距离
     * FIXME
     */
    public GridSpacingItemDecoration(int spanCount, int spacing, int sub, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        this.sub = sub;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (column == 0) { // top edge(第一行的数据)  //position < spanCount
                //outRect.top = spacing;
            }
            outRect.top = spacing;
            if (column == 0) {
                outRect.bottom = spacing - sub; // item bottom
            } else {
                outRect.bottom = spacing;
            }
        } else {
            outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing; // item top
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
    }
}
