/**
 * Subclass 2: Perawat. Hanya mengimplementasikan satu interface (Terjadwal).
 */
public class Perawat extends TenagaMedis implements Terjadwal {

    private String shift;          // "Pagi", "Siang", atau "Malam"
    private int jumlahInjeksi;

    // Constructor #1 -> super(...)
    public Perawat(String idPegawai, String nama, String spesialisasi, String shift) {
        super(idPegawai, nama, spesialisasi);
        this.shift = shift;
        this.jumlahInjeksi = 0;
    }

    // Constructor #2 (overload)
    public Perawat(String idPegawai, String nama) {
        this(idPegawai, nama, "Umum", "Pagi");
    }

    // ===== Overriding + Logika State Percabangan =====
    @Override
    public boolean tanganiPasien(String namaPasien) {
        int biaya = shift.equals("Malam") ? 15 : 10;   // shift malam lebih melelahkan

        if (hitungSisaKapasitas() <= 0) {
            System.out.println("  [Perawat] " + getNama() + " tidak bisa menambah pasien "
                    + namaPasien + ": kapasitas penuh.");
            return false;
        } else if (getStamina() < biaya) {
            System.out.println("  [Perawat] " + getNama() + " kehabisan stamina (" + getStamina()
                    + "), " + namaPasien + " menunggu.");
            return false;
        } else {
            kurangiStamina(biaya);
            catatPasien();
            jumlahInjeksi++;
            System.out.println("  [Perawat] " + getNama() + " (shift " + shift + ") merawat "
                    + namaPasien + ". Sisa stamina: " + getStamina());
            return true;
        }
    }

    // ===== Overriding + super.namaMethod() =====
    @Override
    public String tampilkanInfo() {
        return "PERAWAT -> " + super.tampilkanInfo()
                + " | Shift: " + shift + " | Injeksi: " + jumlahInjeksi;
    }

    // ===== Implementasi interface Terjadwal =====
    @Override
    public String lihatJadwal() {
        return "Perawat " + getNama() + " bertugas shift " + shift;
    }

    @Override
    public void istirahat() {
        pulihkanStamina(40);
        System.out.println("  [Perawat] " + getNama() + " istirahat. Stamina kini " + getStamina());
    }

    // ===== Getter & Setter =====
    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    public int getJumlahInjeksi() { return jumlahInjeksi; }
    public void setJumlahInjeksi(int jumlahInjeksi) { this.jumlahInjeksi = jumlahInjeksi; }
}
