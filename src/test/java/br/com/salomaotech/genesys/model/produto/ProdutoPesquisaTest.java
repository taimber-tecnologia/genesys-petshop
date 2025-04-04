package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.genesys.view.JFproduto;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ProdutoPesquisaTest {

    private final JFproduto view = new JFproduto();
    private final ProdutoModelo produtoModelo = new ProdutoModelo();
    private ProdutoPesquisa produtoPesquisa = new ProdutoPesquisa(view.jTresultados, view.jCpaginador);

    public ProdutoPesquisaTest() {

        /* simula cadastro de produtos */
        new Repository(new ProdutoModelo()).deleteTodos();
        produtoModelo.setNome("Teste");
        produtoModelo.setCategoria("ABC");
        produtoModelo.setQuantidade(new BigDecimal(50));
        new Repository(produtoModelo).save();

    }

    @Test
    public void testSetNome() {

        boolean isErro = false;

        try {

            produtoPesquisa.setNome(produtoModelo.getNome());

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

            produtoPesquisa.setCategoria(produtoModelo.getCategoria());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ProdutoPesquisa metodo: setCategoria");
        assertEquals(false, isErro);

    }

    @Test
    public void testPesquisar() {

        /* utilizando filtro: nenhum */
        view.jTpesquisaNome.setText(null);
        view.jCpesquisaCategoria.getEditor().setItem("");
        produtoPesquisa = new ProdutoPesquisa(view.jTresultados, view.jCpaginador);
        produtoPesquisa.pesquisar();
        System.out.println("Testando classe ProdutoPesquisa metodo: pesquisar etapa 01");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: nome */
        view.jTpesquisaNome.setText(produtoModelo.getNome());
        view.jCpesquisaCategoria.getEditor().setItem("");
        produtoPesquisa = new ProdutoPesquisa(view.jTresultados, view.jCpaginador);
        produtoPesquisa.pesquisar();
        System.out.println("Testando classe ProdutoPesquisa metodo: pesquisar etapa 02");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: categoria */
        view.jTpesquisaNome.setText(null);
        view.jCpesquisaCategoria.getEditor().setItem(produtoModelo.getCategoria());
        produtoPesquisa = new ProdutoPesquisa(view.jTresultados, view.jCpaginador);
        produtoPesquisa.pesquisar();
        System.out.println("Testando classe ProdutoPesquisa metodo: pesquisar etapa 03");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

        /* utilizando filtro: todos */
        view.jTpesquisaNome.setText(produtoModelo.getNome());
        view.jCpesquisaCategoria.getEditor().setItem(produtoModelo.getCategoria());
        produtoPesquisa = new ProdutoPesquisa(view.jTresultados, view.jCpaginador);
        produtoPesquisa.pesquisar();
        System.out.println("Testando classe ProdutoPesquisa metodo: pesquisar etapa 04");
        assertEquals(true, view.jTresultados.getRowCount() > 0);

    }

}
