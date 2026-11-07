/**
 * Interface 1: kontrak untuk tenaga medis yang menghasilkan tagihan.
 */
public interface LayananBerbayar {
    double hitungTarif();      // total tarif yang sudah dihasilkan
    String cetakTagihan();     // ringkasan tagihan dalam bentuk teks
}
