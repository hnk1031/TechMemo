package com.lifeistech.android.techmemo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by hnk_1031 on 16/08/06.
 */

@Table(name = "memo_table")
public class MemoDB extends Model {

    @Column(name = "title")
    public String title;

    @Column(name = "memo")
    public String memo;

    @Column(name = "date")
    public String date;

    @Override
    public String toString() {
        return title;
    }
}
