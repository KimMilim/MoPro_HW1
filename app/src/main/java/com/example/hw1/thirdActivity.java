package com.example.hw1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class thirdActivity extends Activity {

    EditText edit1, edit2;
    Button btnAdd, btnSub,btnMul, btnDiv;
    TextView textResult;
    String num1, num2;
    Integer result;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = {R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,
            R.id.btn5, R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9};
    int i;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        edit1 = (EditText)findViewById(R.id.Edit1);
        edit2 = (EditText)findViewById(R.id.Edit2);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSub = (Button)findViewById(R.id.btnSub);
        btnMul = (Button)findViewById(R.id.btnMul);
        btnDiv = (Button)findViewById(R.id.btnDiv);
        textResult = (TextView)findViewById(R.id.result_text);

        for(int i=0;i<numBtnIDs.length;i++){
            numButtons[i]=(Button) findViewById(numBtnIDs[i]);
        }

        for(int i=0;i<numBtnIDs.length;i++){
            final int idx;
            idx = i;

            numButtons[idx].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(edit1.isFocused()){
                        num1 = edit1.getText().toString() + numButtons[idx].getText().toString();
                        edit1.setText(num1);
                    }else if(edit2.isFocused()){
                        num2= edit2.getText().toString() + numButtons[idx].getText().toString();
                        edit2.setText(num2);
                    }
                }
            });
        }

        Button btnreturnmain = (Button)findViewById(R.id.btnreturnmain);

        btnreturnmain.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),
                        MainActivity.class);
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.equals("") || num2.equals("") ){
                    Toast.makeText(thirdActivity.this, "숫자를 먼저 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else{
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.equals("") || num2.equals("") ){
                    Toast.makeText(thirdActivity.this, "숫자를 먼저 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    result = Integer.parseInt(num1) - Integer.parseInt(num2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.equals("") || num2.equals("") ){
                    Toast.makeText(thirdActivity.this, "숫자를 먼저 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    result = Integer.parseInt(num1) * Integer.parseInt(num2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.equals("") || num2.equals("") ){
                    Toast.makeText(thirdActivity.this, "숫자를 먼저 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    result = Integer.parseInt(num1) / Integer.parseInt(num2);
                    textResult.setText("계산 결과 : " + result.toString());
                }
            }
        });
    }
}
