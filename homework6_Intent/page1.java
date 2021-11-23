package com.example.homework6_intentdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class page1 extends AppCompatActivity implements View.OnClickListener {

    String lastPath = "";
    public static final int A_TO_B = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
        ((Button) findViewById(R.id.page1_to_page2)).setOnClickListener(this);
        ((Button) findViewById(R.id.page1_back)).setOnClickListener(this);
        lastPath = getIntent().getStringExtra("path");
        ((TextView) findViewById(R.id.page1_show_path)).setText(lastPath);
        lastPath += "A → ";
        System.out.println("onCreate\nnow : A");
        System.out.println(lastPath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == A_TO_B){
            if (resultCode == RESULT_OK){
                lastPath = data.getStringExtra("backPath");
                ((TextView) findViewById(R.id.page1_show_path)).setText(lastPath);
                lastPath += "A → ";
                System.out.println("onActivityResult\nnow : A");
                System.out.println(lastPath);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.page1_to_page2){
            Intent intent = new Intent();
            intent.setClass(page1.this,page2.class);
            intent.putExtra("path",lastPath);
            try {
                //startActivity(intent);
                startActivityForResult(intent,A_TO_B);
            } catch (Exception e){
                System.out.println("\n\n\nError\n\n\n");
                System.out.println(e+"\n\n\n");
            }
        } else if(view.getId()==R.id.page1_back){
            getIntent().putExtra("backPath",lastPath);
            setResult(RESULT_OK,getIntent());
            finish();
        }
    }
}
