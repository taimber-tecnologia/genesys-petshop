package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.financeiro.FinanceiroMovimenta;
import br.com.salomaotech.genesys.model.produto.ProdutoMovimenta;
import br.com.salomaotech.sistema.jpa.Repository;

public class VendaMovimenta {

    private final VendaModelo vendaModelo;
    private final ProdutoMovimenta produtosMovimenta = new ProdutoMovimenta();
    private final FinanceiroMovimenta financeiroMovimenta;

    public VendaMovimenta(VendaModelo vendaModelo) {
        this.vendaModelo = vendaModelo;
        financeiroMovimenta = new FinanceiroMovimenta(vendaModelo);
    }

    private void baixarEstoque() {

        vendaModelo.getVendaModeloItemList().forEach(vendaModeloItem -> {

            produtosMovimenta.removerItemLista(vendaModeloItem.getIdProduto(), vendaModeloItem.getQuantidade());

        });

        produtosMovimenta.movimentar();

    }

    private void voltarEstoque() {

        vendaModelo.getVendaModeloItemList().forEach(vendaModeloItem -> {

            produtosMovimenta.adicionarItemLista(vendaModeloItem.getIdProduto(), vendaModeloItem.getQuantidade());

        });

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
