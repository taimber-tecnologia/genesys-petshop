package br.com.salomaotech.genesys.controller.venda.venda_visualiza;

import br.com.salomaotech.genesys.view.JFvendaVisualiza;

public class VendaVisualizaValidador {

    private final JFvendaVisualiza view;
    private String mensagensErro = "";

    public VendaVisualizaValidador(JFvendaVisualiza view) {
        this.view = view;
    }

    public boolean isValido() {

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
