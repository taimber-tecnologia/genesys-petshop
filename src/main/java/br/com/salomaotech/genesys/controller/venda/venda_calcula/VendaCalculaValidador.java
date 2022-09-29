package br.com.salomaotech.genesys.controller.venda.venda_calcula;

import br.com.salomaotech.genesys.view.JFvendaCalcula;

public class VendaCalculaValidador {

    private final JFvendaCalcula view;
    private String mensagensErro = "";

    public VendaCalculaValidador(JFvendaCalcula view) {
        this.view = view;
    }

    public boolean isValido() {

        return true;

    }

}
