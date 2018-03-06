package com.example.dell.lab9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText p1, p2, p3;
    private Button b1, b2;
    private String temp;
    private boolean flag= false; //标记是否已经存储了密码
    private SharedPreferences sharedpreferences;
    private static final String PREFERENCE_NAME = "PASSWORD_PREFERENCE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1=findViewById(R.id.pw1);
        p2=findViewById(R.id.pw2);
        p3=findViewById(R.id.pw3);
        b1=findViewById(R.id.b_ok);
        b2=findViewById(R.id.b_clear);
        sharedpreferences= getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        temp= sharedpreferences.getString("KEY_PASSWORD", "");
        if (!temp.isEmpty()) {  //已经存储了密码
            p1.setVisibility(View.GONE);  //不可见的且不占用原来的布局空间
            p2.setVisibility(View.GONE);
            p3.setVisibility(View.VISIBLE);
            flag= true;
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    String s= p3.getText().toString();
                    if (s.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Password cannot be empty.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (s.equals(temp)) {
                            Intent intent2 = new Intent(MainActivity.this, file_edit.class);
                            startActivity(intent2);
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid Password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    String s1= p1.getText().toString();
                    String s2= p2.getText().toString();
                    if (s1.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Password cannot be empty.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!s1.equals(s2)) {
                            Toast.makeText(MainActivity.this, "Password Mismatch.", Toast.LENGTH_SHORT).show();
                        } else {
                            SharedPreferences.Editor editor= sharedpreferences.edit();
                            editor.putString("KEY_PASSWORD", s1);
                            editor.commit();
                            Intent intent = new Intent(MainActivity.this, file_edit.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    p3.setText("");
                } else {
                    p1.setText("");
                    p2.setText("");
                }
            }
        });
    }
}
