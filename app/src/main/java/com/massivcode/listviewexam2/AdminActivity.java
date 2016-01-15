package com.massivcode.listviewexam2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private CustomAdapter mAdapter;

    private Button mCloseButton;

    private ArrayList<Account> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        init();
    }

    private void init() {
        mListView = (ListView) findViewById(R.id.listview);
        mCloseButton = (Button) findViewById(R.id.btn_close);
        mCloseButton.setOnClickListener(this);

        Intent intent = getIntent();
        HashMap<String, Account> map = (HashMap<String, Account>) intent.getSerializableExtra("data");

        mData = new ArrayList<>(map.values());

        mAdapter = new CustomAdapter(mData, getApplicationContext());
        mListView.setAdapter(mAdapter);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
