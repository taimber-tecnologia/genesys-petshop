package br.com.salomaotech.genesys.controller.venda.pdv;

import br.com.salomaotech.genesys.view.JFvendaPdv;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VendaPdvController {

    private final JFvendaPdv view = new JFvendaPdv();

    public VendaPdvController() {

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

    }

}
