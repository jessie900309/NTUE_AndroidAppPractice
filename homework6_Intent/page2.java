package com.example.homework6_intentdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class page2 extends AppCompatActivity implements View.OnClickListener {

    String lastPath = "";
    public static final int B_TO_C = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        ((Button) findViewById(R.id.page2_to_page3)).setOnClickListener(this);
        ((Button) findViewById(R.id.page2_back)).setOnClickListener(this);
        lastPath = getIntent().getStringExtra("path");
        ((TextView) findViewById(R.id.page2_show_path)).setText(lastPath);
        lastPath += "B → ";
        System.out.println("onCreate\nnow : B");
        System.out.println(lastPath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == B_TO_C){
            if (resultCode == RESULT_OK){
                lastPath = data.getStringExtra("backPath");
                ((TextView) findViewById(R.id.page2_show_path)).setText(lastPath);
                lastPath += "B → ";
                System.out.println("onActivityResult\nnow : B");
                System.out.println(lastPath);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.page2_to_page3){
            Intent intent = new Intent();
            intent.setClass(page2.this,page3.class);
            intent.putExtra("path",lastPath);
            try {
                //startActivity(intent);
                startActivityForResult(intent,B_TO_C);
            } catch (Exception e){
                System.out.println("\n\n\nError\n\n\n");
                System.out.println(e+"\n\n\n");
            }
        } else if(view.getId()==R.id.page2_back){
            getIntent().putExtra("backPath",lastPath);
            setResult(RESULT_OK,getIntent());
            finish();
        }
    }
}

