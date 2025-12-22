package id.ac.unpas.Modul07;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManajemenNilaiSiswaApp extends JFrame {

    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tab;

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        JLabel lblNama = new JLabel("Nama Mahasiswa:");
        JLabel lblMatkul = new JLabel("Mata Kuliah:");
        JLabel lblNilai = new JLabel("Nilai:");

        txtNama = new JTextField();
        txtNilai = new JTextField();

        cmbMatkul = new JComboBox<>(new String[]{
                "Praktikum Pemrograman II", "Pemrograman Basis Data", "Pemrograman Web",
                "Design Visual", "Infrastruktur Jaringan", "Internet of Think", "Bahasa Inggris"
        });

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(e -> prosesSimpan());

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(e -> resetInputan());

        panel.add(lblNama);
        panel.add(txtNama);

        panel.add(lblMatkul);
        panel.add(cmbMatkul);

        panel.add(lblNilai);
        panel.add(txtNilai);

        panel.add(btnReset);
        panel.add(btnSimpan);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"Nama", "Mata Kuliah", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        panel.add(new JScrollPane(tableData), BorderLayout.CENTER);

        JButton btnHapus = new JButton("Hapus Data");
        btnHapus.addActionListener(e -> hapusData());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnHapus);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void prosesSimpan() {
        String nama = txtNama.getText();
        String strNilai = txtNilai.getText();
        String matkul = cmbMatkul.getSelectedItem().toString();

        if (nama.trim().length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama minimal harus 3 karakter!");
            return;
        }

        int nilai;
        try {
            nilai = Integer.parseInt(strNilai);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!");
            return;
        }

        if (nilai < 0 || nilai > 100) {
            JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!");
            return;
        }

        String grade;
        switch (nilai / 10) {
            case 10:
            case 9:
            case 8: grade = "A"; break;
            case 7: grade = "B"; break;
            case 6: grade = "C"; break;
            case 5: grade = "D"; break;
            default: grade = "E"; break;
        }

        Object[] data = {nama, matkul, nilai, grade};
        tableModel.addRow(data);

        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
    }

    private void resetInputan() {
        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);
    }

    private void hapusData() {
        int selected = tableData.getSelectedRow();
        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!");
            return;
        }
        tableModel.removeRow(selected);
    }

    public ManajemenNilaiSiswaApp() {
        setTitle("Aplikasi Input Nilai Siswa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tab = new JTabbedPane();
        tab.add("Input Data", createInputPanel());
        tab.add("Daftar Nilai", createTablePanel());

        add(tab);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}
