package com.lifeistech.android.techmemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.memo_list);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MemoDB data = (MemoDB)parent.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this, MemoDetailActivity.class);
                i.putExtra("date", data.date);
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        setMemoList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (R.id.main_create == id) {
            Intent i = new Intent(MainActivity.this,MemoCreateActivity.class);
            startActivity(i);

        }
        return true;
    }

    void setMemoList() {
        List<MemoDB> memoList =new Select().from(MemoDB.class).execute();
        ArrayAdapter<MemoDB> adapter=new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.memo_row,
                memoList
        );
        mListView.setAdapter(adapter);
    }
}
