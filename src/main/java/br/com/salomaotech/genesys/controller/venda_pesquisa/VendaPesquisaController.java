package br.com.salomaotech.genesys.controller.venda_pesquisa;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.venda_pesquisa.VendaPesquisa;
import br.com.salomaotech.genesys.view.JFvendaPesquisa;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VendaPesquisaController {

    private final JFvendaPesquisa view = new JFvendaPesquisa();
    private final VendaPesquisaMetodos vendaPesquisaMetodos = new VendaPesquisaMetodos(view);
    private final VendaPesquisaEventos vendaPesquisaEventos = new VendaPesquisaEventos(view);
    private final ComboBoxClientes comboBoxClientesPesquisa = new ComboBoxClientes(view.jCpesquisaNomeCliente);

    public VendaPesquisaController() {

        /* preenche comboboxes */
        comboBoxClientesPesquisa.preencher();

        /* eventos */
        vendaPesquisaEventos.setVendaPesquisaMetodos(vendaPesquisaMetodos);
        vendaPesquisaEventos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);

        /* metodos */
        vendaPesquisaMetodos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        /* eventos */
        vendaPesquisaEventos.addEventos();

        /* exibe os dados */
        new VendaPesquisa(view.jTresultados, view.jCpaginador).pesquisar();

    }

}
