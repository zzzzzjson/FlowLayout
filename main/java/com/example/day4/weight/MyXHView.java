package com.example.day4.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.day4.R;

public class MyXHView extends LinearLayout {
    private EditText mSearch;
    private TextView mCancel;

    public MyXHView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.header_view, this);
        mSearch = findViewById(R.id.search_edit);
        mCancel = findViewById(R.id.cancel_text);
        mSearch.setBackgroundResource(R.drawable.edit_style);
    }

    public String getEditStr() {
        return mSearch.getText().toString();
    }

    public TextView getCancel() {
        return mCancel;
    }

    public EditText getEditSearch() {
        return mSearch;
    }
}
