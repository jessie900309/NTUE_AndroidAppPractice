package com.example.homework6_intentdata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String lastPath = "";
    public static final int MAIN_TO_B = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.start_to_page2)).setOnClickListener(this);
        lastPath += "A → ";
        System.out.println("onCreate\nnow : MainActivity");
        System.out.println(lastPath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MAIN_TO_B){
            if (resultCode == RESULT_OK){
                lastPath = data.getStringExtra("backPath");
                ((TextView) findViewById(R.id.main_show_path)).setText(lastPath);
                lastPath += "A → ";
                System.out.println("onActivityResult\nnow : MainActivity");
                System.out.println(lastPath);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.start_to_page2){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,page2.class);
            intent.putExtra("path",lastPath);
            try {
                //startActivity(intent);
                startActivityForResult(intent,MAIN_TO_B);
            } catch (Exception e){
                System.out.println("\n\n\nError\n\n\n");
                System.out.println(e+"\n\n\n");
            }
        }
    }
}