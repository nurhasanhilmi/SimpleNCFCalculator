package com.hilmi.simplencfcalculator.model;

import java.util.ArrayList;

public class NcfCalculator {
    private NetCashFlow netCashFlow;
    private ArrayList<Double> listIncome;
    private ArrayList<Double> listOpex;
    private ArrayList<Double> listDepresiation;
    private ArrayList<Double> listTaxableIncome;
    private ArrayList<Double> listTax;
    private ArrayList<Double> listUndiscountedNcf;
    private int jumlahTahun;

    public NcfCalculator(NetCashFlow netCashFlow) {
        this.netCashFlow = netCashFlow;
        instantiateObject();
        calculate();
    }

    private void instantiateObject() {
        listIncome = new ArrayList<>();
        listOpex = new ArrayList<>();
        listDepresiation = new ArrayList<>();
        listTaxableIncome = new ArrayList<>();
        listTax = new ArrayList<>();
        listUndiscountedNcf = new ArrayList<>();
        jumlahTahun = netCashFlow.getProduksiMinyak().size();
    }

    private void calculate() {
        calculateIncome();
        calculateOpex();
        calculateDepresiation();
        calculateTaxableIncome();
        calculateTax();
        calculateUndiscounterNcf();
    }

    private void calculateIncome() { // produksi * harga minyak
        ArrayList<Double> produksi = netCashFlow.getProduksiMinyak();
        Double hargaMinyak = netCashFlow.getHargaMinyak();

        for (int i=0; i<jumlahTahun; i++) {
            listIncome.add(produksi.get(i) * hargaMinyak);
        }
    }

    private void calculateOpex() {
        ArrayList<Double> produksi = netCashFlow.getProduksiMinyak();
        Double biayaOperasi = netCashFlow.getBiayaOperasi();

        switch (netCashFlow.getBasisPerhitunganOpex()) {
            case "Perbarel Produksi Minyak":
                for (int i=0; i<jumlahTahun; i++) {
                    listOpex.add(produksi.get(i) * biayaOperasi);
                }
                break;
            case "Rata-rata Biaya Pertahun":
                for (int i=0; i<jumlahTahun; i++) {
                    listOpex.add(biayaOperasi);
                }
                break;
        }
    }

    private void calculateDepresiation() {
        int N = jumlahTahun;                            // Jumlah tahun depresiasi
        Double K = netCashFlow.getInvestasiKapital();   // Investasi Capital
        Double R = 1.0/N;                               // Depresiation Rate

        switch (netCashFlow.getMetodePerhitunganDepresiasi()) {
            case "Straight Line":
                for (int i=0; i<N; i++) {
                    listDepresiation.add(K * R);    // Di = K.R
                }
                break;
            case "Declining Balance":
                for (int i=0; i<N; i++) {
                    listDepresiation.add(K * R * Math.pow((1-R), i));   // Di = K.R.(1-R)^(i)
                }
                break;
            case "Double Declining Balance":
                for (int i=0; i<N; i++) {
                    listDepresiation.add(K * 2*R * Math.pow((1-(2*R)), i)); // Di = K.2R.(1-2R)^(i)
                }
                break;
            case "Unit of Production":
                ArrayList<Double> produksi = netCashFlow.getProduksiMinyak();
                Double cadanganMinyak = netCashFlow.getCadanganMinyak();

                for (int i=0; i<N; i++) {
                    listDepresiation.add(produksi.get(i) / cadanganMinyak * K); // Di = produksi / cadangan minyak * K
                }
                break;
            case "Sum of The Year":
                for (int i=0; i<N; i++) {
                    listDepresiation.add((K*2*(N-i)) / (N*(N+1)));    // Di = K.2(N-i) / N(N+1)
                }
                break;
        }
    }

    private void calculateTaxableIncome() {
        for (int i=0; i<jumlahTahun; i++) {
            // Taxable Inc = income - Di - opex
            listTaxableIncome.add(listIncome.get(i) - listDepresiation.get(i) - listOpex.get(i));
        }
    }

    private void calculateTax() {
        Double besaranPajak = netCashFlow.getBesaranPajak();

        for (int i=0; i<jumlahTahun; i++) {
            // Tax = besaran pajak (%) * taxable income
            listTax.add(besaranPajak/100.0 * listTaxableIncome.get(i));
        }
    }

    private void calculateUndiscounterNcf() {
        for (int i=0; i<jumlahTahun; i++) {
            // Undisc. NCF = income - opex - tax
            listUndiscountedNcf.add(listIncome.get(i) - listOpex.get(i) - listTax.get(i));
        }
    }

    // getter
    public NetCashFlow getNetCashFlow() {
        return netCashFlow;
    }

    public ArrayList<Double> getListIncome() {
        return listIncome;
    }

    public ArrayList<Double> getListOpex() {
        return listOpex;
    }

    public ArrayList<Double> getListDepresiation() {
        return listDepresiation;
    }

    public ArrayList<Double> getListTaxableIncome() {
        return listTaxableIncome;
    }

    public ArrayList<Double> getListTax() {
        return listTax;
    }

    public ArrayList<Double> getListUndiscountedNcf() {
        return listUndiscountedNcf;
    }

    public int getJumlahTahun() {
        return jumlahTahun;
    }
}
