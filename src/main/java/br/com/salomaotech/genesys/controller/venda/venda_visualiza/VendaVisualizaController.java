package br.com.salomaotech.genesys.controller.venda.venda_visualiza;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFvendaVisualiza;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VendaVisualizaController {

    private final long id;
    private final JFvendaVisualiza view = new JFvendaVisualiza();
    private final VendaVisualizaMetodos vendaVisualizaMetodos = new VendaVisualizaMetodos(view);
    private final VendaVisualizaEventos vendaVisualizaEventos = new VendaVisualizaEventos(view);
    private final VendaVisualizaMetodosCommand vendaVisualizaMetodosCommand = new VendaVisualizaMetodosCommand(view);
    private final ComboBoxClientes comboBoxClientes = new ComboBoxClientes(view.jCcliente, vendaVisualizaMetodosCommand);

    public VendaVisualizaController(long id) {

        this.id = id;

        /* preenche comboboxes */
        comboBoxClientes.preencher();

        /* inicializa commands */
        vendaVisualizaMetodosCommand.setComboBoxClientes(comboBoxClientes);

        /* metodos */
        vendaVisualizaMetodos.setComboBoxClientes(comboBoxClientes);

        /* eventos */
        vendaVisualizaEventos.setVendaVisualizaMetodos(vendaVisualizaMetodos);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        /* metodos */
        vendaVisualizaMetodos.abrirCadastro(id);
        vendaVisualizaMetodos.habilitarCampos();

        /* eventos */
        vendaVisualizaEventos.addEventos();

    }

}
