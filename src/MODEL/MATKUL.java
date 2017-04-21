/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author HP
 */
public class MATKUL {
    private int kd_matkul;
    private String nama_matkul;
    private int sks;

    public int getKd_mk() {
        return kd_matkul;
    }

    public void setKd_mk(int kd_matkul) {
        this.kd_matkul = kd_matkul;
    }

    public String getNama_mk() {
        return nama_matkul;
    }

    public void setNama_mk(String nama_matkul) {
        this.nama_matkul = nama_matkul;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public int getKd_matkul() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNama_matkul() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
