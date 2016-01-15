package com.massivcode.listviewexam2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mMakeButton, mCheckButton, mAdminButton, mCloseButton;
    private HashMap<String, Account> mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mMakeButton = (Button) findViewById(R.id.btn_make);
        mCheckButton = (Button) findViewById(R.id.btn_check);
        mAdminButton = (Button) findViewById(R.id.btn_admin);
        mCloseButton = (Button) findViewById(R.id.btn_close);

        mMakeButton.setOnClickListener(this);
        mCheckButton.setOnClickListener(this);
        mAdminButton.setOnClickListener(this);
        mCloseButton.setOnClickListener(this);

        mMap = new HashMap<>();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_make:
                startActivityForResult(new Intent(MainActivity.this, JoinActivity.class), 1000);
                break;

            case R.id.btn_check:
                if (mMap.size() == 0) {
                    Toast.makeText(MainActivity.this, "개설된 계좌가 없으니 계좌 개설 먼저 해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, CheckActivity.class);
                    intent.putExtra("data", mMap);
                    startActivity(intent);
                }
                break;

            case R.id.btn_admin:
                if (mMap.size() == 0) {
                    Toast.makeText(MainActivity.this, "개설된 계좌가 없으니 계좌 개설 먼저 해주세요!", Toast.LENGTH_SHORT).show();
                } else {
//                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
//                    intent.putExtra("data", mMap);
//                    startActivity(intent);
                    popUpAlertDiaiogWithCustomView();
                }
                break;

            case R.id.btn_close:
                finish();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Account account = (Account) data.getSerializableExtra("data");
                    mMap.put(account.getId(), account);
                    Toast.makeText(MainActivity.this, "데이터가 추가되었습니다!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void popUpAlertDiaiogWithCustomView() {
        // AlertDialog 를 쉽게 만들도록 도와주는 AlertDialog.Builder
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // CustomView 를 세팅하여 사용되는 경우 Inflater 를 이용하여 View 를 inflate 해야한다.
        View innerView = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        // inflate 한 CustomView 를 세팅하는 메소드
        builder.setView(innerView);
        // 팝업된 AlertDialog 가 다른 곳을 클릭했을 때 닫히지 않도록 설정하는 메소드
        builder.setCancelable(false);

        /**
         * CustomView 에 위치한 View 들을 객체화하는 부분
         */
        final EditText editText1 = (EditText) innerView.findViewById(R.id.et_1);
        final EditText editText2 = (EditText) innerView.findViewById(R.id.et_2);
        Button closeButton = (Button) innerView.findViewById(R.id.btn_close);
        Button loginButton = (Button) innerView.findViewById(R.id.btn_login);


        // 다이얼로그 빌더에 세팅한 여러가지 값이 적용된 AlertDialog 객체를 만드는 부분
        final AlertDialog dialog = builder.create();
        // 그렇게 만들어진 AlertDialog 객체를 화면에 출력하는 부분
        dialog.show();

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "닫기 버튼이 눌렸습니다!", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText1.getText().toString();
                String pw = editText2.getText().toString();
                if("admin".equals(id) && "admin".equals(pw)) {
                    Toast.makeText(MainActivity.this, "로그인에 성공하셨습니다!", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                    intent.putExtra("data", mMap);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
