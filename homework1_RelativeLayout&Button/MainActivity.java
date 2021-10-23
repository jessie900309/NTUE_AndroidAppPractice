package com.example.homework1_shownumbertext;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClickNumberBtn(View view) {
        //取得畫面字串
        TextView text = (TextView)findViewById(R.id.showText);
        //取得按鈕上的數字
        Button srcButton = (Button)view;
        String buttonText = srcButton.getText().toString();
        //改變畫面字串
        text.setText(buttonText);
        text.setTextColor(Color.parseColor("#5A5AAD"));
    }

    public void ClickLeftBtn(View view){//"(=^o o^=)"
        //取得畫面字串
        TextView text = (TextView)findViewById(R.id.showText);
        text.setText("喵喵喵咪喵咪喵OuO!!");
        text.setTextColor(Color.parseColor("#AE57A4"));
    }

    public void ClickRightBtn(View view){//"reset"
        //取得畫面字串
        TextView text = (TextView)findViewById(R.id.showText);
        text.setText("(Press the Button OuO!)");
        text.setTextColor(Color.GRAY);
    }
}