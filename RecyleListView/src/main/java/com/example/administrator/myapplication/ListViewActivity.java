package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.ListAdapter;
import utils.ListItemDecoration;

public class ListViewActivity extends ActionBarActivity {

    private RecyclerView recyclerview;
    private ListAdapter adapter;
    private ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        getData();
        assignViews();

        adapter=new ListAdapter(this,list);
        adapter.setOnItemClickLisenter(new ListAdapter.OnItemClickLisenter() {
            @Override
            public void OnShortClick(View view, int position) {
                Toast.makeText(ListViewActivity.this,"short click",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnLongClick(View view, int position) {
                adapter.remove(position);
                Toast.makeText(ListViewActivity.this,"long click",Toast.LENGTH_SHORT).show();
            }


        });

        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new ListItemDecoration(this, LinearLayoutManager.VERTICAL));
        // 设置item动画
        recyclerview.setItemAnimator(new DefaultItemAnimator());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu,menu);
        return true;
    }

    private int first=0;
    private int last=0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_first:
                first++;
                adapter.add(0,"addFirst"+first);
                break;
            case R.id.add_last:
                last++;
                adapter.add(adapter.getItemCount(),"addLast"+last);
                break;
            case R.id.remove_first:
                adapter.remove(0);
                break;
            case R.id.remove_last:
                adapter.remove(adapter.getItemCount()-1);
                break;
            case R.id.horizontal:
                recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
                recyclerview.addItemDecoration(new ListItemDecoration(this,LinearLayoutManager.HORIZONTAL));
                break;
            case R.id.vertical:
                recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                recyclerview.addItemDecoration(new ListItemDecoration(this,LinearLayoutManager.VERTICAL));
                break;
        }



        return super.onOptionsItemSelected(item);
    }

    public void getData(){
        list=new ArrayList<>();
        for(int i=0;i<30;i++){
            list.add("第"+i+"个");
        }
    }


    private void assignViews() {
        recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
    }

}
