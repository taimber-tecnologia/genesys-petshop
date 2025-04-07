package br.com.salomaotech.genesys.controller.venda.venda_inicia;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VendaIniciaMetodosComum {

    private final JFvendaInicia view;
    private final List<VendaModeloItem> vendaModeloItemList;
    private VendaModelo vendaModelo;
    private VendaIniciaMetodosProdutos vendaIniciaMetodosProdutos;
    private VendaIniciaMetodosServicos vendaIniciaMetodosServicos;

    public VendaIniciaMetodosComum(JFvendaInicia view, List<VendaModeloItem> vendaModeloItemList, VendaModelo vendaModelo) {
        this.view = view;
        this.vendaModeloItemList = vendaModeloItemList;
        this.vendaModelo = vendaModelo;
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

        for (VendaModeloItem vendaModeloItem : vendaModeloItemList) {

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

    public void addItemNaLista(VendaModeloItem vendaModeloItem) {

        // adiciona produto
        if (vendaModeloItem.getIdProduto() != 0) {

            vendaIniciaMetodosProdutos.adicionarProdutoNaLista(vendaModeloItem.getIdProduto());

        }

        // adiciona serviço
        if (vendaModeloItem.getIdServico() != 0) {

            vendaIniciaMetodosServicos.adicionarServicoNaLista(vendaModeloItem.getIdServico());

        }

        // reseta os IDS
        vendaModeloItem.setId(0);
        vendaModeloItem.setIdProduto(0);
        vendaModeloItem.setIdServico(0);

    }

    public void removerItemNaLista() {

        try {

            vendaModeloItemList.remove(view.jTitensSelecionados.getSelectedRow());

        } catch (Exception ex) {

        }

        exibirSelecionados();
        habilitarCamposDeExcluirItemAdicionado();

    }

    public BigDecimal calcularItemSelecionadao(VendaModeloItem vendaModeloItem) {

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

            vendaModeloItemList.add(vendaModeloItem);

        }

        exibirSelecionados();
        habilitarCampos();

    }

}
