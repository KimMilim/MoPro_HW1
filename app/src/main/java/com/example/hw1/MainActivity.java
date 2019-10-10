package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Signin = (Button) findViewById(R.id.login);
        Button Signup = (Button) findViewById(R.id.Signup);
        final EditText user_id = (EditText) findViewById(R.id.user_id);
        final EditText user_pw = (EditText) findViewById(R.id.user_pw);

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(getFilesDir() + "user_data9.txt", true));
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        //로그인 정보 확인
        Signin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    boolean pw_chk = false, user_chk = false;
                    BufferedReader br = new BufferedReader(new FileReader(getFilesDir()+"user_data9.txt"));
                    String readStr = "";
                    String str = null;

                    while(((str = br.readLine()) != null)){
                        int i=0;
                        while(str.charAt(i)!=' ' && str.charAt(i)!='\n' && str.charAt(i)!='\0'){
                            i++;
                        }
                        readStr = str.substring(0,i);

                        //아이디가 있으면 비밀번호 확인
                        if(readStr.equals(user_id.getText().toString())){
                            int j = i+1;
                            while(str.charAt(j)!=' ' && str.charAt(j)!='\n' && str.charAt(j)!='\0'){
                                j++;
                            }
                            readStr = str.substring(i+1,j);
                            //계산기로 이동
                            if(readStr.equals(user_pw.getText().toString())){
                                //Intent third = new Intent(getApplicationContext(),thirdActivity.class);
                                //startActivity(third);
                                user_chk = true;
                                break;
                            }else{
                                Toast.makeText(MainActivity.this, "로그인 정보가 맞지 않습니다.", Toast.LENGTH_LONG).show();
                                pw_chk = true;
                                break;
                            }
                        }
                    }
                    br.close();
                    if(!pw_chk&&!user_chk)
                        Toast.makeText(MainActivity.this, "로그인 정보가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                    else if(user_chk){
                        Intent intent = new Intent(getApplicationContext(),
                                thirdActivity.class);
                        startActivity(intent);
                    }
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),
                        secondActivity.class);
                startActivity(intent);
            }
        });


    }
}
