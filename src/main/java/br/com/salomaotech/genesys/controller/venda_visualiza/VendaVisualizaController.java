package br.com.salomaotech.genesys.controller.venda_visualiza;

import br.com.salomaotech.genesys.view.JFvendaVisualiza;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VendaVisualizaController {

    private final JFvendaVisualiza view = new JFvendaVisualiza();
    private final VendaVisualizaEventos vendaVisualizaEventos = new VendaVisualizaEventos(view);
    private final VendaVisualizaMetodos vendaVisualizaMetodos = new VendaVisualizaMetodos(view);
    private final long id;

    public VendaVisualizaController(long id) {

        this.id = id;

        /* metodos */
        vendaVisualizaMetodos.abrirCadastro(id);

        /* eventos */
        vendaVisualizaEventos.setVendaVisualizaMetodos(vendaVisualizaMetodos);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        /* metodos */
        vendaVisualizaMetodos.habilitarCampos();


        /* eventos */
        vendaVisualizaEventos.addEventos();

    }

}
