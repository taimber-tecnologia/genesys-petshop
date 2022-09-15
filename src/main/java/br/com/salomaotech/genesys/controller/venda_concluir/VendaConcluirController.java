package br.com.salomaotech.genesys.controller.venda_concluir;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.view.JFvenda;
import br.com.salomaotech.genesys.view.JFvendaConcluir;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;

public class VendaConcluirController {

    private final JFvendaConcluir view = new JFvendaConcluir();
    private final VendaConcluirEventos vendaConcluirEventos;
    private final VendaConcluirMetodos vendaConcluirMetodos;
    private final VendaConcluirMetodosCommand vendaConcluirMetodosCommand = new VendaConcluirMetodosCommand(view);
    private final ComboBoxClientes comboBoxClientes = new ComboBoxClientes(view.jCcliente, vendaConcluirMetodosCommand);

    public VendaConcluirController(VendaModelo vendaModelo, JFvenda viewVenda) {

        vendaConcluirMetodos = new VendaConcluirMetodos(view, vendaModelo, viewVenda, comboBoxClientes);
        vendaConcluirEventos = new VendaConcluirEventos(view);

        /* preenche comboboxes */
        comboBoxClientes.preencher();

        /* inicializa commands */
        vendaConcluirMetodosCommand.setComboBoxClientes(comboBoxClientes);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);

        /* eventos */
        vendaConcluirEventos.setVendaConcluirMetodos(vendaConcluirMetodos);
        vendaConcluirEventos.addEventos();

        /* metodos */
        vendaConcluirMetodos.habilitarCampos();
        vendaConcluirMetodos.exibirVenda();

    }

}
