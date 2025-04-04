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

    public VendaMovimenta(VendaModelo vendaModelo, List<VendaModeloItem> vendaModeloItemBaixaList) {
        this.vendaModelo = vendaModelo;
        financeiroMovimenta = new FinanceiroMovimenta(vendaModelo);
        this.vendaModeloItemBaixaList = vendaModeloItemBaixaList;
    }

    private void baixarEstoque() {

        if (!isNull(vendaModeloItemBaixaList)) {

            vendaModeloItemBaixaList.forEach(vendaModeloItem -> {

                produtosMovimenta.removerItemLista(vendaModeloItem.getIdProduto(), vendaModeloItem.getQuantidade());

            });

        }

        produtosMovimenta.movimentar();

    }

    public void finalizar() {

        new Repository(vendaModelo).save();
        baixarEstoque();
        financeiroMovimenta.adicionar();

    }

    public void reabrir() {

        new Repository(vendaModelo).save();
        financeiroMovimenta.remover();

    }

    public void excluir() {

        financeiroMovimenta.remover();

    }

}
