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

        if (id != 0) {

            produtoModeloListAdiciona.add((ProdutoModelo) new Repository(new ProdutoModelo()).findById(id));
            quantidadeListAdiciona.add(quantidade);

        }

    }

    public void removerItemLista(long id, BigDecimal quantidade) {

        if (id != 0) {

            produtoModeloListRemove.add((ProdutoModelo) new Repository(new ProdutoModelo()).findById(id));
            quantidadeListRemove.add(quantidade);

        }

    }

    private void processarAdicionar() {

        int contador = 0;
        for (ProdutoModelo produtoModelo : produtoModeloListAdiciona) {

            /* 1 - puxa os dados novos do banco de dados, caso os da memória já tenham sido atualizados */
            produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(produtoModelo.getId());
            produtoModelo.setQuantidade(produtoModelo.getQuantidade().add(quantidadeListAdiciona.get(contador)));

            /* 2 - atualiza o banco de dados */
            new Repository(produtoModelo).save();
            contador++;

        }

        produtoModeloListAdiciona = new ArrayList();
        quantidadeListAdiciona = new ArrayList();

    }

    private void processarRemover() {

        int contador = 0;
        for (ProdutoModelo produtoModelo : produtoModeloListRemove) {

            /* 1 - puxa os dados novos do banco de dados, caso os da memória já tenham sido atualizados */
            produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(produtoModelo.getId());
            produtoModelo.setQuantidade(produtoModelo.getQuantidade().subtract(quantidadeListRemove.get(contador)));

            /* 2 - atualiza o banco de dados */
            new Repository(produtoModelo).save();
            contador++;

        }

        produtoModeloListRemove = new ArrayList();
        quantidadeListRemove = new ArrayList();

    }

    public void movimentar() {

        processarAdicionar();
        processarRemover();

    }

}
