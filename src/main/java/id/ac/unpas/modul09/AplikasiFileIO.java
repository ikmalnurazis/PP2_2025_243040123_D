/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AplikasiFileIO extends JFrame {

    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;

    public AplikasiFileIO() {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        JPanel buttonPanel = new JPanel();
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        btnOpenText.addActionListener(e -> bukaFileText());
        btnSaveText.addActionListener(e -> simpanFileText());
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        btnLoadBinary.addActionListener(e -> muatConfigBinary());

        setVisible(true);
    }

    private void bukaFileText() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null;

            try {
                reader = new BufferedReader(new FileReader(file));
                textArea.setText("");

                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                JOptionPane.showMessageDialog(this, "file berhasil dimuat!");

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "file tidak ditemukan");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file");
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ex) {
                }
            }
        }
    }

    private void simpanFileText() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "file berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "gagal menyimpan file");
            }
        }
    }

    private void simpanConfigBinary() {
        try (DataOutputStream out =
                     new DataOutputStream(new FileOutputStream("config.bin"))) {
            out.writeInt(textArea.getFont().getSize());
            JOptionPane.showMessageDialog(this, "config berhasil disimpan");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "gagal menyimpan config");
        }
    }

    private void muatConfigBinary() {
        try (DataInputStream in =
                     new DataInputStream(new FileInputStream("config.bin"))) {
            int fontSize = in.readInt();
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this, "config berhasil dimuat");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "gagal memuat config");
        }
    }

    public static void main(String[] args) {
        new AplikasiFileIO();
    }
}
