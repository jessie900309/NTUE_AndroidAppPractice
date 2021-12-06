package com.example.homework8_sqlitesingle;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class PageForShowInfo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // side menu
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;

    // widget
    TextView ShowPath,ShowSize,ShowLimit;

    // SQLite
    static final String dbName = "homework8_test_database";
    private static SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subpage_showinfo);

        // side menu
        mNavigationView = (NavigationView) findViewById(R.id.showinfo_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setItemTextColor(null); //關閉選項變色功能
        mNavigationView.setItemIconTintList(null);
        mDrawerLayout = findViewById(R.id.showinfoDrawerLayout);

        // widget
        ShowPath = (TextView) findViewById(R.id.source_show_path);
        ShowSize = (TextView) findViewById(R.id.source_show_size);
        ShowLimit = (TextView) findViewById(R.id.source_show_limit);

        // SQLite
        db = openOrCreateDatabase(dbName, Context.MODE_PRIVATE,null);

        try{
            ShowPath.setText(" "+db.getPath()+"\n");
            ShowSize.setText(" "+db.getPageSize()+"\n");
            ShowLimit.setText(" "+db.getMaximumSize()+"\n");
        } catch (Exception e){
            ShowPath.setText("OuO nothing");
            ShowSize.setText("OuO nothing");
            ShowLimit.setText("OuO nothing");
        }

        db.close();

    }

    // -------------------------- side menu --------------------------

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_showInfo){
            //self : do nothing
        }else {
            try{
                Intent intent = new Intent();
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent.setClass(PageForShowInfo.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.menu_add:
                        intent.setClass(PageForShowInfo.this,PageForAdd.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.menu_show:
                        intent.setClass(PageForShowInfo.this,PageForShow.class);
                        startActivity(intent);
                        finish();
                        break;
//                    case R.id.menu_showInfo:
//                        intent.setClass(PageForShowInfo.this,PageForShowInfo.class);
//                        startActivity(intent);
//                        finish();
//                        break;
                    default:
                        //throw new Exception("Exception : switch (item.getItemId()){...}");
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
