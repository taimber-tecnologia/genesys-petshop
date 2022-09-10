package br.com.salomaotech.genesys.model.venda.cliente;

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

    private void setarVendaComoFinalizada() {

        vendaModelo.setFinalizado(true);
        new Repository(vendaModelo).save();

    }

    private void setarVendaComoAberta() {

        vendaModelo.setFinalizado(false);
        new Repository(vendaModelo).save();

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

        setarVendaComoFinalizada();
        baixarEstoque();
        financeiroMovimenta.adicionar();

    }

    public void reabrir() {

        setarVendaComoAberta();
        voltarEstoque();
        financeiroMovimenta.remover();

    }

    public void excluir() {

        /* só volta para o estoque se a venda já estiver sido finalizada */
        if (vendaModelo.isFinalizado()) {

            voltarEstoque();

        }

        financeiroMovimenta.remover();

    }

}
