package com.example.pr3q3writefile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText edt_input;
    Button btn_Save,btn_Load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_input=findViewById(R.id.ed_txt_input);
        btn_Save=findViewById(R.id.btn_save);
        btn_Load=findViewById(R.id.btn_load);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    FileOutputStream fOut = openFileOutput("myFile.txt",MODE_APPEND);
                    OutputStreamWriter writer=new OutputStreamWriter(fOut);
                    writer.write(edt_input.getText().toString());
                    writer.flush();
                    writer.close();
                    Toast.makeText(getApplicationContext(), "Data saved in file", Toast.LENGTH_SHORT).show();
                    edt_input.setText("");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }
        });

        btn_Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String line, fileContent="";
                try {
                    FileInputStream fIn = openFileInput("myFile.txt");
                    BufferedReader reader=new BufferedReader(new InputStreamReader(fIn));

                    while ((line=reader.readLine())!=null)
                    {
                        fileContent +=line;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                edt_input.setText(fileContent);
            }
        });

    }
}
