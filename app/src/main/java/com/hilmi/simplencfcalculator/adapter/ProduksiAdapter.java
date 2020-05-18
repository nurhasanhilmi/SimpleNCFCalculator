package com.hilmi.simplencfcalculator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hilmi.simplencfcalculator.R;

import java.util.ArrayList;

public class ProduksiAdapter extends RecyclerView.Adapter<ProduksiAdapter.ProduksiViewHolder> {

    private ArrayList<Double> listProduksiMinyak = new ArrayList<>();

    public ArrayList<Double> getListProduksiMinyak() {
        return listProduksiMinyak;
    }

    public void addProduksiMinyak(Double jumlahProduksi) {
        listProduksiMinyak.add(jumlahProduksi);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProduksiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produksi, parent, false);
        return new ProduksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProduksiViewHolder holder, final int position) {
        Double jumlahProduksi = listProduksiMinyak.get(position);

        holder.tvTahunProduksi.setText(String.valueOf(position+1));
        holder.tvJumlahProduksi.setText(String.valueOf(jumlahProduksi));
        holder.buttonHapusProduksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listProduksiMinyak.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProduksiMinyak.size();
    }

    public class ProduksiViewHolder extends RecyclerView.ViewHolder {

        TextView tvTahunProduksi;
        TextView tvJumlahProduksi;
        Button buttonHapusProduksi;

        public ProduksiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTahunProduksi = itemView.findViewById(R.id.tv_tahun_produksi);
            tvJumlahProduksi = itemView.findViewById(R.id.tv_jumlah_produksi);
            buttonHapusProduksi = itemView.findViewById(R.id.button_hapus_produksi);
        }
    }
}
