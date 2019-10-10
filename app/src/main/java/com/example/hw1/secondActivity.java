package com.example.hw1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class secondActivity  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        final CheckBox id_check = (CheckBox) findViewById(R.id.id_check) ;
        final CheckBox pw_check = (CheckBox) findViewById(R.id.pw_check) ;
        Button btn_id_is = (Button) findViewById(R.id.btn_id_is);
        Button btn_pw_pass = (Button) findViewById(R.id.btn_pw_pass);
        Button btnFirstReturn = (Button) findViewById(R.id.btnFirstReturn);
        final EditText user_id = (EditText) findViewById(R.id.id_make); //아이디
        final EditText name = (EditText) findViewById(R.id.edit_name);
        final EditText add = (EditText) findViewById(R.id.edit_add);
        final EditText p_num = (EditText) findViewById(R.id.edit_number);
        final EditText user_pw = (EditText) findViewById(R.id.pw_make);


        btnFirstReturn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                finish();
            }
        });


        //id 중복확인
        btn_id_is.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean id_chk = false;

                try{
                    BufferedReader br = new BufferedReader(new FileReader(getFilesDir()+"user_data9.txt"));
                    String readStr = "";
                    String str = null;
                    while(((str = br.readLine()) != null)){
                        //readStr += str +"\n";
                        int i=0;
                        while(str.charAt(i)!=' ' && str.charAt(i)!='\n' && str.charAt(i)!='\0'){
                            i++;
                        }
                        readStr = str.substring(0,i);
                        if(readStr.equals(user_id.getText().toString())){
                            Toast.makeText(secondActivity.this, "이미 사용중인 아이디입니다.", Toast.LENGTH_SHORT).show();
                            id_chk = true;
                            id_check.setChecked(false);
                            break;
                        }
                    }
                    if(!id_chk){
                        Toast.makeText(secondActivity.this, "사용가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                        id_check.setChecked(true);
                    }
                    br.close();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //비밀번호 규칙 확인
        btn_pw_pass.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                boolean pw_chk = false;
                int num=0;
                int al=0;
                int i=0;
                String pw = user_pw.getText().toString();
                if( pw.length() > 6 || pw.length() < 5){
                    pw_check.setChecked(false);
                }
                else {
                    while (pw.length() > i) {
                        if (48 <= pw.charAt(i) && pw.charAt(i) <= 57) {
                            num++;
                        } else {
                            al++;
                        }
                        i++;
                    }
                    if (num > 0 && al > 0) {
                        pw_check.setChecked(true);
                    } else {
                        pw_check.setChecked(false);
                    }
                }
            }
        });

        //이용약관 동의확인
        Button Signup_com = (Button) findViewById(R.id.signup_com);
        final RadioButton btnaccept = (RadioButton) findViewById(R.id.accept);

        Signup_com.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                if(id_check.isChecked()==false){
                    Toast.makeText(secondActivity.this, "아이디 중복을 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(pw_check.isChecked()==false){
                    Toast.makeText(secondActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(btnaccept.isChecked()==false){
                    Toast.makeText(secondActivity.this, "약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    //파일에 user 정보 저장
                    try{
                        BufferedWriter bw = new BufferedWriter(new FileWriter(getFilesDir() + "user_data9.txt", true));
                        bw.write(user_id.getText().toString() + " " + user_pw.getText().toString() + " " + name.getText().toString() + " " + add.getText().toString() + " " + p_num.getText().toString() + "\n");
                        bw.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(getApplicationContext(),
                           MainActivity.class);
                    finish();
                }
            }
        });
    }
}
