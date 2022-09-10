package br.com.salomaotech.genesys.controller.produto;

import br.com.salomaotech.genesys.model.fornecedor.ComboBoxFornecedores;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.produto.ProdutoPesquisa;
import br.com.salomaotech.genesys.view.JFproduto;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class ProdutoMetodos {

    private final JFproduto view;
    private ComboBoxFornecedores comboBoxFornecedores;

    public ProdutoMetodos(JFproduto view) {
        this.view = view;
    }

    public void setComboBoxFornecedores(ComboBoxFornecedores comboBoxFornecedores) {
        this.comboBoxFornecedores = comboBoxFornecedores;
    }

    public void popularFormulario(ProdutoModelo produtoModelo) {

        view.setId(produtoModelo.getId());
        view.jTnome.setText(produtoModelo.getNome());
        view.jTvalorCusto.setText(produtoModelo.getValorCusto().toString());
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTdescricao.setText(produtoModelo.getDescricao());
        view.jCcategoria.getEditor().setItem(produtoModelo.getCategoria());
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        view.jTestoqueMinimo.setText(produtoModelo.getEstoqueMinimo().toString());
        view.jCmedida.setSelectedItem(produtoModelo.getMedida());
        comboBoxFornecedores.selecionarItemPorId(produtoModelo.getIdFornecedor());

    }

    public void resetarView() {

        popularFormulario(new ProdutoModelo());
        view.jTnome.requestFocus();
        view.jCmedida.setSelectedIndex(-1);

        /* evita erro de indexOfBounds no select */
        try {

            view.jCfornecedor.setSelectedIndex(0);

        } catch (Exception ex) {

            view.jCfornecedor.addItem("");
            view.jCfornecedor.setSelectedIndex(0);

        }

    }

    public void habilitarCampos() {

        boolean isIdAberto = view.getId() != 0;
        view.jBcadastroExcluir.setEnabled(isIdAberto);

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTnome);
        popUp.adicionarMenu(view.jTvalorCusto);
        popUp.adicionarMenu(view.jTvalorVenda);
        popUp.adicionarMenu(view.jTdescricao);
        popUp.adicionarMenu(view.jTquantidade);
        popUp.adicionarMenu(view.jTestoqueMinimo);

    }

    public ProdutoModelo salvar() {

        ProdutoModelo produtoModelo = new ProdutoModelo();
        produtoModelo.setId(view.getId());
        produtoModelo.setNome(view.jTnome.getText());
        produtoModelo.setValorCusto(BigDecimais.formatarParaBigDecimal(view.jTvalorCusto.getText()));
        produtoModelo.setValorVenda(BigDecimais.formatarParaBigDecimal(view.jTvalorVenda.getText()));
        produtoModelo.setDescricao(view.jTdescricao.getText());
        produtoModelo.setCategoria(view.jCcategoria.getEditor().getItem().toString());
        produtoModelo.setQuantidade(BigDecimais.formatarParaBigDecimal(view.jTquantidade.getText()));
        produtoModelo.setEstoqueMinimo(BigDecimais.formatarParaBigDecimal(view.jTestoqueMinimo.getText()));
        produtoModelo.setMedida(view.jCmedida.getSelectedItem().toString());
        produtoModelo.setIdFornecedor(comboBoxFornecedores.getIdSelecionado());
        new Repository(produtoModelo).save();
        return produtoModelo;

    }

    public boolean excluir() {

        return new Repository(new ProdutoModelo()).delete(view.getId());

    }

    public void pesquisar() {

        ProdutoPesquisa produtoPesquisa = new ProdutoPesquisa(view.jTresultados);
        produtoPesquisa.setNome(view.jTpesquisaNome.getText());
        produtoPesquisa.setCategoria(view.jCpesquisaCategoria.getEditor().getItem().toString());
        produtoPesquisa.pesquisar();

    }

}
