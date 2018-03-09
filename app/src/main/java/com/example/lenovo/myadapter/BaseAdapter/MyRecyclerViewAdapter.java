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

public abstract class MyRecyclerViewAdapter<T> extends RecyclerView.Adapter<MyRecylerViewHolder> implements View.OnClickListener,View.OnLongClickListener{
    protected List<T> mDatas = new ArrayList<T>();
    private int mLayoutId;
    private LayoutInflater mLayoutInflater;

    /**
     * 点击事件的 处理   start
     */

    private OnItemClickLitener mOnItemClickLitener;
    private OnItemLongClickLitener mOnItemLongClickLitener;


    public MyRecyclerViewAdapter(Context context, List<T> mDatas, int layoutId) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
        this.mDatas = mDatas;
    }
    @Override
    public MyRecylerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        convert(holder,mDatas.get(position));
    }

    public abstract void convert(MyRecylerViewHolder holder, T item);

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        if (mOnItemClickLitener != null) {
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
