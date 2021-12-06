package com.example.homework8_sqlitesingle;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class PageForAdd extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, View.OnTouchListener {

    // side menu
    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;

    // widget
    View pageBackground;
    TextInputEditText nameInput,priceInput;
    String itemName, itemPrice;

    // SQLite
    static final String dbName = "homework8_test_database";
    static final String tbName = "homework8_test_table1";
    static final int itemLengthMAX = 100;
    static final String[] FROM = new String[] {"name","price"};
    private static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subpage_add);

        //side menu
        mNavigationView = (NavigationView) findViewById(R.id.add_navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setItemTextColor(null);
        mNavigationView.setItemIconTintList(null);
        mDrawerLayout = findViewById(R.id.addDrawerLayout);

        // widget
        ((Button)findViewById(R.id.adding_submit_button)).setOnClickListener(this);
        nameInput = (TextInputEditText) findViewById(R.id.input_name);
        priceInput = (TextInputEditText) findViewById(R.id.input_price);
        pageBackground = (View) findViewById(R.id.add_page_background);
        pageBackground.setOnClickListener(this);
        pageBackground.setOnTouchListener(this);

        // SQLite
        try{
            db = openOrCreateDatabase(dbName, Context.MODE_PRIVATE,null);
            String sqlCodeForCreateTable =
                    "CREATE TABLE IF NOT EXISTS " +
                    tbName + "(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(16)," +
                    "price VARCHAR(16)" +
                    ")";
            db.execSQL(sqlCodeForCreateTable);
        } catch (Exception e){
            System.out.println("\n\n\n");
            System.out.println("\n\n\n"+e);
            System.out.println("\n\n\n");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //SQLite
            case R.id.adding_submit_button:
                try {
                    itemName = nameInput.getText().toString();
                    itemPrice = priceInput.getText().toString();
                    System.out.println("itemName : "+itemName);
                    System.out.println("itemPrice : "+itemPrice);
                    if(itemName.equals("")||itemPrice.equals("")){
                        throw new Exception("value is null !!!");
                    } else {
                        addData(itemName,itemPrice);
                        String doneMsg = "itemName : "+itemName+"\n"+ "itemPrice : "+itemPrice+"\n"+ "新增成功OuO!";
                        Toast.makeText(this,doneMsg,Toast.LENGTH_SHORT).show();
                        clearText();
                        closeKeyBoard(view);
                        getWindow().getDecorView().clearFocus();
                    }
                } catch (Exception e){
                    String doneMsg = "值不可為空，新增失敗QAQ!";
                    Toast.makeText(this,doneMsg,Toast.LENGTH_SHORT).show();
                    clearText();
                    closeKeyBoard(view);
                    getWindow().getDecorView().clearFocus();
                }
                break;
            //TextInputEditText
            case R.id.add_page_background:
                closeKeyBoard(view);
                break;
            //Other
            default:
                //do nothing
        }
    }


    // -------------------------- SQLite -----------------------------

    public void addData(String name,String price){
        ContentValues contentValues = new ContentValues(2);
        contentValues.put(FROM[0],name);
        contentValues.put(FROM[1],price);
        db.insert(tbName,null,contentValues);
    }

    // -------------------------- TextInputEditText ------------------

    public void clearText(){
        itemName = itemPrice = "";
        nameInput.getText().clear();
        priceInput.getText().clear();
    }

    public void closeKeyBoard(View view){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void autoClickView(View view, float x, float y){
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime,downTime,MotionEvent.ACTION_DOWN,x,y,0);
        downTime += 10;
        final MotionEvent upEvent = MotionEvent.obtain(downTime,downTime,MotionEvent.ACTION_UP,x,y,0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(view.getId()==R.id.add_page_background){
            autoClickView(view,100,100);
        }
        return true;
    }

    // -------------------------- side menu --------------------------

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_add){
            //self : do nothing
        }else {
            try{
                Intent intent = new Intent();
                switch (item.getItemId()){
                    case R.id.action_home:
                        intent.setClass(PageForAdd.this,MainActivity.class);
                        startActivity(intent);
                        db.close();
                        finish();
                        break;
//                    case R.id.menu_add:
//                        intent.setClass(PageForAdd.this,PageForAdd.class);
//                        startActivity(intent);
//                        db.close();
//                        finish();
//                        break;
                    case R.id.menu_show:
                        intent.setClass(PageForAdd.this,PageForShow.class);
                        startActivity(intent);
                        db.close();
                        finish();
                        break;
                    case R.id.menu_showInfo:
                        intent.setClass(PageForAdd.this,PageForShowInfo.class);
                        startActivity(intent);
                        db.close();
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
