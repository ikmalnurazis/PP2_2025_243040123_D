package id.ac.unpas.Modul08.Model;

public class PersegiPanjangModel {
    private double panjang;
    private double lebar;

    public PersegiPanjangModel(double panjang, double lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    public double getLuas() {
        return panjang * lebar;
    }

    public double getKeliling() {
        return 2 * (panjang + lebar);
    }
}
