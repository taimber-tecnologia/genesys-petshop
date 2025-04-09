package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.controller.venda.venda_conclui.VendaConcluiController;
import br.com.salomaotech.genesys.controller.venda.venda_inicia.servico.VendaIniciaMetodosServicos;
import br.com.salomaotech.genesys.controller.venda.venda_inicia.produto.VendaIniciaMetodosProdutos;
import br.com.salomaotech.genesys.model.configuracoes.PastasSistema;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.model.venda.VendaComprovantePdf;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.model.venda.VendaMovimenta;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VendaIniciaMetodosComum {

    private final JFvendaInicia view;
    private final List<VendaModeloItem> vendaModeloItemListCompartilhado;
    private final VendaModeloItem vendaModeloItemCompartilhado;

    private VendaModelo vendaModelo = new VendaModelo();
    private VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos;
    private VendaIniciaMetodosServicos vendaIniciaMetodosServicos;

    public VendaIniciaMetodosComum(JFvendaInicia view, List<VendaModeloItem> vendaModeloItemListCompartilhado, VendaModeloItem vendaModeloItemCompartilhado) {
        this.view = view;
        this.vendaModeloItemListCompartilhado = vendaModeloItemListCompartilhado;
        this.vendaModeloItemCompartilhado = vendaModeloItemCompartilhado;
    }

    public void setVendaIniciaMetodosProdutos(VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos) {
        this.vendaIniciaMetodosProdutos = vendaIniciaMetodosProdutos;
    }

    public void setVendaIniciaMetodosServicos(VendaIniciaMetodosServicos vendaIniciaMetodosServicos) {
        this.vendaIniciaMetodosServicos = vendaIniciaMetodosServicos;
    }

    public void exibirSelecionados() {

        DefaultTableModel defaultTableModel = (DefaultTableModel) view.jTitensSelecionados.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;
        BigDecimal valorTotal = new BigDecimal(0);

        for (VendaModeloItem vendaModeloItem : vendaModeloItemListCompartilhado) {

            ItemVenda itemVenda;

            if (vendaModeloItem.getIdProduto() != 0) {

                itemVenda = new ItemVenda(vendaModeloItem.getIdProduto(), new ProdutoModelo());

            } else {

                itemVenda = new ItemVenda(vendaModeloItem.getIdServico(), new ServicoModelo());

            }

            BigDecimal preco = vendaModeloItem.getValor().multiply(vendaModeloItem.getQuantidade());

            Object[] linha = new Object[]{
                vendaModeloItem.getQuantidade(),
                itemVenda.getNome(),
                ConverteNumeroParaMoedaBr.converter(preco.toString())
            };

            valorTotal = valorTotal.add(preco);
            defaultTableModel.insertRow(contador++, linha);

        }

        view.jTvendaValorTotal.setText(ConverteNumeroParaMoedaBr.converter(valorTotal.toString()));

    }

    public void habilitarCampos() {

        boolean isNovaVenda = vendaModelo.getId() == 0;
        boolean isVendaExistente = !isNovaVenda;

        // Botões disponíveis apenas para vendas existentes
        view.jBvendaExcluir.setEnabled(isVendaExistente);
        view.jBimprimir.setEnabled(isVendaExistente);

        // Campos disponíveis apenas para novas vendas
        view.jBvendaFinaliza.setEnabled(isNovaVenda);
        view.jTitemQuantidade.setEnabled(isNovaVenda);
        view.jTitensSelecionados.setEnabled(isNovaVenda);
        view.jTlistaDeProdutos.setEnabled(isNovaVenda);
        view.jTlistaDeServicos.setEnabled(isNovaVenda);
        view.jTpesquisaNomeProduto.setEnabled(isNovaVenda);
        view.jTpesquisaNomeServico.setEnabled(isNovaVenda);

    }

    public void habilitarCamposDeExcluirItemAdicionado() {

        view.jBremoveItemSelecionadoLista.setEnabled(view.jTitensSelecionados.getSelectedRow() != -1);

    }

    public void addItemNaLista() {

        // adiciona produto
        if (vendaModeloItemCompartilhado.getIdProduto() != 0) {

            ItemVenda itemVenda = new ItemVenda(vendaModeloItemCompartilhado.getIdProduto(), new ProdutoModelo());
            VendaModeloItem vendaModeloItem = new VendaModeloItem();
            vendaModeloItem.setIdProduto(itemVenda.getProdutoModelo().getId());
            vendaModeloItem.setValor(itemVenda.getValor());
            vendaModeloItem.setQuantidade(BigDecimais.formatarParaBigDecimal(view.jTitemQuantidade.getText()));
            vendaModeloItemListCompartilhado.add(vendaModeloItem);
            vendaIniciaMetodosProdutos.adicionarProdutoNaLista();

        }

        // adiciona serviço
        if (vendaModeloItemCompartilhado.getIdServico() != 0) {

            ItemVenda itemVenda = new ItemVenda(vendaModeloItemCompartilhado.getIdServico(), new ServicoModelo());
            VendaModeloItem vendaModeloItem = new VendaModeloItem();
            vendaModeloItem.setIdServico(itemVenda.getServicoModelo().getId());
            vendaModeloItem.setValor(itemVenda.getValor());
            vendaModeloItem.setQuantidade(BigDecimais.formatarParaBigDecimal(view.jTitemQuantidade.getText()));
            vendaModeloItemListCompartilhado.add(vendaModeloItem);
            vendaIniciaMetodosServicos.adicionarServicoNaLista();

        }

        // exibe os itens selecionados
        exibirSelecionados();

        // reseta os IDS
        vendaModeloItemCompartilhado.setId(0);
        vendaModeloItemCompartilhado.setIdProduto(0);
        vendaModeloItemCompartilhado.setIdServico(0);

    }

    public void removerItemNaLista() {

        try {

            vendaModeloItemListCompartilhado.remove(view.jTitensSelecionados.getSelectedRow());

        } catch (Exception ex) {

        }

        exibirSelecionados();
        habilitarCamposDeExcluirItemAdicionado();

    }

    public BigDecimal calcularItemSelecionado(VendaModeloItem vendaModeloItem) {

        ItemVenda itemVenda;

        if (vendaModeloItem.getIdProduto() != 0) {

            itemVenda = new ItemVenda(vendaModeloItem.getIdProduto(), new ProdutoModelo());

        } else {

            itemVenda = new ItemVenda(vendaModeloItem.getIdServico(), new ServicoModelo());

        }

        BigDecimal quantidade = BigDecimais.formatarParaBigDecimal(view.jTitemQuantidade.getText());
        return itemVenda.getValor().multiply(quantidade);

    }

    public void abrirCadastro(long id) {

        view.setId(id);
        vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(id);

        /* copia todos os itens de venda para as listas */
        for (VendaModeloItem vendaModeloItem : vendaModelo.getVendaModeloItemList()) {

            vendaModeloItemListCompartilhado.add(vendaModeloItem);

        }

        exibirSelecionados();
        habilitarCampos();

    }

    public void finalizarVenda() {

        vendaModelo.setVendaModeloItemList(vendaModeloItemListCompartilhado);
        new VendaConcluiController(vendaModelo, view, vendaModeloItemListCompartilhado).construir();

    }

    public boolean excluir() {

        VendaMovimenta vendaMovimenta = new VendaMovimenta(vendaModelo, null);
        vendaMovimenta.excluir();

        new VendaComprovantePdf(new PastasSistema().getSubPastaImpressao(), view.getId()).excluir();
        return new Repository(new VendaModelo()).delete(vendaModelo.getId());

    }

}
