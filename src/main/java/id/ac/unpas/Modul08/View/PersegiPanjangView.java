package id.ac.unpas.Modul08.View;

import javax.swing.*;

public class PersegiPanjangView extends JFrame {

    public JTextField txtPanjang, txtLebar;
    public JButton btnHitung, btnReset;
    public JLabel lblLuas, lblKeliling;

    public PersegiPanjangView() {
        setTitle("MVC Persegi Panjang");
        setSize(350, 260);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Panjang");
        l1.setBounds(20, 20, 80, 25);
        add(l1);

        txtPanjang = new JTextField();
        txtPanjang.setBounds(120, 20, 150, 25);
        add(txtPanjang);

        JLabel l2 = new JLabel("Lebar");
        l2.setBounds(20, 60, 80, 25);
        add(l2);

        txtLebar = new JTextField();
        txtLebar.setBounds(120, 60, 150, 25);
        add(txtLebar);

        btnHitung = new JButton("Hitung");
        btnHitung.setBounds(20, 100, 100, 30);
        add(btnHitung);

        btnReset = new JButton("Reset");
        btnReset.setBounds(140, 100, 100, 30);
        add(btnReset);

        lblLuas = new JLabel("Luas : ");
        lblLuas.setBounds(20, 150, 200, 25);
        add(lblLuas);

        lblKeliling = new JLabel("Keliling : ");
        lblKeliling.setBounds(20, 180, 200, 25);
        add(lblKeliling);

        setVisible(true);
    }
}
