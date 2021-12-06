package com.example.homework8_sqlitesingle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // side menu
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;

    // SQLite
    static final String dbName = "homework8_test_database";
    static final String tbName = "homework8_test_table1";
    static final int itemLengthMAX = 100;
    static final String[] FROM = new String[] {"name","price"};
    private static SQLiteDatabase db;
    Cursor cursor;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //side menu
        mNavigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setItemTextColor(null); //關閉選項變色功能
        mNavigationView.setItemIconTintList(null);
        mDrawerLayout = findViewById(R.id.mainDrawerLayout);

        // SQLite
        try{
            db = openOrCreateDatabase(dbName, Context.MODE_PRIVATE,null);
            String sqlCodeForCreateTable =
                    "CREATE TABLE IF NOT EXISTS " +
                            tbName + "(" +
                            "_id INTERGER PRIMARY KEY AUTOINCREMENT," +
                            "name VARCHAR(16)," +
                            "price VARCHAR(16)" +
                            ")";
            db.execSQL(sqlCodeForCreateTable);
            cursor = db.rawQuery("SELECT * FROM "+tbName,null);
            if(cursor.getCount()==0){
                addData("測試資料","OuO?");
                System.out.println("insert 測試資料");
            }
            db.close();
        } catch (Exception e){
            System.out.println("\n\n\n");
            System.out.println("\n\n\n"+e);
            System.out.println("\n\n\n");
        }

    }
    // -------------------------- SQLite -----------------------------

    public void addData(String name,String price){
        ContentValues contentValues = new ContentValues(2);
        contentValues.put(FROM[0],name);
        contentValues.put(FROM[1],price);
        db.insert(tbName,null,contentValues);
    }

    // -------------------------- side menu --------------------------

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_home){
            //self : do nothing
        }else {
            try{
                Intent intent = new Intent();
                switch (item.getItemId()){
//                    case R.id.action_home:
//                        intent.setClass(MainActivity.this,MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                        break;
                    case R.id.menu_add:
                        intent.setClass(MainActivity.this,PageForAdd.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.menu_show:
                        intent.setClass(MainActivity.this,PageForShow.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.menu_showInfo:
                        intent.setClass(MainActivity.this,PageForShowInfo.class);
                        startActivity(intent);
                        finish();
                        break;
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