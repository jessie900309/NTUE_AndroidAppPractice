package com.example.homework5_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TextWatcher {

    double[] energyRate={3.1,4.4,13.2,9.7,5.1,3.7};
    Spinner SportsList;
    EditText InputKG,InputHR;
    TextView ShowRate,ShowResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SportsList = (Spinner) findViewById(R.id.spinner);
        SportsList.setOnItemSelectedListener(this);
        InputKG = (EditText) findViewById(R.id.inputKG);
        InputKG.addTextChangedListener(this);
        InputHR = (EditText) findViewById(R.id.inputHR);
        InputHR.addTextChangedListener(this);
        ShowRate = (TextView) findViewById(R.id.selectSportsEnergyRate);
        ShowResult = (TextView) findViewById(R.id.resultEnergy);
    }

    public void clac(){
        String w = InputKG.getText().toString();
        String h = InputHR.getText().toString();
        int pos = SportsList.getSelectedItemPosition();
        double weight,hour,energy,kcal;
        energy = energyRate[pos];
        try {
            weight = Double.parseDouble(w);
        }catch (Exception e){
            weight = 1;
        }
        try {
            hour = Double.parseDouble(h);
        }catch (Exception e){
            hour = 1;
        }
        kcal = Math.round(weight*hour*energy*100.0)/100.0;
        ShowResult.setText(""+kcal);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        //即時顯示運動能量及重新計算
        ShowRate.setText(String.valueOf(energyRate[pos]));
        clac();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //此處不會用到OuO
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //此處不會用到OuO
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //此處不會用到OuO
    }

    @Override
    public void afterTextChanged(Editable editable) {
        clac();
    }
}