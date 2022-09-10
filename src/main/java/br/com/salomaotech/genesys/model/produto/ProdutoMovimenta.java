package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoMovimenta {

    private List<ProdutoModelo> produtoModeloListAdiciona = new ArrayList();
    private List<BigDecimal> quantidadeListAdiciona = new ArrayList();

    private List<ProdutoModelo> produtoModeloListRemove = new ArrayList();
    private List<BigDecimal> quantidadeListRemove = new ArrayList();

    public void adicionarItemLista(long id, BigDecimal quantidade) {

        produtoModeloListAdiciona.add((ProdutoModelo) new Repository(new ProdutoModelo()).findById(id));
        quantidadeListAdiciona.add(quantidade);

    }

    public void removerItemLista(long id, BigDecimal quantidade) {

        produtoModeloListRemove.add((ProdutoModelo) new Repository(new ProdutoModelo()).findById(id));
        quantidadeListRemove.add(quantidade);

    }

    private void processarAdicionar() {

        int contador = 0;

        for (ProdutoModelo produtosModelo : produtoModeloListAdiciona) {

            /* atualiza a quantidade para mais */
            produtosModelo.setQuantidade(produtosModelo.getQuantidade().add(quantidadeListAdiciona.get(contador)));
            new Repository(produtosModelo).save();
            contador++;

        }

        /* limpa lista */
        produtoModeloListAdiciona = new ArrayList();
        quantidadeListAdiciona = new ArrayList();

    }

    private void processarRemover() {

        int contador = 0;

        for (ProdutoModelo produtosModelo : produtoModeloListRemove) {

            /* atualiza a quantidade para menos */
            produtosModelo.setQuantidade(produtosModelo.getQuantidade().subtract(quantidadeListRemove.get(contador)));

            new Repository(produtosModelo).save();
            contador++;

        }

        /* limpa lista */
        produtoModeloListRemove = new ArrayList();
        quantidadeListRemove = new ArrayList();

    }

    public void movimentar() {

        processarAdicionar();
        processarRemover();

    }

}
