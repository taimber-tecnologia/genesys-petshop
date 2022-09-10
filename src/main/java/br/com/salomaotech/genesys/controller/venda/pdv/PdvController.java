package br.com.salomaotech.genesys.controller.venda.pdv;

import br.com.salomaotech.genesys.model.produto.ComboBoxProduto;
import br.com.salomaotech.genesys.view.JFpdv;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class PdvController {

    private final JFpdv view = new JFpdv();
    private final PdvMetodos pdvMetodos = new PdvMetodos(view);
    private final PdvEventos pdvEventos = new PdvEventos(view);
    private final ComboBoxProduto comboBoxProduto = new ComboBoxProduto(view.jCprodutoLista, pdvEventos);

    public PdvController() {

        /* preenche comboboxes */
        comboBoxProduto.preencher();

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        /* metodos */
        pdvMetodos.addPopUpMenu();
        pdvMetodos.habilitarCampos();

        /* eventos */
        pdvEventos.addEventos();
        pdvEventos.setComboBoxProduto(comboBoxProduto);
        pdvEventos.setPdvMetodos(pdvMetodos);

    }

}
