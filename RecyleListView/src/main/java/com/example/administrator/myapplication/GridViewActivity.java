package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import adapter.ListAdapter;
import utils.GridItemDecoration;

public class GridViewActivity extends ActionBarActivity {

    private RecyclerView gridRecyclerView;
    ArrayList<String> list;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        assignViews();

        getData();

        adapter=new ListAdapter(this,list);
        adapter.setOnItemClickLisenter(new ListAdapter.OnItemClickLisenter() {
            @Override
            public void OnShortClick(View view, int position) {

            }

            @Override
            public void OnLongClick(View view, int position) {
                adapter.remove(position);

            }
        });

        gridRecyclerView.setAdapter(adapter);
        gridRecyclerView.addItemDecoration(new GridItemDecoration(this));
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this,5));
        gridRecyclerView.setItemAnimator(new DefaultItemAnimator());


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
                gridRecyclerView.setLayoutManager(new GridLayoutManager(this,5,LinearLayoutManager.HORIZONTAL,false));
                break;
            case R.id.vertical:
                gridRecyclerView.setLayoutManager(new GridLayoutManager(this,5,LinearLayoutManager.VERTICAL,false));
                break;
        }



        return super.onOptionsItemSelected(item);
    }




    public void getData(){
        list=new ArrayList<>();
        for(int i=0;i<100;i++){
            list.add("第"+i+"个");
        }
    }



    private void assignViews() {
        gridRecyclerView = (RecyclerView) findViewById(R.id.grid_recycler_view);
    }

}
