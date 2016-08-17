package com.example.xiaogang.recycler;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mlist;
    private RecyclerViewAdapter madapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView  = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mlist = new ArrayList<String>();
        for(int i = 0;i < 31;i++) {
            mlist.add(String.valueOf(i));
        }
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //增加和删除的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        madapter = new RecyclerViewAdapter(this,mlist);
        recyclerView.setAdapter(madapter);
        //来个函数吧
        initEvent();
    }

    private void initEvent() {

        madapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                Toast.makeText(MainActivity.this,"onItemClick: " +position, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("添加Item");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        madapter.addNextItem("添加项",position);
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                Toast.makeText(MainActivity.this,"onItemLongClick " + position,Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("删除Item");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        madapter.removeItem(position);
                    }
                });
                builder.show();
            }
        });
    }
}
