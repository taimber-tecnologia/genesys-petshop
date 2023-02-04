package br.com.salomaotech.genesys.controller.produto;

import br.com.salomaotech.genesys.model.fornecedor.ComboBoxFornecedores;
import br.com.salomaotech.genesys.model.produto.ComboBoxProdutoCategoria;
import br.com.salomaotech.genesys.view.JFproduto;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import javax.swing.JFrame;

public class ProdutoController {

    private final JFproduto view = new JFproduto();
    private final ComboBoxProdutoCategoria comboBoxProdutoCategoriaCadastro = new ComboBoxProdutoCategoria(view.jCcategoria);
    private final ComboBoxProdutoCategoria comboBoxProdutoCategoriaPesquisa = new ComboBoxProdutoCategoria(view.jCpesquisaCategoria);
    private final ComboBoxFornecedores comboBoxFornecedores = new ComboBoxFornecedores(view.jCfornecedor);
    private final ProdutoMetodos produtoMetodos = new ProdutoMetodos(view);
    private final ProdutoEventos produtoEventos = new ProdutoEventos(view);

    public ProdutoController() {

        /* preenche comboboxes */
        comboBoxProdutoCategoriaCadastro.preencher();
        comboBoxProdutoCategoriaPesquisa.preencher();
        comboBoxFornecedores.preencher();

        /* metodos */
        produtoMetodos.setComboBoxFornecedores(comboBoxFornecedores);

        /* eventos */
        produtoEventos.setComboBoxProdutoCategoriaCadastro(comboBoxProdutoCategoriaCadastro);
        produtoEventos.setComboBoxProdutoCategoriaPesquisa(comboBoxProdutoCategoriaPesquisa);
        produtoEventos.setComboBoxFornecedores(comboBoxFornecedores);

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

    public void construirEstoqueBaixo() {

        construir();
        view.jCestoqueBaixo.setSelected(true);
        produtoMetodos.pesquisar();

    }

}
