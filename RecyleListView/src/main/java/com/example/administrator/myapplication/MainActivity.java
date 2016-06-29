package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button listView;
    private Button gridView;
    private Button strongerGirdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }

    public View.OnClickListener myClickLisenter=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.listView:
                    startActivity(new Intent(MainActivity.this,ListViewActivity.class));
                    break;
                case R.id.gridView:
                    startActivity(new Intent(MainActivity.this,GridViewActivity.class));
                    break;
                case R.id.strongerGirdView:
                    startActivity(new Intent(MainActivity.this,StrongerGridViewActivity.class));
                    break;
            }
        }
    };


    private void assignViews() {
        listView = (Button) findViewById(R.id.listView);
        gridView = (Button) findViewById(R.id.gridView);
        strongerGirdView = (Button) findViewById(R.id.strongerGirdView);

        listView.setOnClickListener(myClickLisenter);
        gridView.setOnClickListener(myClickLisenter);
        strongerGirdView.setOnClickListener(myClickLisenter);
    }

}
