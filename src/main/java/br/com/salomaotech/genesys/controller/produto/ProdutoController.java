package br.com.salomaotech.genesys.controller.produto;

import br.com.salomaotech.genesys.model.produto.ComboBoxProdutoCategoria;
import br.com.salomaotech.genesys.view.JFproduto;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class ProdutoController {

    private final JFproduto view = new JFproduto();
    private final ComboBoxProdutoCategoria comboBoxProdutoCategoriaCadastro = new ComboBoxProdutoCategoria(view.jCcategoria);
    private final ComboBoxProdutoCategoria comboBoxProdutoCategoriaPesquisa = new ComboBoxProdutoCategoria(view.jCpesquisaCategoria);
    private final ProdutoMetodos produtoMetodos = new ProdutoMetodos(view);
    private final ProdutoEventos produtoEventos = new ProdutoEventos(view);

    public ProdutoController() {

        /* preenche comboboxes */
        comboBoxProdutoCategoriaCadastro.preencher();
        comboBoxProdutoCategoriaPesquisa.preencher();

        /* eventos */
        produtoEventos.setComboBoxProdutoCategoriaCadastro(comboBoxProdutoCategoriaCadastro);
        produtoEventos.setComboBoxProdutoCategoriaPesquisa(comboBoxProdutoCategoriaPesquisa);

    }

    public void construir() {

        /* view */
        new MudaIconeJframe().alterar("produto64x", view);
        view.setVisible(true);
        view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        view.jTabaPrincipal.setSelectedIndex(1);
        view.jTnome.requestFocus();

        /* metodos */
        produtoMetodos.addPopUpMenu();
        produtoMetodos.habilitarCampos();

        /* eventos */
        produtoEventos.setProdutoMetodos(produtoMetodos);
        produtoEventos.addEventos();

        /* exibe os dados */
        produtoMetodos.pesquisar();

    }

}
