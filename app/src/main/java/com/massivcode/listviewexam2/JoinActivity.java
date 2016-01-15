package com.massivcode.listviewexam2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mIdEditText, mPasswordEditText, mPasswordConfirmEditText, mBalanceEditText;
    private Button mResetButton, mMakeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        init();
    }

    private void init() {
        mResetButton = (Button) findViewById(R.id.btn_reset);
        mMakeButton = (Button) findViewById(R.id.btn_make);
        mIdEditText = (EditText) findViewById(R.id.id_et);
        mPasswordEditText = (EditText) findViewById(R.id.pw_et);
        mPasswordConfirmEditText = (EditText) findViewById(R.id.pw_confirm_et);
        mBalanceEditText = (EditText) findViewById(R.id.balance_et);

        mResetButton.setOnClickListener(this);
        mMakeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_reset:
                mIdEditText.setText("");
                mPasswordEditText.setText("");
                mPasswordConfirmEditText.setText("");
                mBalanceEditText.setText("");
                break;

            case R.id.btn_make:
                String id = mIdEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String balance = mBalanceEditText.getText().toString();

                if (TextUtils.isEmpty(id) || TextUtils.isEmpty(password) || TextUtils.isEmpty(balance)) {
                    Toast.makeText(JoinActivity.this, "값을 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    Account account = new Account(id, password, balance);
                    Intent intent = new Intent();
                    intent.putExtra("data", account);
                    setResult(RESULT_OK, intent);
                    finish();
                }

                break;

        }
    }
}
