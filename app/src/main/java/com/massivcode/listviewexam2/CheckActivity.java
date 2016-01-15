package com.massivcode.listviewexam2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class CheckActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mIdEditText, mPasswordEditText;
    private TextView mIdTextView, mBalanceTextView;
    private Button mLoginButton, mCloseButton;
    private LinearLayout mContainer;

    private HashMap<String, Account> mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        Intent intent = getIntent();
        mMap = (HashMap<String, Account>) intent.getSerializableExtra("data");


        init();
    }

    private void init() {
        mIdEditText = (EditText) findViewById(R.id.et_id);
        mPasswordEditText = (EditText) findViewById(R.id.et_pw);
        mIdTextView = (TextView) findViewById(R.id.tv_account);
        mBalanceTextView = (TextView) findViewById(R.id.tv_balance);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mCloseButton = (Button) findViewById(R.id.btn_close);
        mContainer = (LinearLayout) findViewById(R.id.container);

        mLoginButton.setOnClickListener(this);
        mCloseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_login:
                String id = mIdEditText.getText().toString();
                String pw = mPasswordEditText.getText().toString();

                if(TextUtils.isEmpty(id) || TextUtils.isEmpty(pw)) {
                    Toast.makeText(CheckActivity.this, "아이디 또는 비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    if(mMap.containsKey(id)) {
                        Account account = mMap.get(id);

                        if(account.getPw().equals(pw)) {
                            mContainer.setVisibility(View.VISIBLE);
                            mIdTextView.setText("조회한 ID : " + id);
                            mBalanceTextView.setText("조회한 계좌의 잔액 : " + account.getBalance());
                        } else {
                            Toast.makeText(CheckActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(CheckActivity.this, "해당하는 계좌가 없습니다!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.btn_close:
                finish();
                break;
        }

    }
}
