/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siak;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.List;
import java.util.Scanner;
import MODEL.MAHASISWA;
import MODEL.MATKUL;
import SERVICE.ServiceJdbc;

/**
 *
 * @author HP
 */
public class SIAK {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("siak?serverTimezone=UTC");
        dataSource.setServerName("localhost");

        dataSource.setPortNumber(3306);

        ServiceJdbc service = new ServiceJdbc();
        service.setDataSource(dataSource);
        
        Scanner in = new Scanner(System.in);
        System.out.println("Selamat datang di Sistem Informasi Akademik");
        Boolean mainmenu = true;
        while (mainmenu) {
            System.out.println("\nMenu Utama");
            System.out.println("\n1. Pengelolaan Mahasiswa");
            System.out.println("2. Pengelolaan Mata Kuliah");
            System.out.println("3. Input Nilai Mahasiswa");
            System.out.print("\nMasukkan pilihan : ");
            String pilihanutama = in.nextLine();
            switch(Integer.parseInt(pilihanutama)) {
                case 1:
                    getMenuMahasiswa(service);
                    break;
                case 2:
                    getMenuMatkul(service);
                    break;
                case 3:
                    getMenuNilai();
                    break;
                default:
                    break;
            }
            
        }
    }
    
    public static void getMenuMahasiswa(ServiceJdbc service) {
        Boolean mhsmenu = true;
        Scanner in = new Scanner(System.in);
        while (mhsmenu) {
            System.out.println("\nMenu Pengelolaan Mahasiswa");
            System.out.println("\n1. Lihat Daftar Mahasiswa");
            System.out.println("2. Tambah Data Mahasiswa");
            System.out.println("3. Ubah Data Mahasiswa");
            System.out.println("4. Hapus Data Mahasiswa");
            System.out.print("\nMasukkan pilihan : ");
            String pilihanmhs = in.nextLine();
            switch (Integer.parseInt(pilihanmhs)) {
                case 1:
                    List<MAHASISWA> mhsR = service.getAllMAHASISWA();
                    if (mhsR.isEmpty()) {
                        System.out.println("\nBelum ada mahasiswa yang terdaftar!");
                        break;
                    }
                    System.out.println("NIM \t | Nama \t\t\t | Jurusan | Tgl_lahir");
                    System.out.println("=============================================================================================");
                    for (MAHASISWA mhs : mhsR) {
                        System.out.println(mhs.getNim() + "\t | " + mhs.getNama() + " \t | " + mhs.getJurusan() + " | "+ mhs.getTgl_lahir());
                    }
                    break;
                case 2:
                    System.out.print("NIM : ");
                    String npm = in.nextLine();
                    System.out.print("Nama : ");                    
                    String nama = in.nextLine();
                    System.out.print("Jurusan : ");                    
                    String Jurusan = in.nextLine();
                    System.out.print("Tgl_lahir : ");
                    String Tgl_lahir = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                        MAHASISWA mhs = new MAHASISWA();
                        mhs.setNim(Integer.parseInt(npm));
                        mhs.setNama(nama);
                        mhs.setTgllahir(Jurusan);
                        mhs.setAlamat(Tgl_lahir);
                        service.save(mhs);
                    }
                    break;
                case 3:
                    System.out.print("Masukkan NIM : ");
                    String nim_x = in.nextLine();
                    MAHASISWA mhs_x = service.getMAHASISWA(Integer.parseInt(nim_x));
                    if (mhs_x == null) {
                        System.out.println("Tidak ditemukan mahasiswa dengan NIM " + nim_x);
                        break;
                    }
                    System.out.print("Nama : ");                    
                    String nama_x = in.nextLine();
                    System.out.print("Nim : ");                    
                    String Nim_x = in.nextLine();
                    System.out.print("Jurusan : ");
                    String Jurusan_x = in.nextLine();
                    System.out.print("Tgl_lahir : ");
                    String Tgllahir_x = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah_x = in.nextLine();
                    if (tambah_x.toLowerCase().equals("y")) {
                        mhs_x.setNim(Integer.parseInt(nim_x));
                        mhs_x.setNama(nama_x);
                        mhs_x.setTgllahir(Jurusan_x);
                        service.update(mhs_x);
                    }
                    break;
                case 4:
                    System.out.print("Masukkan NIM : ");
                    String npm_d = in.nextLine();
                    MAHASISWA mhs_d = service.getMAHASISWA(Integer.parseInt(npm_d));
                    if (mhs_d == null) {
                        System.out.println("Tidak ditemukan mahasiswa dengan NIM "+npm_d);
                        break;
                    }
                    System.out.print("Hapus? (Y/N) : ");
                    String hapus = in.nextLine();
                    if (hapus.toLowerCase().equals("y")) {
                        service.delete(mhs_d);
                    }
                    break;
                default:
                    break;
            }

        }
    }
    
    public static void getMenuMatkul(ServiceJdbc service) {
        Scanner in = new Scanner(System.in);
        Boolean active = true;
        while (active) {
            System.out.println("\nMenu : \n");
            System.out.println("1. Lihat Daftar Matkul");
            System.out.println("2. Tambah Data Matkul");
            System.out.println("3. Ubah Data Matkul");
            System.out.println("4. Hapus Data Matkul");
            System.out.println("\n0. Kembali ke Menu");

            System.out.print("\nPilihan : ");
            String pilih = in.nextLine();
            switch (pilih) {
                case "1":
                    List<MATKUL> matkulR = service.getAllMatkul();
                    if (matkulR.isEmpty()) {
                        System.out.println("\nBelum ada mata kuliah yang terdaftar. Silakan tambahkan mata kuliah terlebih dahulu!");
                    } else {
                        System.out.println("\nKd_Matkul\t | Nama Matkul\t | SKS");
                        System.out.println("--------------------------------------------------------------------------------------");
                        for (MATKUL matkul : matkulR) {
                            System.out.println(matkul.getKd_mk()+ "\t\t | " + matkul.getNama_mk()+ "\t| " + matkul.getSks());
                        }
                    }
                    break;
                case "2":
                    System.out.print("Nama Matkul : ");
                    String nama_mk = in.nextLine();
                    System.out.print("SKS : ");
                    String sks = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                        MATKUL buku = new MATKUL();
                        buku.setNama_mk(nama_mk);
                        buku.setSks(Integer.parseInt(sks));
                        service.save(buku);
                    }
                    break;
                case "3":
                    System.out.print("Masukkan KODE Matkul : ");
                    String id_matkul_x = in.nextLine();
                    MATKUL matkul_x = service.getMatkul(Integer.parseInt(id_matkul_x));
                    if (matkul_x == null) {
                        System.out.println("Tidak ditemukan KODE Matkul " + id_matkul_x);
                        break;
                    }
                    System.out.print("Nama Matkul : ");
                    String nama_mk_x = in.nextLine();
                    System.out.print("SKS : ");
                    String sks_x = in.nextLine();
                    System.out.print("Simpan? (Y/N) : ");
                    String ubah_x = in.nextLine();
                    if (ubah_x.toLowerCase().equals("y")) {
                        matkul_x.setKd_mk(Integer.parseInt(id_matkul_x));
                        matkul_x.setNama_mk(nama_mk_x);
                        matkul_x.setSks(Integer.parseInt(sks_x));
                        service.save(matkul_x);
                    }
                    break;
                case "4":
                    System.out.print("Masukkan KODE Matkul : ");
                    String id_matkul_d = in.nextLine();
                    MATKUL matkul_d = service.getMatkul(Integer.parseInt(id_matkul_d));
                    if (matkul_d == null) {
                        System.out.println("Tidak ditemukan KODE Matkul " + id_matkul_d);
                        break;
                    }
                    System.out.print("Hapus? (Y/N) : ");
                    String hapus = in.nextLine();
                    if (hapus.toLowerCase().equals("y")) {
                        service.delete(matkul_d);
                    }
                    break;
                case "0":
                    active = false;
                    break;
            }
        }
    }
    
    public static void getMenuNilai() {
        
    }
    
}
