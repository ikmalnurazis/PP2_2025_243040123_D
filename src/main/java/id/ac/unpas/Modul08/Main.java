package Modul08;

import Modul08.View.PersegiPanjangView;
import Modul08.Controller.PersegiPanjangController;

public class Main {
    public static void main(String[] args) {
        PersegiPanjangView view = new PersegiPanjangView();
        new PersegiPanjangController(view);
    }
}
