package com.hilmi.simplencfcalculator.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.hilmi.simplencfcalculator.R;
import com.hilmi.simplencfcalculator.model.NetCashFlow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout inputCadanganMinyak;
    private TextInputLayout inputHargaMinyak;
    private TextInputLayout inputInvestasiKapital;
    private TextInputLayout inputInvestasiNonKapital;
    private TextInputLayout inputBesaranPajak;
    private Button buttonLanjut;

    private NetCashFlow netCashFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        netCashFlow = new NetCashFlow();
        initializeView();
        buttonLanjut.setOnClickListener(this);
    }

    private void initializeView() {
        inputCadanganMinyak = findViewById(R.id.input_cadangan_minyak);
        inputHargaMinyak = findViewById(R.id.input_harga_minyak);
        inputInvestasiKapital = findViewById(R.id.input_investasi_kapital);
        inputInvestasiNonKapital = findViewById(R.id.input_investasi_nonkapital);
        inputBesaranPajak = findViewById(R.id.input_besaran_pajak);
        buttonLanjut = findViewById(R.id.button_lanjut1);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonLanjut) {
            if (readInputValue()) {
                Intent intent = new Intent(this, ProduksiActivity.class);
                intent.putExtra("NCF_DATA", netCashFlow);
                startActivity(intent);
            }
        }
    }

    private boolean readInputValue() {
        String cadanganMinyak = inputCadanganMinyak.getEditText().getText().toString().trim();
        String hargaMinyak = inputHargaMinyak.getEditText().getText().toString().trim();
        String investasiKapital = inputInvestasiKapital.getEditText().getText().toString().trim();
        String investasiNonKapital = inputInvestasiNonKapital.getEditText().getText().toString().trim();
        String besaranPajak = inputBesaranPajak.getEditText().getText().toString().trim();

        boolean isInputNotEmpty = true;

        if (cadanganMinyak.isEmpty()) {
            isInputNotEmpty = false;
            inputCadanganMinyak.getEditText().setError("Input tidak boleh kosong");
        }
        if (hargaMinyak.isEmpty()) {
            isInputNotEmpty = false;
            inputHargaMinyak.getEditText().setError("Input tidak boleh kosong");
        }
        if (investasiKapital.isEmpty()) {
            isInputNotEmpty = false;
            inputInvestasiKapital.getEditText().setError("Input tidak boleh kosong");
        }
        if (investasiNonKapital.isEmpty()) {
            isInputNotEmpty = false;
            inputInvestasiNonKapital.getEditText().setError("Input tidak boleh kosong");
        }
        if (besaranPajak.isEmpty()) {
            isInputNotEmpty = false;
            inputBesaranPajak.getEditText().setError("Input tidak boleh kosong");
        }

        if (isInputNotEmpty) {
            netCashFlow.setCadanganMinyak(Double.parseDouble(cadanganMinyak));
            netCashFlow.setHargaMinyak(Double.parseDouble(hargaMinyak));
            netCashFlow.setInvestasiKapital(Double.parseDouble(investasiKapital));
            netCashFlow.setInvestasiNonKapital(Double.parseDouble(investasiNonKapital));
            netCashFlow.setBesaranPajak(Double.parseDouble(besaranPajak));
        }

        return isInputNotEmpty;
    }
}
