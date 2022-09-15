package br.com.salomaotech.genesys.controller.venda_visualiza;

import br.com.salomaotech.genesys.view.JFvendaVisualiza;

public class VendaVisualizaEventos {

    private final JFvendaVisualiza view;
    private VendaVisualizaMetodos vendaVisualizaMetodos;

    public VendaVisualizaEventos(JFvendaVisualiza view) {
        this.view = view;
    }

    public void setVendaVisualizaMetodos(VendaVisualizaMetodos vendaVisualizaMetodos) {
        this.vendaVisualizaMetodos = vendaVisualizaMetodos;
    }

}
