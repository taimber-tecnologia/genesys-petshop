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
import br.com.salomaotech.genesys.model.venda.VendaProdutoPesquisa;
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

    public VendaIniciaMetodos(JFvendaInicia view) {
        this.view = view;
    }

    public void pesquisarProdutos() {

        VendaProdutoPesquisa vendaProdutoPesquisa = new VendaProdutoPesquisa(view.jTprodutoListaDeProdutos);
        vendaProdutoPesquisa.setNome(view.jTpesquisaNomeProduto.getText());
        vendaProdutoPesquisa.pesquisar();

    }

    public void exibirProdutoSelecionado(long id) {

        // carrega novo item de venda: produto
        ItemVenda itemVenda = new ItemVenda(id, new ProdutoModelo());
        view.jTprodutoTotal.setText(ConverteNumeroParaMoedaBr.converter(calcularProdutoSelecionado(id).toString()));
        new ImagemProduto().exibir(String.valueOf(itemVenda.getId()), view.jPdadosPerfilFoto);

        // habilita campos para adicionar o produto a lista
        habilitarCamposDeAdicionarProduto(itemVenda);

    }

    public void limparCalculosProdutoSelecionado() {

        view.jTprodutoQuantidade.setText(null);
        view.jTprodutoTotal.setText(null);

    }

    public void limparProdutoSelecionado() {

        view.jTprodutoQuantidade.setText(null);
        view.jTprodutoTotal.setText(null);
        view.jTprodutoListaDeProdutos.clearSelection();
        view.jTprodutoListaDeServicos.clearSelection();
        view.jBprodutoAdicionaItem.setEnabled(false);
        view.jBcalcularGranel.setEnabled(false);

        // limpa o campo de imagem do produto
        view.jPdadosPerfilFoto.removeAll();
        view.jPdadosPerfilFoto.repaint();

    }

    public void adicionarProdutoNaLista(long id) {

        ItemVenda itemVenda = new ItemVenda(id, new ProdutoModelo());
        VendaModeloItem vendaModeloItem = new VendaModeloItem();
        vendaModeloItem.setIdProduto(itemVenda.getProdutoModelo().getId());
        vendaModeloItem.setIdServico(itemVenda.getServicoModelo().getId());
        vendaModeloItem.setValor(itemVenda.getValor());
        vendaModeloItem.setQuantidade(BigDecimais.formatarParaBigDecimal(view.jTprodutoQuantidade.getText()));
        vendaModeloItemList.add(vendaModeloItem);
        vendaModeloItemBaixaList.add(vendaModeloItem);

        /* atualiza a view */
        habilitarCamposDeAdicionarProduto(new ItemVenda(0, new ProdutoModelo()));
        limparProdutoSelecionado();
        exibirProdutosSelecionados();
        habilitarCamposDeExcluirProdutoAdicionado();

    }

    public void removerProdutoNaLista() {

        try {

            vendaModeloItemList.remove(view.jTitensSelecionados.getSelectedRow());
            vendaModeloItemBaixaList.remove(view.jTitensSelecionados.getSelectedRow());

        } catch (Exception ex) {

        }

        exibirProdutosSelecionados();
        habilitarCamposDeExcluirProdutoAdicionado();

    }

    private void exibirProdutosSelecionados() {

        DefaultTableModel defaultTableModel = (DefaultTableModel) view.jTitensSelecionados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;
        BigDecimal valorTotal = new BigDecimal(0);

        for (VendaModeloItem vendaModeloItem : vendaModeloItemList) {

            ItemVenda itemVenda = new ItemVenda();
            BigDecimal preco = vendaModeloItem.getValor().multiply(vendaModeloItem.getQuantidade());

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
                ConverteNumeroParaMoedaBr.converter(preco.toString())

            };

            valorTotal = valorTotal.add(preco);
            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

        view.jTvendaValorTotal.setText(ConverteNumeroParaMoedaBr.converter(valorTotal.toString()));

    }

    public BigDecimal calcularProdutoSelecionado(long id) {

        ItemVenda itemVenda = new ItemVenda(id, new ProdutoModelo());
        BigDecimal quantidade = BigDecimais.formatarParaBigDecimal(view.jTprodutoQuantidade.getText());
        return itemVenda.getValor().multiply(quantidade);

    }

    public void habilitarCampos() {

        boolean isNovaVenda = vendaModelo.getId() == 0;
        boolean isVendaExistente = !isNovaVenda;

        // Botões disponíveis apenas para vendas existentes
        view.jBvendaExcluir.setEnabled(isVendaExistente);
        view.jBimprimir.setEnabled(isVendaExistente);

        // Campos disponíveis apenas para novas vendas
        view.jBvendaFinaliza.setEnabled(isNovaVenda);
        view.jTprodutoQuantidade.setEnabled(isNovaVenda);
        view.jTitensSelecionados.setEnabled(isNovaVenda);
        view.jTprodutoListaDeProdutos.setEnabled(isNovaVenda);
        view.jTprodutoListaDeServicos.setEnabled(isNovaVenda);
        view.jTpesquisaNomeProduto.setEnabled(isNovaVenda);
        view.jTpesquisaNomeServico.setEnabled(isNovaVenda);

    }

    private void habilitarCamposDeAdicionarProduto(ItemVenda itemVenda) {

        view.jBprodutoAdicionaItem.setEnabled(itemVenda.getId() != 0);
        view.jBcalcularGranel.setEnabled(itemVenda.getProdutoModelo().getId() != 0 && itemVenda.getPeso().compareTo(new BigDecimal(0)) == 1);

    }

    public void habilitarCamposDeExcluirProdutoAdicionado() {

        view.jBprodutoSelecionadoRemoverItem.setEnabled(view.jTitensSelecionados.getSelectedRow() != -1);

    }

    public void finalizarVenda() {

        vendaModelo.setVendaModeloItemList(vendaModeloItemList);
        new VendaConcluiController(vendaModelo, view, vendaModeloItemBaixaList).construir();

    }

    public boolean excluir() {

        VendaMovimenta vendaMovimenta = new VendaMovimenta(vendaModelo, null);
        vendaMovimenta.excluir();

        new VendaComprovantePdf(new PastasSistema().getSubPastaImpressao(), view.getId()).excluir();
        return new Repository(new VendaModelo()).delete(vendaModelo.getId());

    }

    public void popularGranel(BigDecimal quantidade, ItemVenda itemVenda) {

        view.jTprodutoQuantidade.setText(quantidade.toString());
        exibirProdutoSelecionado(itemVenda.getId());

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

        /* habilita campos */
        habilitarCampos();

    }

}
