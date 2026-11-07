/**
 * Superclass abstract untuk seluruh tenaga medis rumah sakit.
 * Memenuhi: State & Behavior, Immutability, Enkapsulasi, Overloading,
 * Abstract Class.
 */
public abstract class TenagaMedis {

    // ===== Immutability: konstanta global (public static final) =====
    public static final int STAMINA_MAKS = 100;
    public static final int BATAS_PASIEN_HARIAN = 8;

    // ===== Enkapsulasi: semua atribut private =====
    private final String idPegawai;   // Immutability: atribut instans kekal (private final)
    private String nama;
    private String spesialisasi;
    private int stamina;              // state yang berubah
    private int jumlahPasien;         // state yang berubah

    // ===== Overloaded constructor #1 (lengkap) =====
    public TenagaMedis(String idPegawai, String nama, String spesialisasi) {
        this.idPegawai = idPegawai;
        this.nama = nama;
        this.spesialisasi = spesialisasi;
        this.stamina = STAMINA_MAKS;
        this.jumlahPasien = 0;
    }

    // ===== Overloaded constructor #2 (spesialisasi default "Umum") =====
    public TenagaMedis(String idPegawai, String nama) {
        this(idPegawai, nama, "Umum");
    }

    // ===== Abstract method: wajib diimplementasikan subclass =====
    public abstract boolean tanganiPasien(String namaPasien);

    // ===== Method fungsi (return value) =====
    public String tampilkanInfo() {
        return "[" + idPegawai + "] " + nama + " | Spesialisasi: " + spesialisasi
                + " | Stamina: " + stamina + "/" + STAMINA_MAKS
                + " | Pasien: " + jumlahPasien + "/" + BATAS_PASIEN_HARIAN;
    }

    public int hitungSisaKapasitas() {
        return BATAS_PASIEN_HARIAN - jumlahPasien;
    }

    // ===== Method prosedur (void) =====
    public void kurangiStamina(int jumlah) {
        this.stamina = Math.max(0, this.stamina - jumlah);
    }

    public void pulihkanStamina(int jumlah) {
        this.stamina = Math.min(STAMINA_MAKS, this.stamina + jumlah);
    }

    public void catatPasien() {
        this.jumlahPasien++;
    }

    // ===== Getter & Setter =====
    // idPegawai sengaja TIDAK punya setter karena final (kekal).
    public String getIdPegawai() { return idPegawai; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getSpesialisasi() { return spesialisasi; }
    public void setSpesialisasi(String spesialisasi) { this.spesialisasi = spesialisasi; }

    public int getStamina() { return stamina; }
    public void setStamina(int stamina) {
        this.stamina = Math.max(0, Math.min(STAMINA_MAKS, stamina));
    }

    public int getJumlahPasien() { return jumlahPasien; }
    public void setJumlahPasien(int jumlahPasien) { this.jumlahPasien = Math.max(0, jumlahPasien); }
}
