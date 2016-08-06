package com.lifeistech.android.techmemo;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MemoCreateActivity extends ActionBarActivity {

    MemoDB mMemoDB;
    EditText mTitle;
    EditText mMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_create);

        mTitle = (EditText)findViewById(R.id.create_title);
        mMemo = (EditText)findViewById(R.id.create_memo);
        mMemoDB = new MemoDB();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Saveのメニューが押された時の処理
        if (R.id.create_save == id) {
            saveMemo();
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void saveMemo() {
        mMemoDB.title = mTitle.getText().toString();
        mMemoDB.memo = mMemo.getText().toString();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        mMemoDB.date = sdf.format(date);
        mMemoDB.save();
    }
}
