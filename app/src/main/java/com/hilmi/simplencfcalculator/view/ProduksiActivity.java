package com.hilmi.simplencfcalculator.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hilmi.simplencfcalculator.adapter.ProduksiAdapter;
import com.hilmi.simplencfcalculator.R;
import com.hilmi.simplencfcalculator.model.NetCashFlow;

public class ProduksiActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout inputProduksiMinyak;
    private Button buttonTambah;
    private Button buttonLanjut;
    private RecyclerView rvProduksi;

    private NetCashFlow netCashFlow;
    private ProduksiAdapter produksiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produksi);

        netCashFlow = getIntent().getParcelableExtra("NCF_DATA");
        produksiAdapter = new ProduksiAdapter();

        initializeView();
    }

    private void initializeView() {
        inputProduksiMinyak = findViewById(R.id.input_produksi_minyak);

        buttonTambah = findViewById(R.id.button_tambah);
        buttonTambah.setOnClickListener(this);

        buttonLanjut = findViewById(R.id.button_lanjut2);
        buttonLanjut.setOnClickListener(this);

        rvProduksi = findViewById(R.id.rv_produksi);
        rvProduksi.setLayoutManager(new LinearLayoutManager(this));
        rvProduksi.setAdapter(produksiAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonTambah) {
            String jumlahProduksi = inputProduksiMinyak.getEditText().getText().toString().trim();

            if (jumlahProduksi.isEmpty()) {
                inputProduksiMinyak.getEditText().setError("Input tidak boleh kosong");
            }
            else {
                produksiAdapter.addProduksiMinyak(Double.parseDouble(jumlahProduksi));
                inputProduksiMinyak.getEditText().setText("");
                rvProduksi.smoothScrollToPosition(produksiAdapter.getItemCount()-1);
            }
        }
        if (v == buttonLanjut) {
            if (produksiAdapter.getItemCount() > 0) {
                netCashFlow.setProduksiMinyak(produksiAdapter.getListProduksiMinyak());
                Intent intent = new Intent(this, OpexActivity.class);
                intent.putExtra("NCF_DATA", netCashFlow);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Produksi Minyak Kosong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
