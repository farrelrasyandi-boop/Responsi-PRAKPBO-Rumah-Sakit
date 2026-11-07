public class Main {
    private static void judul(String teks) {
        System.out.println("\n=== " + teks + " ===");
    }
    public static void main(String[] args) {

        judul("1. State & Behavior");
        Dokter dokter = new Dokter("DR-001", "Andi", "Penyakit Dalam", 100000);
        System.out.println(dokter.tampilkanInfo());
        dokter.kurangiStamina(5);
        System.out.println("Setelah kurangiStamina(5): stamina = " + dokter.getStamina());

        judul("2. Immutability");
        System.out.println("Konstanta global STAMINA_MAKS = " + TenagaMedis.STAMINA_MAKS);
        System.out.println("Konstanta global BATAS_PASIEN_HARIAN = " + TenagaMedis.BATAS_PASIEN_HARIAN);
        System.out.println("ID pegawai (private final, tanpa setter) = " + dokter.getIdPegawai());

        judul("3. Enkapsulasi & Overloading Constructor");
        dokter.setNama("Andi Pratama");                       // setter
        System.out.println("Nama lewat getter/setter: " + dokter.getNama());
        Perawat perawatDefault = new Perawat("PR-001", "Sari");                       // ctor 2 argumen
        Perawat perawatLengkap = new Perawat("PR-002", "Budi", "Bedah", "Malam");     // ctor 4 argumen
        System.out.println(perawatDefault.tampilkanInfo());
        System.out.println(perawatLengkap.tampilkanInfo());

        judul("4. Inheritance (TenagaMedis -> Dokter & Perawat)");
        TenagaMedis[] staf = { dokter, perawatDefault, perawatLengkap };
        for (TenagaMedis t : staf) {
            System.out.println(t.getNama() + " adalah " + t.getClass().getSuperclass().getSimpleName()
                    + " -> " + t.getClass().getSimpleName());
        }

        judul("5. Super Chaining (super(...) dan super.tampilkanInfo())");
        System.out.println("Dokter.tampilkanInfo() memanggil super.tampilkanInfo():");
        System.out.println(dokter.tampilkanInfo());

        judul("6. Method Overriding (@Override)");
        System.out.println("tanganiPasien() & tampilkanInfo() ditimpa di Dokter & Perawat:");
        for (TenagaMedis t : staf) {
            System.out.println(t.tampilkanInfo());
        }

        judul("7 & 8. Abstract Method + Logika if/else State");
        Dokter dokterMuda = new Dokter("DR-002", "Rina");
        for (int i = 1; i <= 6; i++) {
            dokterMuda.tanganiPasien("Pasien-" + i);
        }
        dokterMuda.istirahat();
        dokterMuda.tanganiPasien("Pasien-7");
        perawatLengkap.tanganiPasien("Pasien-A");

        judul("9. Polimorfisme Interface");
        LayananBerbayar layanan = new Dokter("DR-003", "Citra", "Anak", 75000);
        Terjadwal jadwal = new Perawat("PR-003", "Dewi", "IGD", "Siang");
        ((Dokter) layanan).tanganiPasien("Pasien-X");
        ((Dokter) layanan).tanganiPasien("Pasien-Y");
        System.out.println(layanan.cetakTagihan());
        System.out.println("Total tarif: Rp" + (long) layanan.hitungTarif());
        System.out.println(jadwal.lihatJadwal());
        jadwal.istirahat();

        judul("10. Multiple Interface (Dokter implements LayananBerbayar, Terjadwal)");
        System.out.println("dokterMuda instanceof LayananBerbayar: " + (dokterMuda instanceof LayananBerbayar));
        System.out.println("dokterMuda instanceof Terjadwal      : " + (dokterMuda instanceof Terjadwal));
        System.out.println(dokterMuda.cetakTagihan());
        System.out.println(dokterMuda.lihatJadwal());
    }
}
