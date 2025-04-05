package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import javax.swing.JTable;
import javax.swing.JComboBox;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ProdutoPesquisaTest {

    private final JTable jTresultados = new JTable();
    private final JComboBox<String> jCpaginador = new JComboBox<>();
    private final ProdutoPesquisa produtoPesquisa = new ProdutoPesquisa(jTresultados, jCpaginador);

    public ProdutoPesquisaTest() {

        /* remove cadastros antigos */
        new Repository(new ProdutoModelo()).deleteTodos();

        /* 100 produtos: nome = Teste, categoria = ABC */
        for (int i = 1; i <= 50; i++) {

            ProdutoModelo produtoModelo = new ProdutoModelo();
            produtoModelo.setNome("Teste");
            produtoModelo.setCategoria("ABC");
            produtoModelo.setQuantidade(new BigDecimal(50));
            new Repository(produtoModelo).save();

        }

        /* 100 produtos: nome = Outro, categoria = XYZ */
        for (int i = 1; i <= 50; i++) {

            ProdutoModelo produtoModelo = new ProdutoModelo();
            produtoModelo.setNome("Outro");
            produtoModelo.setCategoria("XYZ");
            produtoModelo.setQuantidade(new BigDecimal(25));
            new Repository(produtoModelo).save();

        }

        /* 25 produtos com nome = NomeTeste */
        for (int i = 1; i <= 25; i++) {

            ProdutoModelo produtoModelo = new ProdutoModelo();
            produtoModelo.setNome("NomeTeste");
            produtoModelo.setCategoria("Unica");
            produtoModelo.setQuantidade(new BigDecimal(10));
            new Repository(produtoModelo).save();

        }

        /* 25 produtos com categoria = CategoriaTeste */
        for (int i = 1; i <= 25; i++) {

            ProdutoModelo produtoModelo = new ProdutoModelo();
            produtoModelo.setNome("Unico");
            produtoModelo.setCategoria("CategoriaTeste");
            produtoModelo.setQuantidade(new BigDecimal(5));
            new Repository(produtoModelo).save();

        }

    }

    @Test
    public void testSetNome() {

        boolean isErro = false;

        try {

            produtoPesquisa.setNome("Teste");

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ProdutoPesquisa metodo: setNome");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetCategoria() {

        boolean isErro = false;

        try {

            produtoPesquisa.setCategoria("ABC");

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ProdutoPesquisa metodo: setCategoria");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* filtro: nenhum */
        produtoPesquisa.setNome(null);
        produtoPesquisa.setCategoria(null);
        produtoPesquisa.pesquisar();
        int totalTodos = jTresultados.getRowCount();
        System.out.println("Pesquisar etapa 01: nenhum filtro");
        assertEquals(100, totalTodos); // respeitando limite do paginador

        /* filtro: nome = Teste */
        produtoPesquisa.setNome("Teste");
        produtoPesquisa.setCategoria(null);
        produtoPesquisa.pesquisar();
        int totalNome = jTresultados.getRowCount();
        System.out.println("Pesquisar etapa 02: nome = Teste");
        assertEquals(75, totalNome);

        /* filtro: categoria = XYZ */
        produtoPesquisa.setNome(null);
        produtoPesquisa.setCategoria("XYZ");
        produtoPesquisa.pesquisar();
        int totalCategoria = jTresultados.getRowCount();
        System.out.println("Pesquisar etapa 03: categoria = XYZ");
        assertEquals(50, totalCategoria);

        /* filtro: nome = Outro e categoria = XYZ */
        produtoPesquisa.setNome("Outro");
        produtoPesquisa.setCategoria("XYZ");
        produtoPesquisa.pesquisar();
        int totalAmbos = jTresultados.getRowCount();
        System.out.println("Pesquisar etapa 04: nome = Outro, categoria = XYZ");
        assertEquals(50, totalAmbos);

        /* filtro: nome = NomeTeste */
        produtoPesquisa.setNome("NomeTeste");
        produtoPesquisa.setCategoria(null);
        produtoPesquisa.pesquisar();
        int totalNomeTeste = jTresultados.getRowCount();
        System.out.println("Pesquisar etapa 05: nome = NomeTeste");
        assertEquals(25, totalNomeTeste);

        /* filtro: categoria = CategoriaTeste */
        produtoPesquisa.setNome(null);
        produtoPesquisa.setCategoria("CategoriaTeste");
        produtoPesquisa.pesquisar();
        int totalCategoriaTeste = jTresultados.getRowCount();
        System.out.println("Pesquisar etapa 06: categoria = CategoriaTeste");
        assertEquals(25, totalCategoriaTeste);

    }

}
