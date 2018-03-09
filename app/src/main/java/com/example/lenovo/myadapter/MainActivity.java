package com.example.lenovo.myadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.myadapter.BaseAdapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<PersonBean> data;
    TestAdapter mAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        initdata();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // 设置adapter
        mAdapter=new TestAdapter(this, data, R.layout.item_test);
        View headView= LayoutInflater.from(this).inflate(R.layout.head_test,null);
        View footView=LayoutInflater.from(this).inflate(R.layout.foot_test,null);
        mAdapter.setHeaderView(headView);
        mAdapter.setFooterView(footView);
        mAdapter.setOnItemClickLitener(new MyRecyclerViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("main", "onItemClick: "+position);
            }

            @Override
            public void onHeadItemClick(View view) {
                Log.d("main", "头部被点击");
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initdata() {
        data = new ArrayList<>();
        PersonBean p = new PersonBean();
        p.setName("yh");
        p.setTelephone("1234456777");
        for (int i = 0; i < 10; i++) {
            data.add(p);
        }
    }
}
