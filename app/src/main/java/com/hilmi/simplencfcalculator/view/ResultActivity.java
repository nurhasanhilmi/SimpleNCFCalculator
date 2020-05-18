package com.hilmi.simplencfcalculator.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.hilmi.simplencfcalculator.R;
import com.hilmi.simplencfcalculator.adapter.ResultAdapter;
import com.hilmi.simplencfcalculator.model.NcfCalculator;
import com.hilmi.simplencfcalculator.model.NetCashFlow;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    private TextView tvTotalUndiscNcf;
    private TextView tvCapitalInvest;
    private TextView tvNonCapitalInvest;
    private TextView tvTotalInvest;
    private TextView tvNetCashFlow;

    private RecyclerView rvResult;
    private ResultAdapter resultAdapter;

    private NetCashFlow netCashFlow;
    private NcfCalculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initializeView();

        netCashFlow = getIntent().getParcelableExtra("NCF_DATA");
        calculator = new NcfCalculator(netCashFlow);

        resultAdapter = new ResultAdapter(calculator);
        rvResult.setAdapter(resultAdapter);

        attachResult();
    }

    private void initializeView() {
        tvTotalUndiscNcf = findViewById(R.id.tv_total_undisc_ncf);
        tvCapitalInvest = findViewById(R.id.tv_capital_invest);
        tvNonCapitalInvest = findViewById(R.id.tv_non_capital_invest);
        tvTotalInvest = findViewById(R.id.tv_total_invest);
        tvNetCashFlow = findViewById(R.id.tv_total_ncf);
        rvResult = findViewById(R.id.rv_result);
        rvResult.setLayoutManager(new LinearLayoutManager(this));
    }

    private void attachResult() {
        DecimalFormat df = new DecimalFormat("#.00");

        Double totalUndiscNcf = 0.0;
        for (int i=0; i<calculator.getJumlahTahun(); i++) {
            totalUndiscNcf += calculator.getListUndiscountedNcf().get(i);
        }

        Double capitalInvest = calculator.getNetCashFlow().getInvestasiKapital();
        Double nonCapitalInvest = calculator.getNetCashFlow().getInvestasiNonKapital();
        Double totalInvest = capitalInvest + nonCapitalInvest;

        Double totalNcf = totalUndiscNcf - totalInvest;

        tvTotalUndiscNcf.setText("$ " + df.format(totalUndiscNcf) + " M");
        tvCapitalInvest.setText("$ " + df.format(capitalInvest) + " M");
        tvNonCapitalInvest.setText("$ " + df.format(nonCapitalInvest) + " M");
        tvTotalInvest.setText("$ " + df.format(totalInvest) + " M");
        tvNetCashFlow.setText("$ " + df.format(totalNcf) + " M");
    }
}
