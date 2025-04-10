package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import javax.swing.JTable;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaProdutoPesquisaTest {

    private final JTable jTresultados = new JTable();
    private final VendaProdutoPesquisa vendaProdutoPesquisa = new VendaProdutoPesquisa(jTresultados);

    @Test
    public void testSetNome() {

        boolean isErro = false;

        try {

            vendaProdutoPesquisa.setNome("Teste");

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe VendaProdutoPesquisa método setNome");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* remove cadastros antigos */
        new Repository(new ProdutoModelo()).deleteTodos();

        // Simula cadastro de produtos
        for (int i = 1; i <= 5; i++) {

            ProdutoModelo produtoModelo = new ProdutoModelo();
            produtoModelo.setNome("Teste");
            produtoModelo.setCategoria("ABC");
            produtoModelo.setQuantidade(new BigDecimal(50));
            new Repository(produtoModelo).save();

        }

        // Simula cadastro de produtos
        for (int i = 1; i <= 6; i++) {

            ProdutoModelo produtoModelo = new ProdutoModelo();
            produtoModelo.setNome("Outro");
            produtoModelo.setCategoria("XYZ");
            produtoModelo.setQuantidade(new BigDecimal(25));
            new Repository(produtoModelo).save();

        }

        /* filtro: nenhum */
        vendaProdutoPesquisa.setNome(null);
        vendaProdutoPesquisa.pesquisar();
        System.out.println("Testando classe VendaProdutoPesquisa método pesquisar etapa 01");
        assertEquals(11, jTresultados.getRowCount());

        /* filtro: nome = Teste */
        vendaProdutoPesquisa.setNome("Teste");
        vendaProdutoPesquisa.pesquisar();
        System.out.println("Testando classe VendaProdutoPesquisa método pesquisar etapa 02");
        assertEquals(5, jTresultados.getRowCount());

        /* filtro: nome = Outro */
        vendaProdutoPesquisa.setNome("Outro");
        vendaProdutoPesquisa.pesquisar();
        System.out.println("Testando classe VendaProdutoPesquisa método pesquisar etapa 03");
        assertEquals(6, jTresultados.getRowCount());

    }

}
