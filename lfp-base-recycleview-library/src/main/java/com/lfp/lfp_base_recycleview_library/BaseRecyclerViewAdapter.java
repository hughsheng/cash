package com.lfp.lfp_base_recycleview_library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lfp on 2016/5/7.
 * 万能适配器
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.SpaViewHolder> {

    //管理整体视图布局(头部,普通,底部,刷新底部)

    protected final Context context;
    protected List<T> datas;
    protected List<Object> headViews;   //头部视图(可能有多个)
    protected List<Object> footViews;   //底部视图(可能有多个)
    protected Object loadFootView;          //主要用于刷新(底部)
    protected Object empthView;
    protected Object tipContent;        //提示内容
    protected int layoutId = 0;         //主内容布局
    protected boolean isFooter = false;  //底部刷新(true,表示可以刷新)
    private BaseEvent.OnItemClickListener<T> onItemClickListener;
    private BaseEvent.OnLongItemClickListener<T> onLongItemClickListener;
    private boolean hasData = true;  //是否有数据

    /**
     * @param datas the datas to attach the adapter
     */
    public BaseRecyclerViewAdapter(Context context, List<T> datas, int layoutId) {
        this(context, datas, layoutId, null, null, null);
    }

    public BaseRecyclerViewAdapter(Context context, List<T> datas, int layoutId, Object footView) {
        this(context, datas, layoutId, footView, null, null);
    }

    public BaseRecyclerViewAdapter(Context context, List<T> datas, int layoutId, Object footView, List<Object> headViews, List<Object> footViews) {
        this.context = context;
        this.datas = datas == null ? new ArrayList<T>() : new ArrayList<>(datas);
        this.layoutId = layoutId;
        this.headViews = headViews == null ? new ArrayList<>() : new ArrayList<>(headViews);
        this.footViews = footViews == null ? new ArrayList<>() : new ArrayList<>(footViews);

        if (footView != null) {
            this.loadFootView = footView;
        }
    }

    public void setOnItemClickListener(BaseEvent.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLongItemClickListener(BaseEvent.OnLongItemClickListener onLongItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
    }

    public void setDataList(List<T> dataList) {
        this.datas = dataList;
    }

    /**
     * @param viewGroup (依赖的父容器)
     * @param layoutId  (布局)
     * @return 生成视图
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId) {
        return inflateItemView(viewGroup, layoutId, false);
    }

    /**
     * inflate a view by viewgroup ,id ,etc
     *
     * @param viewGroup (依赖的父容器)
     * @param layoutId  (布局)
     * @param attach    (是否生成)
     * @return 生成视图
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId, boolean attach) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, attach);
    }

    /**
     * viewType只有头部布局和尾部布局,才表示是position
     *
     * @param parent   (布局容器)
     * @param viewType (视图类型)
     * @return 本身
     */
    @Override
    public SpaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SpaViewHolder spaViewHolder = null;
        switch (viewType) {
            case ViewType.EMPTY:
                if (empthView instanceof View) {
                    spaViewHolder = new SpaViewHolder(context, (View) empthView);
                } else if (empthView instanceof Integer) {
                    spaViewHolder = new SpaViewHolder(context, inflateItemView(parent, (int) empthView, false));
                }
                break;
            case ViewType.NORMAL:
                spaViewHolder = new SpaViewHolder(context, inflateItemView(parent, layoutId));
                break;
            case ViewType.REFOOTER:
                if (loadFootView instanceof View) {
                    spaViewHolder = new SpaViewHolder(context, (View) loadFootView);
                } else if (loadFootView instanceof Integer) {
                    spaViewHolder = new SpaViewHolder(context, inflateItemView(parent, (int) loadFootView, false));
                }
                break;
            default:
                if (isHeadView(viewType)) {     //判断是否是头部
                    final Object hview = headViews.get(viewType);
                    if (hview instanceof View) {
                        spaViewHolder = new SpaViewHolder(context, (View) hview);
                    } else if (hview instanceof Integer) {
                        spaViewHolder = new SpaViewHolder(context, inflateItemView(parent, (int) hview, false));
                    }
                } else if (isFooterView(viewType)) {
                    final Object fview = footViews.get(getFootRealPosition(viewType));
                    if (fview instanceof View) {
                        spaViewHolder = new SpaViewHolder(context, (View) fview);
                    } else if (fview instanceof Integer) {
                        spaViewHolder = new SpaViewHolder(context, inflateItemView(parent, (int) fview, false));
                    }
                }
                break;
        }
        return spaViewHolder;
    }

    @Override
    public void onBindViewHolder(final SpaViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case ViewType.NORMAL:
                int realPos = getItemPosition(holder);
                final T item = datas.get(realPos);
                bindDataToItemView(holder, item, realPos);
                bindItemViewClickListener(holder, item, position);
                break;
            case ViewType.REFOOTER:
                bindReFooterView(holder, position);
                break;
            case ViewType.EMPTY:
                bindEmptyView(holder, position, tipContent);
                break;
            default:
                if (isHeadView(position)) {     //判断是否是头部
                    bindHeadView(holder, position);
                } else if (isFooterView(position)) {
                    bindFooterView(holder, position);
                }
                break;
        }
    }

    /**
     * 预留四个改写布局的方法,子类复写即可
     */
    public void bindHeadView(SpaViewHolder holder, int position) {
    }

    public void bindFooterView(SpaViewHolder holder, int position) {
    }

    public void bindReFooterView(SpaViewHolder holder, int position) {
    }

    public void bindEmptyView(SpaViewHolder holder, int position, Object tipContent) {
    }

    @Override
    public int getItemCount() {
        //总共有多少个View
        return (!isAllDataSize() && empthView != null) ? 1 : getHeadSize() + getFooterSize() + getDataSize();
    }

    /**
     * 当是头部布局或者底部布局时,采用位置作为viewType类型(一般针对不同布局的使用)
     *
     * @param position (位置)
     * @return view类型
     */
    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (empthView != null && !isAllDataSize()) {
            viewType = ViewType.EMPTY;
        } else if (isHeadView(position)) {  //说明是头部的视图
            viewType = position;
        } else if (isFooterView(position) && isFooterViews()) {
            viewType = position;  //子选项内容
        } else if (isReFooterView(position) && loadFootView != null && isFooter) {  //是否包含下拉刷新的底部(大于0表示是)
            viewType = ViewType.REFOOTER;
        } else {
            viewType = ViewType.NORMAL;
        }
        return viewType;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int count;
                    if (isHeadView(position)) {
                        count = gridManager.getSpanCount();  //若是头部,则返回spanCount
                    } else if (isFooterView(position)) {
                        count = gridManager.getSpanCount();  //若是有数据且是底部,返回spanCount
                    } else if (isDataSizes()) {
                        count = 1;                           //若有数据,则返回1
                    } else {
                        count = gridManager.getSpanCount();
                    }
                    return count;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(SpaViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

    /**
     * bind data to itemview
     *
     * @param vh   viewholder
     * @param item item
     */
    protected abstract void bindDataToItemView(SpaViewHolder vh, T item, int relPosition);

    /**
     * bind click listner to itemview
     *
     * @param vh       viewholder
     * @param item     item
     * @param position (位置)
     */
    protected final void bindItemViewClickListener(SpaViewHolder vh, final T item, final int position) {
        if (onItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, item, position);
                }
            });
        }
        if (onLongItemClickListener != null) {
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onLongItemClickListener.onLongItemClick(view, item, position);
                    return true;
                }
            });
        }
    }

    protected int getItemPosition(final SpaViewHolder holder) {
        int position = holder.getLayoutPosition();
        return (headViews != null && headViews.size() > 0) ? position - headViews.size() : position;
    }

    private int getFootRealPosition(int footPosition) {
        return footPosition - getHeadSize() - getDataSize();
    }

    public boolean isHeadView(int position) {
        return position < getHeadSize();
    }

    public boolean isFooterView(int position) {
        return (getHeadSize() + getDataSize()) - 1 < position;
    }

    private boolean isReFooterView(int position) {
        return (getHeadSize() + getDataSize() + getFooterSize() - 1) <= position;
    }

    private int getDataSize() {
        return datas != null ? datas.size() : 0;
    }

    public boolean isHasData() {
        return hasData;
    }

    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }

    /**
     * 判断所有数据(包括头部和底部数据)
     *
     * @return true表示有数据, false表示没有数据
     */
    private boolean isAllDataSize() {
        return getHeadSize() + getDataSize() + getFooterSize() > 0;
    }

    /**
     * 单个头部的情况
     *
     * @param view (头部视图)
     */
    public void addSingleHeadView(View view) {
        addSingleHeadView(getHeadSize(), view);
    }

    public void addSingleHeadView(int position, View view) {
        if (headViews != null && !headViews.contains(view)) {
            this.headViews.add(position, view);
        }
    }

    public void removeSingleHeadView(View view) {
        if (headViews != null) {
            this.headViews.remove(view);
        }
    }

    public void removeSingleFootView(View view) {
        if (footViews != null) {
            this.footViews.remove(view);
        }
    }

    /**
     * 单个底部的情况
     *
     * @param view (底部布局)
     */
    public void addSingleFooterView(View view) {
        addSingleFooterView(getFooterSize(), view);
    }

    public void addSingleFooterView(int position, View view) {
        if (footViews != null && !footViews.contains(view)) {
            this.footViews.add(position, view);
        }
    }

    public void addHeadViews(List<Object> headViews) {
        if (this.headViews.containsAll(headViews)) {
            this.headViews = headViews;
        }
    }

    public void addFootViews(List<Object> footViews) {
        if (this.footViews.containsAll(footViews)) {
            this.footViews = footViews;
        }
    }

    public void addReFootView(Object footView) {
        this.loadFootView = footView;
    }

    public void setEmptyView(Object empthView) {
        this.empthView = empthView;
    }

    public Object getTipContent() {
        return tipContent;
    }

    public void setTipContent(Object tipContent) {
        this.tipContent = tipContent;
    }

    public void clearHeadViews() {
        headViews.clear();
    }

    public void clearFootView() {
        footViews.clear();
    }

    public int getHeadSize() {
        return headViews != null ? headViews.size() : 0;
    }

    private int getFooterSize() {
        return footViews != null ? footViews.size() : 0;
    }

    public boolean isHeadViews() {
        return getHeadSize() > 0;
    }

    public boolean isFooterViews() {
        return getFooterSize() > 0;
    }

    public boolean isDataSizes() {
        return getDataSize() > 0;
    }

    public void clear() {
        this.datas.clear();
    }

    public void notifyDatas() {
        notifyDataSetChanged();
    }

    public void add(T data) {
        add(getDataSize() - 1, data);
    }

    public void addToFirst(T data) {
        add(0, data);
    }

    public void add(int position, T data) {
        this.datas.add(position, data);
        notifyData(position);
    }

    public void addAllToLast(List<T> data) {
        addAllToLast(getDataSize(), data);
    }

    public void addAllToFirst(List<T> data) {
        addAllToLast(0, data);
    }

    public void addAllToLast(int position, List<T> data) {
        this.datas.addAll(data);
        notifyData(position);
    }

    public void remove(int position) {
        this.datas.remove(position);
        notifyItemRemoved(position + getHeadSize());
    }

    public void remove(T data) {
        int index = datas.indexOf(data);
        if (index != -1) {
            datas.remove(index);
            notifyItemRemoved(index + getHeadSize());  //index是真实的数据,若有头部,则加上头部数据
        }
    }

    public List<T> getDatas() {
        return datas;
    }

    public void updatePosition(int position) {
        notifyItemChanged(position);
    }

    //底部布局的显示和隐藏
    public void setLoadFooter(int visible) {
        if (loadFootView != null) {
            if (visible == View.VISIBLE) {
                isFooter = true;
                notifyItemInserted(getHeadSize() + getDataSize() + getFooterSize());
            } else if (visible == View.GONE) {
                isFooter = false;
                notifyItemRemoved(getHeadSize() + getDataSize() + getFooterSize());
            }
        }
    }

    private void notifyData(int position) {
        if (getHeadSize() > 0) {
            position += getHeadSize();
        }
        notifyItemInserted(position);
    }

    private final static class ViewType {

        public final static int NORMAL = -1;

        public final static int REFOOTER = -3;

        public final static int EMPTY = -2;
    }

    public static class SpaViewHolder extends RecyclerView.ViewHolder {
        private final SparseArray<View> viewSparseArray;
        private final Context context;

        public SpaViewHolder(Context context, View itemView) {
            super(itemView);
            viewSparseArray = new SparseArray<>();
            this.context = context;
        }

        public <T extends View> T getView(int viewId) {
            return retrieveView(viewId);
        }

        @SuppressWarnings("unchecked")
        protected <T extends View> T retrieveView(int viewId) {
            View view = viewSparseArray.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                viewSparseArray.put(viewId, view);
            }
            return (T) view;
        }

        /**
         * Will set the text of a TextView.
         *
         * @param viewId The view id.
         * @param value  The text to put in the text view.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setText(int viewId, String value) {
            TextView view = retrieveView(viewId);
            view.setText(value);
            return this;
        }

        public SpaViewHolder setText(int viewId, int resId) {
            TextView view = retrieveView(viewId);
            view.setText(resId);
            return this;
        }

        public SpaViewHolder setText(int viewId, CharSequence value) {
            TextView view = retrieveView(viewId);
            view.setText(value);
            return this;
        }

        /**
         * Will set the image of an ImageView from a resource id.
         *
         * @param viewId     The view id.
         * @param imageResId The image resource id.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setImageResource(int viewId, int imageResId) {
            ImageView view = retrieveView(viewId);
            view.setImageResource(imageResId);
            return this;
        }

        /**
         * 设置图像地址
         *
         * @param viewId (viewId)
         * @param url    (图片url)
         * @return 自身
         */
        public SpaViewHolder setImageUrl(int viewId, String url) {
            ImageView view = retrieveView(viewId);
            Glide.with(context).load(url).into(view);
            return this;
        }

        public SpaViewHolder setImageUrl(int viewId, String url, int errRes) {
            ImageView view = retrieveView(viewId);
            Glide.with(context)
                    .load(url)
                    .error(errRes)
                    .into(view);
            return this;
        }

        /**
         * 默认图
         *
         * @param viewId   (加载的viewid)
         * @param url      (图片地址)
         * @param errRes   (resId)
         * @param placeRes (resId)
         * @return current
         */
        public SpaViewHolder setImageUrl(int viewId, String url, int errRes, int placeRes) {
            ImageView view = retrieveView(viewId);
            Glide.with(context)
                    .load(url)
                    .error(errRes)
                    .placeholder(placeRes)
                    .into(view);
            return this;
        }

        /**
         * 默认图
         *
         * @param viewId (加载的viewid)
         * @param url    (图片地址)
         * @param err    (resId)
         * @param place  (resId)
         * @return current
         */
        public SpaViewHolder setImageUrl(int viewId, String url, Drawable err, Drawable place) {
            ImageView view = retrieveView(viewId);
            Glide.with(context)
                    .load(url)
                    .error(err)
                    .placeholder(place)
                    .centerCrop()
                    .dontAnimate()
                    .into(view);
            return this;
        }

        /**
         * Will set background color of a view.
         *
         * @param viewId The view id.
         * @param color  A color, not a resource id.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setBackgroundColor(int viewId, int color) {
            View view = retrieveView(viewId);
            view.setBackgroundColor(color);
            return this;
        }

        /**
         * Will set background of a view.
         *
         * @param viewId        The view id.
         * @param backgroundRes A resource to use as a background.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setBackgroundRes(int viewId, int backgroundRes) {
            View view = retrieveView(viewId);
            view.setBackgroundResource(backgroundRes);
            return this;
        }

        /**
         * Will set text color of a TextView.
         *
         * @param viewId    The view id.
         * @param textColor The text color (not a resource id).
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setTextColor(int viewId, int textColor) {
            TextView view = retrieveView(viewId);
            view.setTextColor(textColor);
            return this;
        }

        /**
         * Will set text color of a TextView.
         *
         * @param viewId       The view id.
         * @param textColorRes The text color resource id.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setTextColorRes(int viewId, int textColorRes) {
            TextView view = retrieveView(viewId);
            view.setTextColor(ContextCompat.getColor(context, textColorRes));
            return this;
        }

        /**
         * Will set the image of an ImageView from a drawable.
         *
         * @param viewId   The view id.
         * @param drawable The image drawable.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setImageDrawable(int viewId, Drawable drawable) {
            ImageView view = retrieveView(viewId);
            view.setImageDrawable(drawable);
            return this;
        }

        /**
         * Add an action to set the image of an image view. Can be called multiple times.
         */
        public SpaViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
            ImageView view = retrieveView(viewId);
            view.setImageBitmap(bitmap);
            return this;
        }

        /**
         * Add an action to set the alpha of a view. Can be called multiple times.
         * Alpha between 0-1.
         */
        public SpaViewHolder setAlpha(int viewId, float value) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                retrieveView(viewId).setAlpha(value);
            } else {
                // Pre-honeycomb hack to set Alpha value
                AlphaAnimation alpha = new AlphaAnimation(value, value);
                alpha.setDuration(0);
                alpha.setFillAfter(true);
                retrieveView(viewId).startAnimation(alpha);
            }
            return this;
        }

        /**
         * Set a view visibility to VISIBLE (true) or GONE (false).
         *
         * @param viewId  The view id.
         * @param visible True for VISIBLE, false for GONE.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setVisible(int viewId, boolean visible) {
            View view = retrieveView(viewId);
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
            return this;
        }

        /**
         * Set a view visibility to VISIBLE (true) or GONE (false).
         *
         * @param viewId  The view id.
         * @param visible True for VISIBLE, false for GONE.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setVisibleViewGrpup(int viewId, boolean visible) {
            ViewGroup viewGroup = retrieveView(viewId);
            viewGroup.setVisibility(visible ? View.VISIBLE : View.GONE);
            return this;
        }

        /**
         * Add links into a TextView.
         *
         * @param viewId The id of the TextView to linkify.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder linkify(int viewId) {
            TextView view = retrieveView(viewId);
            Linkify.addLinks(view, Linkify.ALL);
            return this;
        }

        /**
         * Apply the typeface to the given viewId, and enable subpixel rendering.
         */
        public SpaViewHolder setTypeface(int viewId, Typeface typeface) {
            TextView view = retrieveView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            return this;
        }

        /**
         * Apply the typeface to all the given viewIds, and enable subpixel rendering.
         */
        public SpaViewHolder setTypeface(Typeface typeface, int... viewIds) {
            for (int viewId : viewIds) {
                TextView view = retrieveView(viewId);
                view.setTypeface(typeface);
                view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
            }
            return this;
        }

        /**
         * Sets the progress of a ProgressBar.
         *
         * @param viewId   The view id.
         * @param progress The progress.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setProgress(int viewId, int progress) {
            ProgressBar view = retrieveView(viewId);
            view.setProgress(progress);
            return this;
        }

        /**
         * Sets the progress and max of a ProgressBar.
         *
         * @param viewId   The view id.
         * @param progress The progress.
         * @param max      The max value of a ProgressBar.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setProgress(int viewId, int progress, int max) {
            ProgressBar view = retrieveView(viewId);
            view.setMax(max);
            view.setProgress(progress);
            return this;
        }

        /**
         * Sets the range of a ProgressBar to 0...max.
         *
         * @param viewId The view id.
         * @param max    The max value of a ProgressBar.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setMax(int viewId, int max) {
            ProgressBar view = retrieveView(viewId);
            view.setMax(max);
            return this;
        }

        /**
         * Sets the rating (the number of stars filled) of a RatingBar.
         *
         * @param viewId The view id.
         * @param rating The rating.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setRating(int viewId, float rating) {
            RatingBar view = retrieveView(viewId);
            view.setRating(rating);
            return this;
        }

        /**
         * Sets the rating (the number of stars filled) and max of a RatingBar.
         *
         * @param viewId The view id.
         * @param rating The rating.
         * @param max    The range of the RatingBar to 0...max.
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setRating(int viewId, float rating, int max) {
            RatingBar view = retrieveView(viewId);
            view.setMax(max);
            view.setRating(rating);
            return this;
        }

        /**
         * Sets the on click listener of the view.
         *
         * @param viewId   The view id.
         * @param listener The on click listener;
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
            View view = retrieveView(viewId);
            view.setOnClickListener(listener);
            return this;
        }

        /**
         * Sets the on touch listener of the view.
         *
         * @param viewId   The view id.
         * @param listener The on touch listener;
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
            View view = retrieveView(viewId);
            view.setOnTouchListener(listener);
            return this;
        }

        /**
         * Sets the on long click listener of the view.
         *
         * @param viewId   The view id.
         * @param listener The on long click listener;
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
            View view = retrieveView(viewId);
            view.setOnLongClickListener(listener);
            return this;
        }

        /**
         * Sets the listview or gridview's item click listener of the view
         *
         * @param viewId   The view id.
         * @param listener The item on click listener;
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
            AdapterView view = retrieveView(viewId);
            view.setOnItemClickListener(listener);
            return this;
        }

        /**
         * Sets the listview or gridview's item long click listener of the view
         *
         * @param viewId   The view id.
         * @param listener The item long click listener;
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
            AdapterView view = retrieveView(viewId);
            view.setOnItemLongClickListener(listener);
            return this;
        }

        /**
         * Sets the listview or gridview's item selected click listener of the view
         *
         * @param viewId   The view id.
         * @param listener The item selected click listener;
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
            AdapterView view = retrieveView(viewId);
            view.setOnItemSelectedListener(listener);
            return this;
        }

        /**
         * Sets the tag of the view.
         *
         * @param viewId The view id.
         * @param tag    The tag;
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setTag(int viewId, Object tag) {
            View view = retrieveView(viewId);
            view.setTag(tag);
            return this;
        }

        /**
         * Sets the tag of the view.
         *
         * @param viewId The view id.
         * @param key    The key of tag;
         * @param tag    The tag;
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setTag(int viewId, int key, Object tag) {
            View view = retrieveView(viewId);
            view.setTag(key, tag);
            return this;
        }

        /**
         * Sets the checked status of a checkable.
         *
         * @param viewId  The view id.
         * @param checked The checked status;
         * @return The SpaViewHolder for chaining.
         */
        public SpaViewHolder setChecked(int viewId, boolean checked) {
            Checkable view = retrieveView(viewId);
            view.setChecked(checked);
            return this;
        }

    }
}
