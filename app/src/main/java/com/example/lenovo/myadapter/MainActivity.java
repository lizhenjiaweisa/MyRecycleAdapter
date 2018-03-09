package com.example.lenovo.myadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<PersonBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        initdata();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // 设置adapter
        mRecyclerView.setAdapter(new TestAdapter(this, data, R.layout.item_test));
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
