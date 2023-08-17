package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.controller.venda.venda_conclui.VendaConcluiController;
import br.com.salomaotech.genesys.model.configuracoes.PastasSistema;
import br.com.salomaotech.genesys.model.produto.ImagemProduto;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.genesys.model.venda.VendaComprovantePdf;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.model.venda.VendaMovimenta;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VendaIniciaMetodos {

    private final JFvendaInicia view;
    private VendaModelo vendaModelo = new VendaModelo();
    private final List<VendaModeloItem> vendaModeloItemList = new ArrayList();
    private final List<VendaModeloItem> vendaModeloItemOriginalList = new ArrayList();
    private final List<VendaModeloItem> vendaModeloItemBaixaList = new ArrayList();
    private final List<VendaModeloItem> vendaModeloItemDevolveList = new ArrayList();

    public VendaIniciaMetodos(JFvendaInicia view) {
        this.view = view;
    }

    public void exibirProdutoSelecionado(ItemVenda itemVenda) {

        view.jTprodutoCodigo.setText(String.valueOf(itemVenda.getId()));
        view.jTprodutoPreco.setText(ConverteNumeroParaMoedaBr.converter(itemVenda.getValor().toString()));
        view.jTprodutoTotal.setText(ConverteNumeroParaMoedaBr.converter(calcularProdutoSelecionado(itemVenda).toString()));
        new ImagemProduto().exibir(String.valueOf(itemVenda.getId()), view.jPdadosPerfilFoto);
        habilitarCamposDeAdicionarProduto(itemVenda);

    }

    public void limparCalculosProdutoSelecionado() {

        view.jTprodutoQuantidade.setText(null);
        view.jTprodutoDesconto.setText(null);
        view.jTprodutoTotal.setText(null);

    }

    public void limparProdutoSelecionado() {

        view.jTprodutoCodigo.setText(null);
        view.jTprodutoPreco.setText(null);
        view.jTprodutoQuantidade.setText(null);
        view.jTprodutoDesconto.setText(null);
        view.jTprodutoTotal.setText(null);

        try {

            view.jCprodutoLista.setSelectedIndex(0);

        } catch (Exception ex) {

            view.jCprodutoLista.addItem("");
            view.jCprodutoLista.setSelectedIndex(0);

        }

    }

    public void adicionarProdutoNaLista(ItemVenda itemVenda) {

        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setIdProduto(itemVenda.getProdutoModelo().getId());
        vendaModeloItem.setIdServico(itemVenda.getServicoModelo().getId());
        vendaModeloItem.setValor(itemVenda.getValor());
        vendaModeloItem.setQuantidade(BigDecimais.formatarParaBigDecimal(view.jTprodutoQuantidade.getText()));
        vendaModeloItem.setDesconto(BigDecimais.formatarParaBigDecimal(view.jTprodutoDesconto.getText()));
        vendaModeloItemList.add(vendaModeloItem);
        vendaModeloItemBaixaList.add(vendaModeloItem);

        /* atualiza a view */
        limparProdutoSelecionado();
        exibirProdutosSelecionados();

    }

    public void removerProdutoNaLista() {

        try {

            vendaModeloItemDevolveList.add(vendaModeloItemList.get(view.jTprodutoSelecionado.getSelectedRow()));
            vendaModeloItemList.remove(view.jTprodutoSelecionado.getSelectedRow());
            vendaModeloItemBaixaList.remove(view.jTprodutoSelecionado.getSelectedRow());

        } catch (Exception ex) {

        }

        exibirProdutosSelecionados();
        habilitarCamposDeExcluirProdutoAdicionado();

    }

    private void exibirProdutosSelecionados() {

        DefaultTableModel defaultTableModel = (DefaultTableModel) view.jTprodutoSelecionado.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;
        BigDecimal valorTotal = new BigDecimal(0);

        for (VendaModeloItem vendaModeloItem : vendaModeloItemList) {

            ItemVenda itemVenda = new ItemVenda();
            BigDecimal preco = vendaModeloItem.getValor().multiply(vendaModeloItem.getQuantidade()).subtract(vendaModeloItem.getDesconto());

            /* valida se é um produto */
            if (vendaModeloItem.getIdProduto() != 0) {

                itemVenda = new ItemVenda(vendaModeloItem.getIdProduto(), new ProdutoModelo());

            }

            /* valida se é um serviço */
            if (vendaModeloItem.getIdServico() != 0) {

                itemVenda = new ItemVenda(vendaModeloItem.getIdServico(), new ServicoModelo());

            }

            Object[] linhaDefaultTableModel = new Object[]{
                vendaModeloItem.getQuantidade(),
                itemVenda.getNome(),
                ConverteNumeroParaMoedaBr.converter(vendaModeloItem.getDesconto().toString()),
                ConverteNumeroParaMoedaBr.converter(preco.toString())

            };

            valorTotal = valorTotal.add(preco);
            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

        view.jTvendaValorTotal.setText(ConverteNumeroParaMoedaBr.converter(valorTotal.toString()));

    }

    public BigDecimal calcularProdutoSelecionado(ItemVenda itemVenda) {

        BigDecimal quantidade = BigDecimais.formatarParaBigDecimal(view.jTprodutoQuantidade.getText());
        BigDecimal desconto = BigDecimais.formatarParaBigDecimal(view.jTprodutoDesconto.getText());
        return itemVenda.getValor().multiply(quantidade).subtract(desconto);

    }

    public void habilitarCamposDeAdicionarProduto(ItemVenda itemVenda) {

        view.jBprodutoAdicionaItem.setEnabled(itemVenda.getId() != 0);
        view.jBprodutoLimpaItem.setEnabled(itemVenda.getId() != 0);
        view.jBcalcularGranel.setEnabled(itemVenda.getProdutoModelo().getId() != 0 && itemVenda.getPeso().compareTo(new BigDecimal(0)) == 1);

    }

    public void habilitarCamposDeExcluirVenda() {

        view.jBvendaExcluir.setEnabled(vendaModelo.getId() != 0);
        view.jBimprimir.setEnabled(vendaModelo.getId() != 0);

    }

    public void habilitarCamposDeExcluirProdutoAdicionado() {

        view.jBprodutoSelecionadoRemoverItem.setEnabled(view.jTprodutoSelecionado.getSelectedRow() != -1);

    }

    public void finalizarVenda() {

        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        new VendaConcluiController(vendaModelo, view, vendaModeloItemBaixaList, vendaModeloItemDevolveList).construir();

    }

    public boolean excluir() {

        /* volta todos os produtos da venda ao estoque */
        VendaMovimenta vendaMovimenta = new VendaMovimenta(vendaModelo, null, vendaModeloItemOriginalList);
        vendaMovimenta.excluir();

        new VendaComprovantePdf(new PastasSistema().getSubPastaImpressao(), view.getId()).excluir();
        return new Repository(new VendaModelo()).delete(vendaModelo.getId());

    }

    public void abrirCadastro(long id) {

        view.setId(id);
        vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(id);

        /* copia todos os itens de venda para as listas */
        for (VendaModeloItem vendaModeloItem : vendaModelo.getVendaModeloItemList()) {

            vendaModeloItemList.add(vendaModeloItem);
            vendaModeloItemOriginalList.add(vendaModeloItem);

        }

        exibirProdutosSelecionados();

    }

    public void popularGranel(BigDecimal quantidade, ItemVenda itemVenda) {

        view.jTprodutoQuantidade.setText(quantidade.toString());
        exibirProdutoSelecionado(itemVenda);

    }

}
