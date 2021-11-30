package com.example.homework7_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class SubpageAboutSub2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subpage_about2);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view_AboutSub2);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setItemTextColor(null);
        mNavigationView.setItemIconTintList(null);
        mDrawerLayout = findViewById(R.id.about2DrawerLayout);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.about_subitem2){
            //self : do nothing
            Toast.makeText(this, "你點選了「關於OAO!?!?」", Toast.LENGTH_SHORT).show();
        }else {

            try{

                Intent intent = new Intent();

                switch (item.getItemId()){

                    case R.id.action_help:
                        Toast.makeText(this, "你點選了「使用說明」", Toast.LENGTH_SHORT).show();
                        intent.setClass(SubpageAboutSub2.this,SubpageHelp.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.settings_subitem1:
                        Toast.makeText(this, "你點選了「設定OuO ???」", Toast.LENGTH_SHORT).show();
                        intent.setClass(SubpageAboutSub2.this,SubpageSettingSub1.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.settings_subitem2:
                        Toast.makeText(this, "你點選了「設定OAO!?!?」", Toast.LENGTH_SHORT).show();
                        intent.setClass(SubpageAboutSub2.this,SubpageSettingSub2.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.about_subitem1:
                        Toast.makeText(this, "你點選了「關於OuO ???」", Toast.LENGTH_SHORT).show();
                        intent.setClass(SubpageAboutSub2.this,SubpageAboutSub1.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.action_home:
                        Toast.makeText(this, "你點選了「主頁」", Toast.LENGTH_SHORT).show();
                        intent.setClass(SubpageAboutSub2.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                    default:
                        //self : do nothing

                }
            }catch (Exception e){
                System.out.println("\n\n\n\n\nException!!!\n\n\n\n\n");
                System.out.println("\n\n\n\n\n"+e+"\n\n\n\n\n");
                System.out.println("\n\n\n\n\nException!!!\n\n\n\n\n");
            }

        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawers();
            }else finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
