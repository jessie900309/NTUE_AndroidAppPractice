package com.example.homework8_sqlitesingle;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class PageForShow extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // side menu
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;

    // widget
    ListView listView;

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
        setContentView(R.layout.subpage_show);

        //side menu
        mNavigationView = (NavigationView) findViewById(R.id.show_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setItemTextColor(null);
        mNavigationView.setItemIconTintList(null);
        mDrawerLayout = findViewById(R.id.showDrawerLayout);

        // widget
        listView = (ListView) findViewById(R.id.show_listview);

        // SQLite
        try {
            db = openOrCreateDatabase(dbName, Context.MODE_PRIVATE,null);
            cursor = db.rawQuery("SELECT * FROM "+tbName,null);
            if(cursor.getCount()==0){
                Toast.makeText(this,"資料不存在",Toast.LENGTH_SHORT);
            } else {
                adapter = new SimpleCursorAdapter(this,
                        R.layout.showitem,
                        cursor,
                        FROM,
                        new int[] {R.id.listName,R.id.listPrice},
                        0);
                listView.setAdapter(adapter);
            }
            db.close();
        } catch (Exception e){
            System.out.println("\n\n\n");
            System.out.println("\n\n\n"+e);
            System.out.println("\n\n\n");
        }

    }


    // -------------------------- side menu --------------------------

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_show){
            //self : do nothing
        }else {
            try{
                Intent intent = new Intent();
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent.setClass(PageForShow.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.menu_add:
                        intent.setClass(PageForShow.this,PageForAdd.class);
                        startActivity(intent);
                        finish();
                        break;
//                    case R.id.menu_show:
//                        intent.setClass(PageForShow.this,PageForShow.class);
//                        startActivity(intent);
//                        finish();
//                        break;
                    case R.id.menu_showInfo:
                        intent.setClass(PageForShow.this,PageForShowInfo.class);
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
