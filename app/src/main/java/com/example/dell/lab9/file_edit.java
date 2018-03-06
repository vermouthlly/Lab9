package com.example.dell.lab9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class file_edit extends AppCompatActivity {

    private Button b1, b2, b3, b4;
    private EditText edit,file_name;
    private String FILE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_edit);
        b1= findViewById(R.id.button_save);
        b2= findViewById(R.id.button_load);
        b3=findViewById(R.id.button_clear);
        b4=findViewById(R.id.button_delete);
        file_name=findViewById(R.id.f1);
        edit=findViewById(R.id.f2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream out= null;
                BufferedWriter writer= null;
                try {
                    FILE_NAME=file_name.getText().toString();
                    out= openFileOutput(FILE_NAME, MODE_PRIVATE);
                    writer= new BufferedWriter(new OutputStreamWriter(out));
                    writer.write(edit.getText().toString());
                    Toast.makeText(file_edit.this, "Save successfully.", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (writer!= null) writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream in= null;
                BufferedReader reader= null;
                String all_words= "";
                try {
                    in= openFileInput(file_name.getText().toString());
                    if (in == null) Toast.makeText(file_edit.this, "Fail to load file.", Toast.LENGTH_SHORT).show();
                    else {
                        reader= new BufferedReader(new InputStreamReader(in));
                        String temp= "";
                        while ((temp= reader.readLine())!= null) {
                            all_words+= temp;
                        }
                        edit.setText(all_words);
                        Toast.makeText(file_edit.this, "Load Successfully.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(file_edit.this, "Fail to load file.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } finally {
                    try {
                        if (reader!= null) reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText("");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FILE_NAME=file_name.getText().toString();
                deleteFile(FILE_NAME);
                Toast.makeText(file_edit.this, "Delete successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
