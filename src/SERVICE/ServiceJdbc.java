package SERVICE;


import dao.daoMahasiwa;
import dao.daoMATKUL;
import MODEL.MAHASISWA;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import MODEL.MATKUL;

/**
 *
 * @author HP
 */
public class ServiceJdbc {

    private daoMahasiwa mahasiswaDao;
    private daoMATKUL matkulDao;
    private Connection connection;
    
    public void setDataSource(DataSource dataSource){
        try {
            connection = dataSource.getConnection();
            mahasiswaDao = new daoMahasiwa();
            matkulDao = new daoMATKUL();
            mahasiswaDao.setConnection(connection);
            matkulDao.setConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public MAHASISWA save(MAHASISWA mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.save(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }
    
    public MAHASISWA update(MAHASISWA mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.update(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }

    public MAHASISWA delete(MAHASISWA mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.delete(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }

    public MAHASISWA getMAHASISWA(int nim) {
        try {
            return mahasiswaDao.getByNim(nim);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<MAHASISWA> getAllMAHASISWA() {
        try {
            return mahasiswaDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public MATKUL save(MATKUL matkul) {
        try {
            connection.setAutoCommit(false);
            matkulDao.save(matkul);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matkul;
    }

    public MATKUL delete(MATKUL matkul) {
        try {
            connection.setAutoCommit(false);
            matkulDao.delete(matkul);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matkul;
    }

    public MATKUL getMatkul(int id) {
        try {
            return matkulDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<MATKUL> getAllMatkul() {
        try {
            return matkulDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
