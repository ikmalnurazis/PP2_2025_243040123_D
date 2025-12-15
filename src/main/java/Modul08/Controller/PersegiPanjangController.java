package Modul08.Controller;

import Modul08.Model.PersegiPanjangModel;
import Modul08.View.PersegiPanjangView;

public class PersegiPanjangController {

    private PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangView view) {
        this.view = view;

        view.btnHitung.addActionListener(e -> hitung());
        view.btnReset.addActionListener(e -> reset());
    }

    private void hitung() {
        try {
            double p = Double.parseDouble(view.txtPanjang.getText());
            double l = Double.parseDouble(view.txtLebar.getText());

            PersegiPanjangModel model = new PersegiPanjangModel(p, l);

            view.lblLuas.setText("Luas : " + model.getLuas());
            view.lblKeliling.setText("Keliling : " + model.getKeliling());
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(view, "Input harus angka!");
        }
    }

    private void reset() {
        view.txtPanjang.setText("");
        view.txtLebar.setText("");
        view.lblLuas.setText("Luas : ");
        view.lblKeliling.setText("Keliling : ");
    }
}
