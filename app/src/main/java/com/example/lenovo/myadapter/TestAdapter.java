package com.example.lenovo.myadapter;

import android.content.Context;

import com.example.lenovo.myadapter.BaseAdapter.MyRecyclerViewAdapter;
import com.example.lenovo.myadapter.BaseAdapter.MyRecylerViewHolder;

import java.util.List;

/**
 * Created by lenovo on 2018/3/9.
 */

public class TestAdapter extends MyRecyclerViewAdapter<PersonBean> {
    public TestAdapter(Context context, List<PersonBean> mDatas, int layoutId) {
        super(context, mDatas, layoutId);
    }

    @Override
    public void convert(MyRecylerViewHolder holder, PersonBean item) {
        holder.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_telephone, item.getTelephone());
    }
}
