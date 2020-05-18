package com.hilmi.simplencfcalculator.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.hilmi.simplencfcalculator.R;
import com.hilmi.simplencfcalculator.model.NetCashFlow;

public class OpexActivity extends AppCompatActivity {

    private AutoCompleteTextView selectBasisOpex;
    private TextInputLayout inputBiayaOperasi;
    private Button buttonLanjut;

    private ArrayAdapter<String> arrayAdapter;

    private NetCashFlow netCashFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opex);

        netCashFlow = getIntent().getParcelableExtra("NCF_DATA");

        final String[] basisOpex = getResources().getStringArray(R.array.basis_opex);
        final String[] satuanOpex = getResources().getStringArray(R.array.satuan_opex);

        selectBasisOpex = findViewById(R.id.select_basis_opex);
        inputBiayaOperasi = findViewById(R.id.input_biaya_operasi);
        buttonLanjut = findViewById(R.id.button_lanjut3);

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item_choices, basisOpex);

        selectBasisOpex.setAdapter(arrayAdapter);
        selectBasisOpex.setInputType(InputType.TYPE_NULL);

        selectBasisOpex.setText(basisOpex[0], false);
        inputBiayaOperasi.setHint(satuanOpex[0]);

        selectBasisOpex.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inputBiayaOperasi.setHint(satuanOpex[position]);
            }
        });

        buttonLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String basisPerhitunganOpex = selectBasisOpex.getText().toString();
                String biayaOperasi = inputBiayaOperasi.getEditText().getText().toString().trim();

                if (biayaOperasi.isEmpty()){
                    inputBiayaOperasi.getEditText().setError("Input tidak boleh kosong");
                }
                else {
                    netCashFlow.setBasisPerhitunganOpex(basisPerhitunganOpex);
                    netCashFlow.setBiayaOperasi(Double.parseDouble(biayaOperasi));
                    Intent intent = new Intent(OpexActivity.this, DepresiasiActivity.class);
                    intent.putExtra("NCF_DATA", netCashFlow);
                    startActivity(intent);
                }
            }
        });
    }
}
