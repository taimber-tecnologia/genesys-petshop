package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.venda.ComboBoxItemVenda;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class VendaIniciaController {

    private final JFvendaInicia view = new JFvendaInicia();
    private final VendaIniciaMetodos vendaIniciaMetodos = new VendaIniciaMetodos(view);
    private final VendaIniciaEventos vendaIniciaEventos = new VendaIniciaEventos(view);
    private final ComboBoxItemVenda comboBoxItemVenda = new ComboBoxItemVenda(view.jCprodutoLista, vendaIniciaEventos);

    public VendaIniciaController() {

        /* preenche comboboxes */
        comboBoxItemVenda.preencher();

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
        vendaIniciaEventos.setComboBoxItemVenda(comboBoxItemVenda);
        vendaIniciaEventos.setVendaIniciaMetodos(vendaIniciaMetodos);

    }

    public JFvendaInicia abrirCadastro(long id) {

        construir();
        vendaIniciaMetodos.abrirCadastro(id);
        vendaIniciaMetodos.habilitarCamposDeExcluirVenda();
        return view;

    }

}
