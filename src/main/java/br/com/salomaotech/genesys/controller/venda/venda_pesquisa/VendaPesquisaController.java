package br.com.salomaotech.genesys.controller.venda.venda_pesquisa;

import br.com.salomaotech.genesys.model.venda.VendaPesquisa;
import br.com.salomaotech.genesys.view.JFvendaPesquisa;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VendaPesquisaController {

    private final JFvendaPesquisa view = new JFvendaPesquisa();
    private final VendaPesquisaMetodos vendaPesquisaMetodos = new VendaPesquisaMetodos(view);
    private final VendaPesquisaEventos vendaPesquisaEventos = new VendaPesquisaEventos(view);

    public VendaPesquisaController() {

        /* eventos */
        vendaPesquisaEventos.setVendaPesquisaMetodos(vendaPesquisaMetodos);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        /* metodos */
        vendaPesquisaMetodos.addPopUpMenu();

        /* eventos */
        vendaPesquisaEventos.addEventos();

        /* exibe os dados */
        new VendaPesquisa(view.jTresultados, view.jCpaginador).pesquisar();

    }

}
