package br.com.salomaotech.genesys.controller.venda.venda_conclui;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.genesys.view.JFvendaConclui;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;

public class VendaConcluiController {

    private final JFvendaConclui view = new JFvendaConclui();
    private final VendaConcluiEventos vendaConcluiEventos;
    private final VendaConcluiMetodos vendaConcluiMetodos;
    private final VendaConcluiMetodosCommand vendaConcluiMetodosCommand = new VendaConcluiMetodosCommand(view);
    private final ComboBoxClientes comboBoxClientes = new ComboBoxClientes(view.jCcliente, vendaConcluiMetodosCommand);

    public VendaConcluiController(VendaModelo vendaModelo, JFvendaInicia viewVenda) {

        vendaConcluiMetodos = new VendaConcluiMetodos(view, vendaModelo, viewVenda, comboBoxClientes);
        vendaConcluiEventos = new VendaConcluiEventos(view);

        /* preenche comboboxes */
        comboBoxClientes.preencher();

        /* inicializa commands */
        vendaConcluiMetodosCommand.setComboBoxClientes(comboBoxClientes);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);

        /* eventos */
        vendaConcluiEventos.setVendaConcluiMetodos(vendaConcluiMetodos);
        vendaConcluiEventos.addEventos();

        /* metodos */
        vendaConcluiMetodos.habilitarCampos();
        vendaConcluiMetodos.exibirVenda();

    }

}
