package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.financeiro.FinanceiroMovimenta;
import br.com.salomaotech.genesys.model.produto.ProdutoMovimenta;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.List;
import static java.util.Objects.isNull;

public class VendaMovimenta {

    private final VendaModelo vendaModelo;
    private final ProdutoMovimenta produtosMovimenta = new ProdutoMovimenta();
    private final FinanceiroMovimenta financeiroMovimenta;
    private final List<VendaModeloItem> vendaModeloItemBaixaList;
    private final List<VendaModeloItem> vendaModeloItemDevolveList;

    public VendaMovimenta(VendaModelo vendaModelo, List<VendaModeloItem> vendaModeloItemBaixaList, List<VendaModeloItem> vendaModeloItemDevolveList) {
        this.vendaModelo = vendaModelo;
        financeiroMovimenta = new FinanceiroMovimenta(vendaModelo);
        this.vendaModeloItemBaixaList = vendaModeloItemBaixaList;
        this.vendaModeloItemDevolveList = vendaModeloItemDevolveList;
    }

    private void baixarEstoque() {

        if (!isNull(vendaModeloItemBaixaList)) {

            vendaModeloItemBaixaList.forEach(vendaModeloItem -> {

                produtosMovimenta.removerItemLista(vendaModeloItem.getIdProduto(), vendaModeloItem.getQuantidade());

            });

        }

        produtosMovimenta.movimentar();

    }

    private void voltarEstoque() {

        if (!isNull(vendaModeloItemDevolveList)) {

            vendaModeloItemDevolveList.forEach(vendaModeloItem -> {

                produtosMovimenta.adicionarItemLista(vendaModeloItem.getIdProduto(), vendaModeloItem.getQuantidade());

            });

        }

        produtosMovimenta.movimentar();

    }

    public void finalizar() {

        vendaModelo.setRevisoes(vendaModelo.getRevisoes() + 1);
        new Repository(vendaModelo).save();
        baixarEstoque();
        financeiroMovimenta.adicionar();

    }

    public void reabrir() {

        new Repository(vendaModelo).save();
        voltarEstoque();
        financeiroMovimenta.remover();

    }

    public void excluir() {

        voltarEstoque();
        financeiroMovimenta.remover();

    }

}
