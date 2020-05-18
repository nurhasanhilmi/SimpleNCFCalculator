package com.hilmi.simplencfcalculator.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.hilmi.simplencfcalculator.R;
import com.hilmi.simplencfcalculator.model.NetCashFlow;

public class DepresiasiActivity extends AppCompatActivity {

    private AutoCompleteTextView selectMetodeDepresiasi;
    private Button buttonHitung;

    private ArrayAdapter<String> arrayAdapter;

    private NetCashFlow netCashFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depresiasi);

        netCashFlow = getIntent().getParcelableExtra("NCF_DATA");

        final String [] metodeDepresiasi = getResources().getStringArray(R.array.metode_depresiasi);

        selectMetodeDepresiasi = findViewById(R.id.select_metode_depresiasi);
        buttonHitung = findViewById(R.id.button_hitung);

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item_choices, metodeDepresiasi);

        selectMetodeDepresiasi.setAdapter(arrayAdapter);
        selectMetodeDepresiasi.setInputType(InputType.TYPE_NULL);
        selectMetodeDepresiasi.setText(metodeDepresiasi[0], false);

        buttonHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedMetodeDepresiasi = selectMetodeDepresiasi.getText().toString();
                netCashFlow.setMetodePerhitunganDepresiasi(selectedMetodeDepresiasi);
                Intent intent = new Intent(DepresiasiActivity.this, ResultActivity.class);
                intent.putExtra("NCF_DATA", netCashFlow);
                startActivity(intent);

                Log.d("DepresiasiActivity:", "onClick: " + netCashFlow.toString());
            }
        });
    }
}
