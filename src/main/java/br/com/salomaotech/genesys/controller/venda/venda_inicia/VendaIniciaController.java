package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.produto.ComboBoxProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VendaIniciaController {

    private final JFvendaInicia view = new JFvendaInicia();
    private final VendaIniciaMetodos vendaIniciaMetodos = new VendaIniciaMetodos(view);
    private final VendaIniciaEventos vendaIniciaEventos = new VendaIniciaEventos(view);
    private final ComboBoxProduto comboBoxProduto = new ComboBoxProduto(view.jCprodutoLista, vendaIniciaEventos);

    public VendaIniciaController() {

        /* preenche comboboxes */
        comboBoxProduto.preencher();

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("venda64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        /* metodos */
        vendaIniciaMetodos.habilitarCamposDeAdicionarProduto(new ProdutoModelo());
        vendaIniciaMetodos.habilitarCamposDeExcluirVenda();

        /* eventos */
        vendaIniciaEventos.addEventos();
        vendaIniciaEventos.setComboBoxProduto(comboBoxProduto);
        vendaIniciaEventos.setVendaIniciaMetodos(vendaIniciaMetodos);

    }

    public JFvendaInicia abrirCadastro(long id) {

        construir();
        vendaIniciaMetodos.abrirCadastro(id);
        vendaIniciaMetodos.habilitarCamposDeExcluirVenda();
        return view;

    }

}
