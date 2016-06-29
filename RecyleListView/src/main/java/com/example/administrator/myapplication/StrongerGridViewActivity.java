package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.StrongerGridAdapter;

public class StrongerGridViewActivity extends ActionBarActivity {

    private RecyclerView strongerGridView;
    private ArrayList<String> list;
    private StrongerGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stronger_grid_view);

        getData();
        assignViews();

        StaggeredGridLayoutManager sglm=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        adapter = new StrongerGridAdapter(list, this,sglm);
        adapter.setOnItemClickListener(new StrongerGridAdapter.OnItemClickListener() {
            @Override
            public void OnShortClick(View view, int pos) {

            }

            @Override
            public void OnLongClick(View view, int pos) {
                adapter.remove(pos);
            }
        });

        strongerGridView.setAdapter(adapter);
        strongerGridView.setLayoutManager(sglm);
        strongerGridView.setItemAnimator(new DefaultItemAnimator());



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_first:
                adapter.add(0, "add first");
                break;
            case R.id.add_last:
                adapter.add(adapter.getItemCount(), "add last");
                break;
            case R.id.remove_first:
                String value = adapter.remove(0);
                Toast.makeText(StrongerGridViewActivity.this, "remove:" + value, Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_last:
                String value1 =  adapter.remove(adapter.getItemCount()-1);
                Toast.makeText(StrongerGridViewActivity.this, "remove:" + value1, Toast.LENGTH_SHORT).show();
                break;
            case R.id.horizontal:
                strongerGridView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
                adapter.setOrientation(StaggeredGridLayoutManager.HORIZONTAL);
                break;
            case R.id.vertical:
                strongerGridView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
                adapter.setOrientation(StaggeredGridLayoutManager.VERTICAL);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getData() {
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
    }

    private void assignViews() {
        strongerGridView = (RecyclerView) findViewById(R.id.strongerGridView);
    }

}
