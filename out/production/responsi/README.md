# Sistem Manajemen Tenaga Medis Rumah Sakit

Responsi Akhir Praktikum Pemrograman Berorientasi Objek
Jurusan Informatika, Fakultas Teknik, Universitas Jenderal Soedirman

## 1. Identitas

| Keterangan | Isi |
|---|---|
| Nama | Muhammad Farrel Rasyandi |
| NIM | H1D025137 |
| Digit terakhir NIM | 7 |
| Tema | **Rumah Sakit** |

## 2. Deskripsi Singkat

Sistem mensimulasikan tenaga medis rumah sakit (dokter dan perawat) yang menangani pasien.
Setiap tenaga medis punya stamina dan batas pasien harian; keputusan menerima atau menolak pasien
bergantung pada kondisi (state) tersebut.

| File | Peran |
|---|---|
| `LayananBerbayar.java` | Interface: tarif dan tagihan |
| `Terjadwal.java` | Interface: jadwal dan istirahat |
| `TenagaMedis.java` | Abstract superclass |
| `Dokter.java` | Subclass, implements `LayananBerbayar` dan `Terjadwal` |
| `Perawat.java` | Subclass, implements `Terjadwal` |
| `Main.java` | Pengujian 10 persyaratan |

## 3. Pemetaan Persyaratan Teknis

| No | Konsep | Lokasi dalam kode |
|---|---|---|
| 1 | State & Behavior | `TenagaMedis`: atribut `stamina`, `jumlahPasien`; void `kurangiStamina()`; fungsi `hitungSisaKapasitas()` |
| 2 | Immutability | `STAMINA_MAKS`, `BATAS_PASIEN_HARIAN` (`public static final`); `idPegawai` (`private final`) |
| 3 | Enkapsulasi & Overloading | Semua atribut `private` + getter/setter, `this`, dua constructor di setiap class |
| 4 | Inheritance | `Dokter` dan `Perawat` `extends TenagaMedis`; `Main` terpisah |
| 5 | Super Chaining | `super(...)` di constructor subclass; `super.tampilkanInfo()` |
| 6 | Overriding | `@Override` pada `tanganiPasien()` dan `tampilkanInfo()` |
| 7 | Abstract Class | `TenagaMedis` abstract dengan `abstract boolean tanganiPasien(String)` |
| 8 | Logika State | `if/else` di `tanganiPasien()` membaca stamina dan kapasitas, lalu mengubahnya |
| 9 | Polimorfisme Interface | `LayananBerbayar layanan = new Dokter(...)`, `Terjadwal jadwal = new Perawat(...)` |
| 10 | Multiple Interface | `Dokter implements LayananBerbayar, Terjadwal` |

## 4. Cara Compile dan Run (Terminal)

Prasyarat: JDK 11 atau lebih baru.

```bash
git clone <URL-REPOSITORI-ANDA>
cd RumahSakitPBO

# Compile
javac -d out src/*.java

# Run
java -cp out Main
```

## 5. Screenshot Luaran Program

> Jalankan program, ambil screenshot terminal, simpan di folder `screenshots/`, lalu ganti baris di bawah.

![Luaran program](screenshots/output.png)

Contoh teks luaran:

```

=== 1. State & Behavior ===
DOKTER  -> [DR-001] Andi | Spesialisasi: Penyakit Dalam | Stamina: 100/100 | Pasien: 0/8 | Tarif: Rp100000
Setelah kurangiStamina(5): stamina = 95

=== 2. Immutability ===
Konstanta global STAMINA_MAKS = 100
Konstanta global BATAS_PASIEN_HARIAN = 8
ID pegawai (private final, tanpa setter) = DR-001

=== 3. Enkapsulasi & Overloading Constructor ===
Nama lewat getter/setter: Andi Pratama
PERAWAT -> [PR-001] Sari | Spesialisasi: Umum | Stamina: 100/100 | Pasien: 0/8 | Shift: Pagi | Injeksi: 0
PERAWAT -> [PR-002] Budi | Spesialisasi: Bedah | Stamina: 100/100 | Pasien: 0/8 | Shift: Malam | Injeksi: 0

=== 4. Inheritance (TenagaMedis -> Dokter & Perawat) ===
Andi Pratama adalah TenagaMedis -> Dokter
Sari adalah TenagaMedis -> Perawat
Budi adalah TenagaMedis -> Perawat

=== 5. Super Chaining (super(...) dan super.tampilkanInfo()) ===
Dokter.tampilkanInfo() memanggil super.tampilkanInfo():
DOKTER  -> [DR-001] Andi Pratama | Spesialisasi: Penyakit Dalam | Stamina: 95/100 | Pasien: 0/8 | Tarif: Rp100000

=== 6. Method Overriding (@Override) ===
tanganiPasien() & tampilkanInfo() ditimpa di Dokter & Perawat:
DOKTER  -> [DR-001] Andi Pratama | Spesialisasi: Penyakit Dalam | Stamina: 95/100 | Pasien: 0/8 | Tarif: Rp100000
PERAWAT -> [PR-001] Sari | Spesialisasi: Umum | Stamina: 100/100 | Pasien: 0/8 | Shift: Pagi | Injeksi: 0
PERAWAT -> [PR-002] Budi | Spesialisasi: Bedah | Stamina: 100/100 | Pasien: 0/8 | Shift: Malam | Injeksi: 0

=== 7 & 8. Abstract Method + Logika if/else State ===
  [Dokter] Rina memeriksa Pasien-1. Sisa stamina: 80
  [Dokter] Rina memeriksa Pasien-2. Sisa stamina: 60
  [Dokter] Rina memeriksa Pasien-3. Sisa stamina: 40
  [Dokter] Rina memeriksa Pasien-4. Sisa stamina: 20
  [Dokter] Rina memeriksa Pasien-5. Sisa stamina: 0
  [Dokter] Rina terlalu lelah untuk menangani Pasien-6 (stamina 0). Perlu istirahat.
  [Dokter] Rina istirahat. Stamina kini 30
  [Dokter] Rina memeriksa Pasien-7. Sisa stamina: 10
  [Perawat] Budi (shift Malam) merawat Pasien-A. Sisa stamina: 85

=== 9. Polimorfisme Interface ===
  [Dokter] Citra memeriksa Pasien-X. Sisa stamina: 80
  [Dokter] Citra memeriksa Pasien-Y. Sisa stamina: 60
Tagihan dr. Citra: 2 pasien x Rp75000 = Rp150000
Total tarif: Rp150000
Perawat Dewi bertugas shift Siang
  [Perawat] Dewi istirahat. Stamina kini 100

=== 10. Multiple Interface (Dokter implements LayananBerbayar, Terjadwal) ===
dokterMuda instanceof LayananBerbayar: true
dokterMuda instanceof Terjadwal      : true
Tagihan dr. Rina: 6 pasien x Rp50000 = Rp300000
dr. Rina praktik Senin-Jumat 08.00-14.00
```
