package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.produto.ImagemProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.model.venda.VendaProdutoPesquisa;
import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import java.math.BigDecimal;
import java.util.List;

public class VendaIniciaMetodosProdutos {

    private final JFvendaInicia view;
    private final List<VendaModeloItem> vendaModeloItemList;
    private final VendaIniciaMetodosComum vendaIniciaMetodosComum;

    public VendaIniciaMetodosProdutos(JFvendaInicia view, List<VendaModeloItem> vendaModeloItemList, VendaIniciaMetodosComum vendaIniciaMetodosComum) {
        this.view = view;
        this.vendaModeloItemList = vendaModeloItemList;
        this.vendaIniciaMetodosComum = vendaIniciaMetodosComum;
    }

    public void pesquisarProdutos() {

        VendaProdutoPesquisa vendaProdutoPesquisa = new VendaProdutoPesquisa(view.jTlistaDeProdutos);
        vendaProdutoPesquisa.setNome(view.jTpesquisaNomeProduto.getText());
        vendaProdutoPesquisa.pesquisar();

    }

    public void exibirProdutoSelecionado(long id) {

        ItemVenda itemVenda = new ItemVenda(id, new ProdutoModelo());
        view.jTitemTotal.setText(ConverteNumeroParaMoedaBr.converter(calcularProdutoSelecionado(id).toString()));
        new ImagemProduto().exibir(String.valueOf(itemVenda.getId()), view.jPdadosPerfilFoto);
        habilitarCamposDeAdicionarProduto(itemVenda);

    }

    public void limparProdutoSelecionado() {

        view.jTitemQuantidade.setText(null);
        view.jTitemTotal.setText(null);
        view.jTlistaDeProdutos.clearSelection();
        view.jTlistaDeServicos.clearSelection();
        view.jBprodutoAdicionaItem.setEnabled(false);
        view.jBcalcularGranel.setEnabled(false);
        view.jPdadosPerfilFoto.removeAll();
        view.jPdadosPerfilFoto.repaint();

    }

    public void adicionarProdutoNaLista(long id) {

        ItemVenda itemVenda = new ItemVenda(id, new ProdutoModelo());
        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setIdProduto(itemVenda.getProdutoModelo().getId());
        vendaModeloItem.setValor(itemVenda.getValor());
        vendaModeloItem.setQuantidade(BigDecimais.formatarParaBigDecimal(view.jTitemQuantidade.getText()));
        vendaModeloItemList.add(vendaModeloItem);

        habilitarCamposDeAdicionarProduto(new ItemVenda(0, new ProdutoModelo()));
        limparProdutoSelecionado();
        vendaIniciaMetodosComum.exibirSelecionados();
        habilitarCamposDeExcluirProdutoAdicionado();

    }

    public void removerProdutoNaLista() {

        try {
            vendaModeloItemList.remove(view.jTitensSelecionados.getSelectedRow());
        } catch (Exception ex) {
        }

        vendaIniciaMetodosComum.exibirSelecionados();
        habilitarCamposDeExcluirProdutoAdicionado();

    }

    public BigDecimal calcularProdutoSelecionado(long id) {

        ItemVenda itemVenda = new ItemVenda(id, new ProdutoModelo());
        BigDecimal quantidade = BigDecimais.formatarParaBigDecimal(view.jTitemQuantidade.getText());
        return itemVenda.getValor().multiply(quantidade);

    }

    private void habilitarCamposDeAdicionarProduto(ItemVenda itemVenda) {

        view.jBprodutoAdicionaItem.setEnabled(itemVenda.getId() != 0);
        view.jBcalcularGranel.setEnabled(itemVenda.getProdutoModelo().getId() != 0 && itemVenda.getPeso().compareTo(new BigDecimal(0)) == 1);
        view.jTitemQuantidade.setEnabled(true);

    }

    private void habilitarCamposDeExcluirProdutoAdicionado() {

        view.jBremoveItemSelecionadoLista.setEnabled(view.jTitensSelecionados.getSelectedRow() != -1);

    }

    public void popularGranel(BigDecimal quantidade, ItemVenda itemVenda) {

        view.jTitemQuantidade.setText(quantidade.toString());
        exibirProdutoSelecionado(itemVenda.getId());

    }

}
