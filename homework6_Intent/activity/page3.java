package com.example.homework6_intentdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class page3 extends AppCompatActivity implements View.OnClickListener {

    String lastPath = "";
    public static final int C_TO_A = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3);
        ((Button) findViewById(R.id.page3_to_page1)).setOnClickListener(this);
        ((Button) findViewById(R.id.page3_back)).setOnClickListener(this);
        lastPath = getIntent().getStringExtra("path");
        ((TextView) findViewById(R.id.page3_show_path)).setText(lastPath);
        lastPath += "C → ";
        System.out.println("onCreate\nnow : C");
        System.out.println(lastPath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == C_TO_A){
            if (resultCode == RESULT_OK){
                lastPath = data.getStringExtra("backPath");
                ((TextView) findViewById(R.id.page3_show_path)).setText(lastPath);
                lastPath += "C → ";
                System.out.println("onActivityResult\nnow : C");
                System.out.println(lastPath);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.page3_to_page1){
            Intent intent = new Intent();
            intent.setClass(page3.this,page1.class);
            intent.putExtra("path",lastPath);
            try {
                //startActivity(intent);
                startActivityForResult(intent,C_TO_A);
            } catch (Exception e){
                System.out.println("\n\n\nError\n\n\n");
                System.out.println(e+"\n\n\n");
            }
        } else if(view.getId()==R.id.page3_back){
            getIntent().putExtra("backPath",lastPath);
            setResult(RESULT_OK,getIntent());
            finish();
        }
    }
}

