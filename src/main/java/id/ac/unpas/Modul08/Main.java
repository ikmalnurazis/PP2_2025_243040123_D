package id.ac.unpas.Modul08;

import id.ac.unpas.Modul08.View.PersegiPanjangView;
import id.ac.unpas.Modul08.Controller.PersegiPanjangController;

public class Main {
    public static void main(String[] args) {
        PersegiPanjangView View = new PersegiPanjangView();
        new PersegiPanjangController(View);
    }
}
