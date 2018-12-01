package com.example.day4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.day4.sqlitedemo.MyDao;
import com.example.day4.weight.MyFloatLayout;
import com.example.day4.weight.MyXHView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] data = {"流感", "咳嗽", "过敏", "发烧", "感冒", "湿疹", "便秘", "痔疮", "协和", "鼻炎", "失眠", "痛风", "上火", "脚气", "抑郁症", "性欲", "乳腺增生", "头晕", "腰痛"};
    private MyFloatLayout MyFloat_Layout;
    private ArrayList<String> mList = new ArrayList<>();
    private ArrayList<String> mHistory = new ArrayList<>();
    private MyDao myDao;
    private MyXHView header_View;
    private ImageView delete_btn;
    private MyFloatLayout MyFloat_Layout_History;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDao = new MyDao(this);
        mHistory = myDao.selectName();
        initData();
        initView();
        if (!mHistory.isEmpty()) {
            MyFloat_Layout_History.setData(mHistory);
        }
    }

    private void initData() {
        for (int i = 0; i < data.length; i++) {
            mList.add(data[i]);
        }
    }

    private void initView() {
        header_View = (MyXHView) findViewById(R.id.header_View);
        header_View.getCancel().setOnClickListener(this);
        delete_btn = (ImageView) findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(this);
        MyFloat_Layout_History = (MyFloatLayout) findViewById(R.id.MyFloat_Layout_History);
        MyFloat_Layout = (MyFloatLayout) findViewById(R.id.MyFloat_Layout);
        MyFloat_Layout.setData(mList);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_text:
                String name = header_View.getEditStr().trim();
                //判空处理
                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    myDao.insertSqlite(header_View.getEditStr().trim());
                    //自己封装了一个方法删除子控件
                    MyFloat_Layout_History.removeChildView();
                    mHistory.add(name);
                    MyFloat_Layout_History.setData(mHistory);
                    header_View.getEditSearch().setText("");
                }
                break;
            case R.id.delete_btn:
                myDao.delete();
                MyFloat_Layout_History.removeChildView();
                //记得清空历史记录
                mHistory.clear();
                break;
        }
    }


}
