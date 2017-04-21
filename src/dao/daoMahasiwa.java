/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import MODEL.MAHASISWA;

/**
 *
 * @author HP
 */
public class daoMahasiwa {
    private Connection connection;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;

    private final String insertQuery = "insert into mahasiswa(NIM,NAMA,JURUSAN,TGL_LAHIR) "
            + " values(?,?,?,?,?)";
    private final String updateQuery = "update mahasiswa set NAMA=?, "
            + " JURUSAN=?, TGL_LAHIR=?, where nim=?";
    private final String deleteQuery = "delete from mahasiswa where nim=?";
    private final String getByIdQuery = "select * from mahasiswa where nim =?";
    private final String getAllQuery = "select * from mahasiswa";
    private Object Mahasiswa;

    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getByIdStatement = this.connection.prepareStatement(getByIdQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
    }
    
    public MAHASISWA save(MAHASISWA mahasiswa) throws SQLException{
        insertStatement.setInt(1, mahasiswa.getNim());
        insertStatement.setString(2, mahasiswa.getNama());
        insertStatement.setString(3, mahasiswa.getJurusan());
        insertStatement.setString(4, mahasiswa.getTgl_lahir());
        insertStatement.executeUpdate();
        return mahasiswa;
    }
    
    public MAHASISWA update(MAHASISWA mahasiswa) throws SQLException {
        updateStatement.setInt(1, mahasiswa.getNim());
        updateStatement.setString(2, mahasiswa.getNama());
        updateStatement.setString(3, mahasiswa.getJurusan());
        updateStatement.setString(4, mahasiswa.getTGL_LAHIR());
        updateStatement.setInt(5, mahasiswa.getNim());
        updateStatement.executeUpdate();
        return mahasiswa;
    }
    
    public MAHASISWA delete(MAHASISWA mahasiswa) throws SQLException {
        deleteStatement.setInt(1, mahasiswa.getNim());
        deleteStatement.executeUpdate();
        return mahasiswa;
    }
    
    public MAHASISWA getByNim(int nim) throws SQLException{
        getByIdStatement.setInt(1, nim);
        ResultSet rs = getByIdStatement.executeQuery();
        //proses mapping dari relational ke object
        if (rs.next()) {
            MAHASISWA mahasiswa = new MAHASISWA();
            mahasiswa.setNim(rs.getInt("NIM"));
            mahasiswa.setNama(rs.getString("NAMA"));
            mahasiswa.setTgllahir(rs.getString("JURUSAN"));
            mahasiswa.setAlamat(rs.getString("TGL_LAHIR"));
            return mahasiswa;
        }
        return null;
    }
    
    public List<MAHASISWA> getAll() throws SQLException{
        List<MAHASISWA> mahasiswaR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            MAHASISWA mahasiswa = new MAHASISWA();
            mahasiswa.setNim(rs.getInt("NIM"));
            mahasiswa.setNama(rs.getString("NAMA"));
            mahasiswa.setTgllahir(rs.getString("JURUSAN"));
            mahasiswa.setAlamat(rs.getString("TGL_LAHIR"));
            mahasiswaR.add(mahasiswa);
        }
        return mahasiswaR;
    }
}
