package br.com.salomaotech.genesys.controller.produto;

import br.com.salomaotech.genesys.model.produto.ImagemProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.produto.ProdutoPesquisa;
import br.com.salomaotech.genesys.view.JFproduto;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class ProdutoMetodos {

    private final JFproduto view;

    public ProdutoMetodos(JFproduto view) {
        this.view = view;
    }

    public void popularFormulario(ProdutoModelo produtoModelo) {

        view.setId(produtoModelo.getId());
        view.jTnome.setText(produtoModelo.getNome());
        view.jTvalorVenda.setText(produtoModelo.getValorVenda().toString());
        view.jTdescricao.setText(produtoModelo.getDescricao());
        view.jCcategoria.getEditor().setItem(produtoModelo.getCategoria());
        view.jTquantidade.setText(produtoModelo.getQuantidade().toString());
        view.jTpeso.setText(produtoModelo.getPeso().toString());
        view.jCmedida.setSelectedItem(produtoModelo.getMedida());

    }

    public void resetarView() {

        popularFormulario(new ProdutoModelo());
        view.jPdadosPerfilFoto.removeAll();
        view.jPdadosPerfilFoto.repaint();
        view.jTnome.requestFocus();
        view.jCmedida.setSelectedIndex(58);

    }

    public void resetarViewPesquisa() {

        view.jTpesquisaNome.setText(null);
        view.jCpesquisaCategoria.setSelectedIndex(-1);
        view.jCpaginador.setSelectedIndex(-1);
        pesquisar();

    }

    public void habilitarCampos() {

        boolean isIdAberto = view.getId() != 0;
        view.jBcadastroExcluir.setEnabled(isIdAberto);
        view.jBadicionaFoto.setEnabled(isIdAberto);
        view.jBremoveFoto.setEnabled(isIdAberto);

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTnome);
        popUp.adicionarMenu(view.jTvalorVenda);
        popUp.adicionarMenu(view.jTdescricao);
        popUp.adicionarMenu(view.jTquantidade);
        popUp.adicionarMenu(view.jTpeso);
        popUp.adicionarMenu(view.jTpesquisaNome);

    }

    public void abrirCadastro(long id) {

        Repository repository = new Repository(new ProdutoModelo());
        ProdutoModelo produtoModelo = (ProdutoModelo) repository.findById(id);
        popularFormulario(produtoModelo);
        new ImagemProduto().exibir(String.valueOf(id), view.jPdadosPerfilFoto);
        habilitarCampos();

    }

    public ProdutoModelo salvar() {

        ProdutoModelo produtoModelo = new ProdutoModelo();
        produtoModelo.setId(view.getId());
        produtoModelo.setNome(view.jTnome.getText());
        produtoModelo.setValorVenda(BigDecimais.formatarParaBigDecimal(view.jTvalorVenda.getText()));
        produtoModelo.setDescricao(view.jTdescricao.getText());
        produtoModelo.setCategoria(view.jCcategoria.getEditor().getItem().toString());
        produtoModelo.setQuantidade(BigDecimais.formatarParaBigDecimal(view.jTquantidade.getText()));
        produtoModelo.setPeso(BigDecimais.formatarParaBigDecimal(view.jTpeso.getText()));
        produtoModelo.setMedida(String.valueOf(view.jCmedida.getSelectedItem()));
        new Repository(produtoModelo).save();
        return produtoModelo;

    }

    public boolean excluir() {

        ImagemProduto.remover(String.valueOf(view.getId()), view.jPdadosPerfilFoto);
        return new Repository(new ProdutoModelo()).delete(view.getId());

    }

    public void pesquisar() {

        ProdutoPesquisa produtoPesquisa = new ProdutoPesquisa(view.jTresultados, view.jCpaginador);

        /* valida nome */
        if (!ValidaStringIsEmpty.isEmpty(view.jTpesquisaNome.getText())) {

            produtoPesquisa.setNome(view.jTpesquisaNome.getText());

        }

        /* valida categoria */
        if (!ValidaStringIsEmpty.isEmpty(view.jCpesquisaCategoria.getSelectedItem())) {

            produtoPesquisa.setCategoria(String.valueOf(view.jCpesquisaCategoria.getSelectedItem()));

        }

        produtoPesquisa.pesquisar();

    }

}
