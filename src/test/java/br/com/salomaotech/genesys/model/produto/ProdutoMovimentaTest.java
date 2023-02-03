package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProdutoMovimentaTest {

    private final ProdutoMovimenta produtoMovimenta = new ProdutoMovimenta();
    private ProdutoModelo produtoModelo = new ProdutoModelo();

    public ProdutoMovimentaTest() {

        /* simula cadastro de produto */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setQuantidade(new BigDecimal(25));
        new Repository(produtoModelo).save();

    }

    @Test
    public void testAdicionarItemLista() {

        /* quantidade a adicionar e esperado */
        BigDecimal quantidadeAdicionar = new BigDecimal(100);
        BigDecimal quantidadeEsperada = produtoModelo.getQuantidade().add(quantidadeAdicionar);

        /* adiciona movimentação de produto */
        produtoMovimenta.adicionarItemLista(produtoModelo.getId(), quantidadeAdicionar);
        produtoMovimenta.movimentar();

        /* recarrega o modelo para puxar as alterações */
        produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(produtoModelo.getId());

        /* testa se o produto foram atualizados para mais */
        System.out.println("Testando classe ProdutoMovimenta metodo: adicionarItemLista");
        assertEquals(true, produtoModelo.getQuantidade().equals(quantidadeEsperada));

    }

    @Test
    public void testRemoverItemLista() {

        /* quantidade a remover e esperado */
        BigDecimal quantidadeRemover = new BigDecimal(20);
        BigDecimal quantidadeEsperada = produtoModelo.getQuantidade().subtract(quantidadeRemover);

        /* remove movimentação de produto */
        produtoMovimenta.removerItemLista(produtoModelo.getId(), quantidadeRemover);
        produtoMovimenta.movimentar();

        /* recarrega o modelo para puxar as alterações */
        produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(produtoModelo.getId());

        /* testa se o produto foram atualizados para menos */
        System.out.println("Testando classe ProdutoMovimenta metodo: removerItemLista");
        assertEquals(true, produtoModelo.getQuantidade().equals(quantidadeEsperada));

    }

    @Test
    public void testMovimentar() {

        /* quantidade a adicionar, remover e esperado */
        BigDecimal quantidadeAdicionar = new BigDecimal(100);
        BigDecimal quantidadeRemover = new BigDecimal(20);
        BigDecimal quantidadeEsperada = produtoModelo.getQuantidade().add(quantidadeAdicionar).subtract(quantidadeRemover);

        /* adiciona e remove */
        produtoMovimenta.adicionarItemLista(produtoModelo.getId(), quantidadeAdicionar);
        produtoMovimenta.removerItemLista(produtoModelo.getId(), quantidadeRemover);
        produtoMovimenta.movimentar();

        /* recarrega o modelo para puxar as alterações */
        produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(produtoModelo.getId());

        /* testa se a quantidade de produto é igual a quantidade esperada */
        System.out.println("Testando classe ProdutoMovimenta metodo: movimentar etapa 01");
        assertEquals(true, produtoModelo.getQuantidade().equals(quantidadeEsperada));

        /* testa se a quantidade de produto é igual a quantidade esperada */
        // Obs: quando foi executado a primeira operação as listas internas foram limpas
        // por isto o valor esperado deve ser o mesmo
        produtoMovimenta.movimentar();
        System.out.println("Testando classe ProdutoMovimenta metodo: movimentar etapa 02");
        assertEquals(true, produtoModelo.getQuantidade().equals(quantidadeEsperada));

    }

}
