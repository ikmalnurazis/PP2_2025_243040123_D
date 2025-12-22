/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.Modul10.controller;

/**
 *
 * @author User
 */


import id.ac.unpas.Modul10.model.KoneksiDB;
import id.ac.unpas.Modul10.model.Mahasiswa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaController {
   public List<Mahasiswa> getAllData() throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        Connection conn = KoneksiDB.configDB();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM mahasiswa");
        while(rs.next()) {
            list.add(new Mahasiswa(rs.getString("nama"), rs.getString("nim"), rs.getString("jurusan")));
        }
        return list;
    }

    public void tambah(Mahasiswa m) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        // Latihan 4: Cek NIM duplikat
        PreparedStatement cek = conn.prepareStatement("SELECT nim FROM mahasiswa WHERE nim=?");
        cek.setString(1, m.getNim());
        if (cek.executeQuery().next()) throw new SQLException("NIM sudah terdaftar!");

        PreparedStatement ps = conn.prepareStatement("INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?,?,?)");
        ps.setString(1, m.getNama());
        ps.setString(2, m.getNim());
        ps.setString(3, m.getJurusan());
        ps.execute();
    }

    public void ubah(Mahasiswa m) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        PreparedStatement ps = conn.prepareStatement("UPDATE mahasiswa SET nama=?, jurusan=? WHERE nim=?");
        ps.setString(1, m.getNama());
        ps.setString(2, m.getJurusan());
        ps.setString(3, m.getNim());
        ps.executeUpdate();
    }

    public void hapus(String nim) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM mahasiswa WHERE nim=?");
        ps.setString(1, nim);
        ps.execute();
    }

    // Latihan 3: Logika Cari Data
    public List<Mahasiswa> cari(String keyword) throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        Connection conn = KoneksiDB.configDB();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM mahasiswa WHERE nama LIKE ?");
        ps.setString(1, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            list.add(new Mahasiswa(rs.getString("nama"), rs.getString("nim"), rs.getString("jurusan")));
        }
        return list;
    } 
}
