package com.lifeistech.android.techmemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MemoDetailActivity extends ActionBarActivity {

    MemoDB mMemoDB;
    EditText mTitle;
    EditText mMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_detail);

        mTitle = (EditText)findViewById(R.id.detail_title);
        mMemo = (EditText)findViewById(R.id.detail_memo);
        setMemo();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memo_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (R.id.detail_save == id) {
            updateMemo();
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void setMemo() {
        Intent i = getIntent();
        List<MemoDB> memoList = new Select().from(MemoDB.class).where("date = ?", i.getStringExtra("date")).execute();
        mMemoDB = memoList.get(0);
        mTitle.setText(mMemoDB.title);
        mMemo.setText(mMemoDB.memo);
    }

    void updateMemo() {
        mMemoDB.title = mTitle.getText().toString();
        mMemoDB.memo = mMemo.getText().toString();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        mMemoDB.date = sdf.format(date);
        mMemoDB.save();
    }
}
