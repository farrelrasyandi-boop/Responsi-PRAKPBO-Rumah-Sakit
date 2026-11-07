/**
 * Subclass 1: Dokter.
 * Mengimplementasikan DUA interface sekaligus (Multiple Interface).
 */
public class Dokter extends TenagaMedis implements LayananBerbayar, Terjadwal {

    private static final int BIAYA_STAMINA = 20;

    private double tarifKonsultasi;
    private double totalPendapatan;

    // Constructor #1 -> Super Chaining: super(...)
    public Dokter(String idPegawai, String nama, String spesialisasi, double tarifKonsultasi) {
        super(idPegawai, nama, spesialisasi);
        this.tarifKonsultasi = tarifKonsultasi;
        this.totalPendapatan = 0;
    }

    // Constructor #2 (overload) -> memakai this(...) lalu berantai ke super(...)
    public Dokter(String idPegawai, String nama) {
        this(idPegawai, nama, "Umum", 50000);
    }

    // ===== Overriding + Logika State Percabangan =====
    @Override
    public boolean tanganiPasien(String namaPasien) {
        if (hitungSisaKapasitas() <= 0) {
            System.out.println("  [Dokter] " + getNama() + " menolak " + namaPasien
                    + ": batas pasien harian tercapai.");
            return false;
        } else if (getStamina() < BIAYA_STAMINA) {
            System.out.println("  [Dokter] " + getNama() + " terlalu lelah untuk menangani "
                    + namaPasien + " (stamina " + getStamina() + "). Perlu istirahat.");
            return false;
        } else {
            kurangiStamina(BIAYA_STAMINA);           // ubah state
            catatPasien();                            // ubah state
            totalPendapatan += tarifKonsultasi;       // ubah state
            System.out.println("  [Dokter] " + getNama() + " memeriksa " + namaPasien
                    + ". Sisa stamina: " + getStamina());
            return true;
        }
    }

    // ===== Overriding + super.namaMethod() =====
    @Override
    public String tampilkanInfo() {
        return "DOKTER  -> " + super.tampilkanInfo()
                + " | Tarif: Rp" + (long) tarifKonsultasi;
    }

    // ===== Implementasi interface LayananBerbayar =====
    @Override
    public double hitungTarif() {
        return totalPendapatan;
    }

    @Override
    public String cetakTagihan() {
        return "Tagihan dr. " + getNama() + ": " + getJumlahPasien()
                + " pasien x Rp" + (long) tarifKonsultasi + " = Rp" + (long) totalPendapatan;
    }

    // ===== Implementasi interface Terjadwal =====
    @Override
    public String lihatJadwal() {
        return "dr. " + getNama() + " praktik Senin-Jumat 08.00-14.00";
    }

    @Override
    public void istirahat() {
        pulihkanStamina(30);
        System.out.println("  [Dokter] " + getNama() + " istirahat. Stamina kini " + getStamina());
    }

    // ===== Getter & Setter =====
    public double getTarifKonsultasi() { return tarifKonsultasi; }
    public void setTarifKonsultasi(double tarifKonsultasi) { this.tarifKonsultasi = tarifKonsultasi; }

    public double getTotalPendapatan() { return totalPendapatan; }
    public void setTotalPendapatan(double totalPendapatan) { this.totalPendapatan = totalPendapatan; }
}
