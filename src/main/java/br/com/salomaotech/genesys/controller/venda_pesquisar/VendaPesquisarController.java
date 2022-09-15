package br.com.salomaotech.genesys.controller.venda_pesquisar;

import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.model.venda_pesquisar.VendaPesquisa;
import br.com.salomaotech.genesys.view.JFvendaPesquisa;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VendaPesquisarController {

    private final JFvendaPesquisa view = new JFvendaPesquisa();
    private final VendaPesquisarMetodos vendaPesquisarMetodos = new VendaPesquisarMetodos(view);
    private final VendaPesquisarEventos vendaPesquisarEventos = new VendaPesquisarEventos(view);
    private final ComboBoxClientes comboBoxClientesPesquisa = new ComboBoxClientes(view.jCpesquisaNomeCliente);

    public VendaPesquisarController() {

        /* preenche comboboxes */
        comboBoxClientesPesquisa.preencher();

        /* eventos */
        vendaPesquisarEventos.setVendaPesquisarMetodos(vendaPesquisarMetodos);
        vendaPesquisarEventos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);

        /* metodos */
        vendaPesquisarMetodos.setComboBoxClientesPesquisa(comboBoxClientesPesquisa);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        /* eventos */
        vendaPesquisarEventos.addEventos();

        /* exibe os dados */
        new VendaPesquisa(view.jTresultados, view.jCpaginador).pesquisar();

    }

}
