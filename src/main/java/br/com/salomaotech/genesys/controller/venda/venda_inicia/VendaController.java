package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.produto.ComboBoxProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvenda;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VendaController {

    private final JFvenda view = new JFvenda();
    private final VendaMetodos vendaMetodos = new VendaMetodos(view);
    private final VendaEventos vendaEventos = new VendaEventos(view);
    private final ComboBoxProduto comboBoxProduto = new ComboBoxProduto(view.jCprodutoLista, vendaEventos);

    public VendaController() {

        /* preenche comboboxes */
        comboBoxProduto.preencher();

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        /* metodos */
        vendaMetodos.habilitarCamposDeAdicionarProduto(new ProdutoModelo());

        /* eventos */
        vendaEventos.addEventos();
        vendaEventos.setComboBoxProduto(comboBoxProduto);
        vendaEventos.setVendaMetodos(vendaMetodos);

    }

}
