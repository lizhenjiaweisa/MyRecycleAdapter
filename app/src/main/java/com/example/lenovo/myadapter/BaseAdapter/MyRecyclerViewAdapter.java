package com.example.lenovo.myadapter.BaseAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/3/9.
 * 可根据自己需要拓展
 */

public abstract class MyRecyclerViewAdapter<T> extends RecyclerView.Adapter<MyRecylerViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private static int TYPE_HEAD = 0x01;
    private static int TYPE_NORMAL = 0x02;
    private static int TYPE_FOOT = 0x03;
    protected List<T> mDatas = new ArrayList<T>();
    private int mLayoutId;
    private LayoutInflater mLayoutInflater;

    /**
     * 点击事件的 处理   start
     */

    private OnItemClickLitener mOnItemClickLitener;
    private OnItemLongClickLitener mOnItemLongClickLitener;


    // 头部控件
    private View mHeaderView;

    // 底部控件
    private View mFooterView;


    private boolean isHasHeader = false;

    private boolean isHasFooter = false;


    /**
     * 添加头部视图
     *
     * @param header
     */
    public void setHeaderView(View header) {
        this.mHeaderView = header;
        mHeaderView.setOnClickListener(this);
        isHasHeader = true;
        notifyDataSetChanged();
    }

    /**
     * 添加底部视图
     *
     * @param footer
     */
    public void setFooterView(View footer) {
        this.mFooterView = footer;
        isHasFooter = true;
        notifyDataSetChanged();
    }


    public MyRecyclerViewAdapter(Context context, List<T> mDatas, int layoutId) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
        this.mDatas = mDatas;
    }

    @Override
    public MyRecylerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            mHeaderView.setOnClickListener(this);
            MyRecylerViewHolder holder = new MyRecylerViewHolder(mHeaderView);
            return holder;
        }
        if (viewType == TYPE_FOOT) {
            MyRecylerViewHolder holder = new MyRecylerViewHolder(mFooterView);
            return holder;
        }
        View view = mLayoutInflater.inflate(mLayoutId, parent, false);
        if (mOnItemClickLitener != null) {
            view.setOnClickListener(this);
        }
        if (mOnItemLongClickLitener != null) {
            view.setOnLongClickListener(this);
        }
        MyRecylerViewHolder holder = new MyRecylerViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(MyRecylerViewHolder holder, int position) {
        holder.getHolderView().setTag(position);
        if(isHasHeader&&isHasFooter){
            // 有头布局和底部时，向前便宜一个，且最后一个不能绑定数据
            if(position==0 ||position==mDatas.size()+1){
                return;
            }
            convert(holder, mDatas.get(position-1));
        }

        if(position!=0&&isHasHeader&&!isHasFooter){
            // 有顶部，但没有底部
            convert(holder, mDatas.get(position-1));
        }

        if(!isHasHeader&&isHasFooter){
            // 没有顶部，但有底部
            if(position==mDatas.size()){
                return;
            }
            convert(holder, mDatas.get(position));
        }

        if(!isHasHeader&&!isHasFooter){
            // 没有顶部，没有底部
            convert(holder, mDatas.get(position));
        }


    }

    public abstract void convert(MyRecylerViewHolder holder, T item);

    @Override
    public int getItemCount() {
        int size = mDatas.size();
        if (mDatas == null) {
            size = 0;
        }
        if (isHasHeader) {
            size++;
        }
        if (isHasFooter) {
            size++;
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHasHeader && position == 0) {
            return TYPE_HEAD;
        }
        if (isHasHeader && isHasFooter && position == mDatas.size() + 1) {
            // 有头部和底部时，最后底部的应该等于size+!
            return TYPE_FOOT;
        } else if (!isHasHeader && isHasFooter && position == mDatas.size()) {
            // 没有头部，有底部，底部索引为size
            return TYPE_FOOT;
        }
        return TYPE_NORMAL;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        if (mOnItemClickLitener != null) {
            if(isHasHeader&&position==0){
                mOnItemClickLitener.onHeadItemClick(v);
            }
            mOnItemClickLitener.onItemClick(v, position);

        }
    }

    @Override
    public boolean onLongClick(View view) {
        int position = (Integer) view.getTag();
        if (mOnItemLongClickLitener != null) {
            mOnItemLongClickLitener.onItemLongClick(view, position);
            return true;
        }
        return false;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
        void onHeadItemClick(View view);
    }

    public interface OnItemLongClickLitener {
        void onItemLongClick(View view, int position);
    }

    /**
     * 该方法需要在setAdapter之前调用
     */
    public MyRecyclerViewAdapter<T> setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
        return this;
    }

    /**
     * 该方法需要在setAdapter之前调用
     */
    public MyRecyclerViewAdapter<T> setOnLongItemClickLitener(OnItemLongClickLitener mOnItemLongClickLitener) {
        this.mOnItemLongClickLitener = mOnItemLongClickLitener;
        return this;
    }
}
