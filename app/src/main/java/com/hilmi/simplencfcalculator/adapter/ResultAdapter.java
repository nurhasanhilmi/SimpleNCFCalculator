package com.hilmi.simplencfcalculator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hilmi.simplencfcalculator.R;
import com.hilmi.simplencfcalculator.model.NcfCalculator;

import java.text.DecimalFormat;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {
    private NcfCalculator calculator;

    public ResultAdapter(NcfCalculator calculator) {
        this.calculator = calculator;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        String tahun = String.valueOf(position+1);
        String produksi = decimalFormat.format(calculator.getNetCashFlow().getProduksiMinyak().get(position));
        String income = decimalFormat.format(calculator.getListIncome().get(position));
        String opex = decimalFormat.format(calculator.getListOpex().get(position));
        String depresiasi = decimalFormat.format(calculator.getListDepresiation().get(position));
        String taxable = decimalFormat.format(calculator.getListTaxableIncome().get(position));
        String tax = decimalFormat.format(calculator.getListTax().get(position));
        String undiscNcf = decimalFormat.format(calculator.getListUndiscountedNcf().get(position));

        holder.tvTahun.setText(tahun);
        holder.tvProduksi.setText(produksi);
        holder.tvIncome.setText(income);
        holder.tvOpex.setText(opex);
        holder.tvDepresiasi.setText(depresiasi);
        holder.tvTaxable.setText(taxable);
        holder.tvTax.setText(tax);
        holder.tvUndiscNcf.setText(undiscNcf);
    }

    @Override
    public int getItemCount() {
        return calculator.getJumlahTahun();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        TextView tvTahun, tvProduksi, tvIncome, tvOpex, tvDepresiasi, tvTaxable, tvTax, tvUndiscNcf;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTahun = itemView.findViewById(R.id.tv_tahun);
            tvProduksi = itemView.findViewById(R.id.tv_produksi);
            tvIncome = itemView.findViewById(R.id.tv_income);
            tvOpex = itemView.findViewById(R.id.tv_opex);
            tvDepresiasi = itemView.findViewById(R.id.tv_depresiasi);
            tvTaxable = itemView.findViewById(R.id.tv_taxable);
            tvTax = itemView.findViewById(R.id.tv_tax);
            tvUndiscNcf = itemView.findViewById(R.id.tv_undisc_ncf);
        }
    }
}
