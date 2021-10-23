package com.example.homework3_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener,View.OnTouchListener {

    //元素
    View pageBackground;
    Button resetButton;
    TextView countNumber,showCat;
    //變數
    int catCount = 0;
    String showCatS;
    //手勢偵測
    float X,Xbar,Y,Ybar;
    float delX,delY;
    int GesRange = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //PageBackground
        pageBackground=(View)findViewById(R.id.PageBackground);
        pageBackground.setOnTouchListener(this);
        //ResetButton
        resetButton = (Button) findViewById(R.id.ResetButton);
        resetButton.setOnClickListener(this);
        resetButton.setOnLongClickListener(this);
        //CountNumber
        countNumber = (TextView) findViewById(R.id.CountNumber);
        countNumber.setOnLongClickListener(this);
        //ShowCat
        showCat = (TextView) findViewById(R.id.ShowCat);
    }

    @Override
    public void onClick(View view) {
        //System.out.println("run onClick()...");
        if(view.getId()==R.id.ResetButton){
            catCount = 0;
            countNumber.setText(""+catCount);
            showCatsText(catCount);
            Toast.makeText(this, getString(R.string.resetMsg), Toast.LENGTH_SHORT).show();
        } else { //default
            Toast.makeText(this, getString(R.string.defaultMsg), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        //System.out.println("run onLongClick()...");
        if(view.getId()==R.id.ResetButton){
            Toast.makeText(this, getString(R.string.helpMsg_Reset), Toast.LENGTH_LONG).show();
        } else if(view.getId()==R.id.CountNumber){
            Toast.makeText(this, getString(R.string.helpMsg_Number), Toast.LENGTH_LONG).show();
        } else { //default
            Toast.makeText(this, getString(R.string.defaultMsg), Toast.LENGTH_SHORT).show();
        }
        //一定要回傳True!!!!!
        return true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //System.out.println("run onTouch()...");
        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            X = motionEvent.getX();
            Y = motionEvent.getY();
        }
        if(motionEvent.getAction()==MotionEvent.ACTION_UP) {
            Xbar = motionEvent.getX();
            Ybar = motionEvent.getY();
            delX = getRange(X-Xbar);
            delY = getRange(Y-Ybar);
            if(delX>delY){
                //水平變化量較大
                if(X-Xbar>GesRange){//left
                    catCount=catCount-1;
                } else if(Xbar-X>GesRange){//right
                    catCount=catCount+1;
                }
            } else {
                //垂直變化量較大
                if(Y-Ybar>GesRange){//up
                    catCount=catCount+2;
                } else if(Ybar-Y>GesRange){//down
                    catCount=catCount-2;
                }
            }
        }
        showCatsText(catCount);
        countNumber.setText(""+catCount);
        return true;
    }

    //背景貓貓顯示
    public void showCatsText(int count){
        showCatS = "";
        if(count>=0){
            for(int i=0;i<count;i++){ showCatS = showCatS + "(=^o o^=)"; }
        }
        if(count<0){
            for(int i=0;i>count;i--){ showCatS = showCatS + "(=^x x^=)"; }
        }
        showCat.setText(showCatS);
    }

    //絕對值
    public float getRange(float num){
        return (num>=0) ? num : -num;
    }

}