package com.example.homework2_texteditform;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView TextField1,TextField2,TextField3,ShowResult;
    EditText Text1,Text2,Text3;
    Button button;
    View PageBackground,TextField1Background,TextField2Background,TextField3Background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextField1 = (TextView) findViewById(R.id.TextEditTitle1);
        TextField1Background = findViewById(R.id.TextField1Background);
        TextField2 = (TextView) findViewById(R.id.TextEditTitle2);
        TextField2Background = findViewById(R.id.TextField2Background);
        TextField3 = (TextView) findViewById(R.id.TextEditTitle3);
        TextField3Background = findViewById(R.id.TextField3Background);
        button = (Button) findViewById(R.id.button);
        PageBackground = findViewById(R.id.PageBackground);

        //settingColor
        Random randomObj = new Random();

        //灰階背景、按鈕
        int BGnum = randomObj.nextInt(256);
        PageBackground.setBackgroundColor(Color.rgb(BGnum,BGnum,BGnum));
        button.setBackgroundColor(Color.rgb(255-BGnum,255-BGnum,255-BGnum));
        button.setTextColor(Color.rgb(BGnum,BGnum,BGnum));

        //基本數值(避免太黑OAO)
        int OuOcolorCtrl = 100;
        int Block1num = randomObj.nextInt(256);
        int Block2num = randomObj.nextInt(256);
        int Block3num = randomObj.nextInt(256);

        //01 R
        TextField1Background.setBackgroundColor(Color.rgb(Block1num,OuOcolorCtrl,OuOcolorCtrl));
        TextField1.setTextColor(Color.rgb(255-Block1num,255-OuOcolorCtrl,255-OuOcolorCtrl));
        //02 G
        TextField2Background.setBackgroundColor(Color.rgb(OuOcolorCtrl,Block2num,OuOcolorCtrl));
        TextField2.setTextColor(Color.rgb(255-OuOcolorCtrl,255-Block2num,255-OuOcolorCtrl));
        //03 B
        TextField3Background.setBackgroundColor(Color.rgb(OuOcolorCtrl,OuOcolorCtrl,Block3num));
        TextField3.setTextColor(Color.rgb(255-OuOcolorCtrl,255-OuOcolorCtrl,255-Block3num));

    }

    public void getValues(View view){

        ShowResult = (TextView) findViewById(R.id.result);
        Text1 = (EditText) findViewById(R.id.TextEdit1);
        Text2 = (EditText) findViewById(R.id.TextEdit2);
        Text3 = (EditText) findViewById(R.id.TextEdit3);
        String LastN = Text1.getText().toString();
        String FirstN = Text2.getText().toString();
        String PhoneN = Text3.getText().toString();
        if (LastN.matches("")||FirstN.matches("")||PhoneN.matches("")) {
            Toast.makeText(this, "你迷有輸入完整 QAQ", Toast.LENGTH_SHORT).show();
            return; //沒輸入完整就不show!!!
        }
        if (!PhoneN.matches("喵")) {
            Toast.makeText(this, "你為什麼不喵 QAQ", Toast.LENGTH_SHORT).show();
            return; //沒喵就不show!!!
        }
        ShowResult.setTextColor(Color.rgb(150,150,150));
        ShowResult.setText("\n\n\n" +LastN+FirstN+ "\n(=^o o^=) 你好，喵喵咪喵!! ");
    }

}