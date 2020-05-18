package com.hilmi.simplencfcalculator.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class NetCashFlow implements Parcelable {

    private ArrayList<Double> produksiMinyak;
    private Double cadanganMinyak;
    private Double hargaMinyak;
    private Double investasiKapital;
    private Double investasiNonKapital;
    private Double besaranPajak;
    private Double biayaOperasi;
    private String basisPerhitunganOpex;
    private String metodePerhitunganDepresiasi;

    // constructor
    public NetCashFlow() {
    }

    // setter and getter
    public ArrayList<Double> getProduksiMinyak() {
        return produksiMinyak;
    }

    public void setProduksiMinyak(ArrayList<Double> produksiMinyak) {
        this.produksiMinyak = produksiMinyak;
    }

    public Double getCadanganMinyak() {
        return cadanganMinyak;
    }

    public void setCadanganMinyak(Double cadanganMinyak) {
        this.cadanganMinyak = cadanganMinyak;
    }

    public Double getHargaMinyak() {
        return hargaMinyak;
    }

    public void setHargaMinyak(Double hargaMinyak) {
        this.hargaMinyak = hargaMinyak;
    }

    public Double getInvestasiKapital() {
        return investasiKapital;
    }

    public void setInvestasiKapital(Double investasiKapital) {
        this.investasiKapital = investasiKapital;
    }

    public Double getInvestasiNonKapital() {
        return investasiNonKapital;
    }

    public void setInvestasiNonKapital(Double investasiNonKapital) {
        this.investasiNonKapital = investasiNonKapital;
    }

    public Double getBesaranPajak() {
        return besaranPajak;
    }

    public void setBesaranPajak(Double besaranPajak) {
        this.besaranPajak = besaranPajak;
    }

    public Double getBiayaOperasi() {
        return biayaOperasi;
    }

    public void setBiayaOperasi(Double biayaOperasi) {
        this.biayaOperasi = biayaOperasi;
    }

    public String getBasisPerhitunganOpex() {
        return basisPerhitunganOpex;
    }

    public void setBasisPerhitunganOpex(String basisPerhitunganOpex) {
        this.basisPerhitunganOpex = basisPerhitunganOpex;
    }

    public String getMetodePerhitunganDepresiasi() {
        return metodePerhitunganDepresiasi;
    }

    public void setMetodePerhitunganDepresiasi(String metodePerhitunganDepresiasi) {
        this.metodePerhitunganDepresiasi = metodePerhitunganDepresiasi;
    }

    // implement parcelable method
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.produksiMinyak);
        dest.writeValue(this.cadanganMinyak);
        dest.writeValue(this.hargaMinyak);
        dest.writeValue(this.investasiKapital);
        dest.writeValue(this.investasiNonKapital);
        dest.writeValue(this.besaranPajak);
        dest.writeValue(this.biayaOperasi);
        dest.writeString(this.basisPerhitunganOpex);
        dest.writeString(this.metodePerhitunganDepresiasi);
    }

    protected NetCashFlow(Parcel in) {
        this.produksiMinyak = new ArrayList<>();
        in.readList(this.produksiMinyak, Double.class.getClassLoader());
        this.cadanganMinyak = (Double) in.readValue(Double.class.getClassLoader());
        this.hargaMinyak = (Double) in.readValue(Double.class.getClassLoader());
        this.investasiKapital = (Double) in.readValue(Double.class.getClassLoader());
        this.investasiNonKapital = (Double) in.readValue(Double.class.getClassLoader());
        this.besaranPajak = (Double) in.readValue(Double.class.getClassLoader());
        this.biayaOperasi = (Double) in.readValue(Double.class.getClassLoader());
        this.basisPerhitunganOpex = in.readString();
        this.metodePerhitunganDepresiasi = in.readString();
    }

    public static final Parcelable.Creator<NetCashFlow> CREATOR = new Parcelable.Creator<NetCashFlow>() {
        @Override
        public NetCashFlow createFromParcel(Parcel source) {
            return new NetCashFlow(source);
        }

        @Override
        public NetCashFlow[] newArray(int size) {
            return new NetCashFlow[size];
        }
    };

    @Override
    public String toString() {
        return "\nNetCashFlow{" +
                "\nproduksiMinyak=" + produksiMinyak.size() +
                ", \ncadanganMinyak=" + cadanganMinyak +
                ", \nhargaMinyak=" + hargaMinyak +
                ", \ninvestasiKapital=" + investasiKapital +
                ", \ninvestasiNonKapital=" + investasiNonKapital +
                ", \nbesaranPajak=" + besaranPajak +
                ", \nbiayaOperasi=" + biayaOperasi +
                ", \nbasisPerhitunganOpex='" + basisPerhitunganOpex + '\'' +
                ", \nmetodePerhitunganDepresiasi='" + metodePerhitunganDepresiasi + '\'' +
                "\n}";
    }
}
