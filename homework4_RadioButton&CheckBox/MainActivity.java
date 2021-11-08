package com.example.homework4_checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener,RadioGroup.OnCheckedChangeListener{

    View pageBackground;
    CheckBox menuBox1,menuBox2,menuBox3,menuBox4,menuBox5,menuBox6;
    TextView menuTitle,colorTitle,showOrderList,showOrderMoney;
    RadioGroup colorGroup;
    Button submitButton;

    ArrayList<CompoundButton> OrderList = new ArrayList<>();
    String sOrderList = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageBackground = (View) findViewById(R.id.PageBackground);
        menuTitle = (TextView) findViewById(R.id.menu_title);
        showOrderList = (TextView) findViewById(R.id.chosenList);
        showOrderMoney = (TextView) findViewById(R.id.showMoney);
        colorTitle = (TextView) findViewById(R.id.color_title);
        setTextViewGradientColor(colorTitle);
        menuBox1 = (CheckBox) findViewById(R.id.checkBox1);
        menuBox1.setOnCheckedChangeListener(this);
        menuBox2 = (CheckBox) findViewById(R.id.checkBox2);
        menuBox2.setOnCheckedChangeListener(this);
        menuBox3 = (CheckBox) findViewById(R.id.checkBox3);
        menuBox3.setOnCheckedChangeListener(this);
        menuBox4 = (CheckBox) findViewById(R.id.checkBox4);
        menuBox4.setOnCheckedChangeListener(this);
        menuBox5 = (CheckBox) findViewById(R.id.checkBox5);
        menuBox5.setOnCheckedChangeListener(this);
        menuBox6 = (CheckBox) findViewById(R.id.checkBox6);
        menuBox6.setOnCheckedChangeListener(this);
        colorGroup = (RadioGroup) findViewById(R.id.color_group);
        colorGroup.setOnCheckedChangeListener(this);
        submitButton = (Button) findViewById(R.id.btnForSubmit);
        submitButton.setOnClickListener(this);
    }

    public void setMainColor(String colorString){
        pageBackground.setBackgroundColor(Color.parseColor("#55"+colorString.charAt(1)+colorString.charAt(2)+colorString.charAt(3)+colorString.charAt(4)+colorString.charAt(5)+colorString.charAt(6)));
        menuTitle.setTextColor(Color.parseColor(colorString));
        showOrderList.setTextColor(Color.parseColor(colorString));
        showOrderMoney.setTextColor(Color.parseColor(colorString));
        menuBox1.setTextColor(Color.parseColor(colorString));
        menuBox2.setTextColor(Color.parseColor(colorString));
        menuBox3.setTextColor(Color.parseColor(colorString));
        menuBox4.setTextColor(Color.parseColor(colorString));
        menuBox5.setTextColor(Color.parseColor(colorString));
        menuBox6.setTextColor(Color.parseColor(colorString));
    }

    public void setTextViewGradientColor(TextView textview){
        int[] colors = {Color.BLUE,Color.YELLOW,Color.RED};
        float[] position = {0f,0.4f,1f};
        LinearGradient mLinearGradient = new LinearGradient(0,0,textview.getPaint().getTextSize()*textview.getText().length(),0,colors,position, Shader.TileMode.CLAMP);
        textview.getPaint().setShader(mLinearGradient);
        textview.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        System.out.println("Run CompoundButton onCheckedChanged......");
        if(b) {
            OrderList.add(compoundButton);
        }
        else {
            OrderList.remove(compoundButton);
        }
        sOrderList = "";
        for(CompoundButton odl:OrderList){
            sOrderList += (odl.getText()+" ");
        }
        showOrderList.setText("目前餐點: " + sOrderList);
    }

    @Override
    public void onClick(View view) {
        System.out.println("Run onClick......");
        if(view.getId()==R.id.btnForSubmit){
            if(OrderList.size()==3){
                showOrderMoney.setText("結帳金額: 50元");
                Toast.makeText(this, "結帳成功!\n一共50元", Toast.LENGTH_SHORT).show();
            } else if(OrderList.size()==4){
                Toast.makeText(this, "結帳成功!\n一共60元", Toast.LENGTH_SHORT).show();
                showOrderMoney.setText("結帳金額: 60元");
            } else {
                Toast.makeText(this, "你必須點3樣菜或4樣菜", Toast.LENGTH_SHORT).show();
            }
        } else {
            System.out.println("Run onClick......ERROR");
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        System.out.println("Run RadioGroup onCheckedChanged......");
        if(radioGroup==colorGroup){
            switch (colorGroup.getCheckedRadioButtonId()){
                case R.id.color_purple:
                    setMainColor("#AA66CC");
                    break;
                case R.id.color_blue:
                    setMainColor("#33B5E5");
                    break;
                case R.id.color_green:
                    setMainColor("#99CC00");
                    break;
                case R.id.color_yellow:
                    setMainColor("#FFBB33");
                    break;
                case R.id.color_orange:
                    setMainColor("#FF8800");
                    break;
                case R.id.color_red:
                    setMainColor("#CC0000");
                    break;
            }
        } else {
            System.out.println("Run RadioGroup onCheckedChanged...... ERROR!!!");
        }
    }

}